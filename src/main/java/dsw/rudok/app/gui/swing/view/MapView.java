package dsw.rudok.app.gui.swing.view;
import dsw.rudok.app.command.CommandManager;
import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.implementation.*;

import lombok.Getter;
import lombok.Setter;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter

public class MapView extends JPanel  implements ISubscriber  {

    List<ElementView> elementViewList;

    String name;
    Map map;

    SelectionModel selectionModel;
    RectangleView rectangleView;

    AffineTransform affineTransform = new AffineTransform() ;
    CommandManager commandManager;

    public double zf = 1;
    public double p = 1;
    public boolean zoom;


    public double xOffset = 0;
    public double yOffset = 0;

    JPanel contentPane ;
    JPanel buttonPanel;

    public CommandManager getCommandManager() {
        return commandManager;
    }


    public enum Handle {
        North, South, East, West, SouthEast, SouthWest, NorthEast, NorthWest
    }

    static final int handleSize = 7;
    private static List<File> fileList = new ArrayList<>();

    public MapView(Map map, SelectionModel selectionModel) {

        setLayout(new BorderLayout(20 , 20));
        setBackground(new Color(43 ,43 , 43));

        this.map = map;
        this.selectionModel = selectionModel;
        this.commandManager = new CommandManager();

        map.addSubscriber(this);
        this.selectionModel.addSubscriber(this);

        this.elementViewList = new ArrayList<ElementView>();

    }

    private void walkDirTreeAndSearch(String rootFolder, String searchFor) {

        try {
            Files.walk(Paths.get(rootFolder), FileVisitOption.FOLLOW_LINKS).filter(t -> {
                return t.toString().contains(searchFor);
            }).forEach(path -> {

                String name = String.valueOf(Paths.get(String.valueOf(path)).getFileName());

                BufferedImage myPicture = null;
                try {
                    myPicture = ImageIO.read(new FileInputStream("D:\\InteliJWork\\rm\\src\\main\\resources\\templates\\" + name));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                contentPane.add(picLabel);

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(Object notification) {

        if (((NotificationType) notification).name() == "ADD") {
            addToElementViewList();
            repaint();
        } else if (((NotificationType) notification).name() == "DELETE") {
            removeFromElementViewList();
            repaint();

        } else if (((NotificationType) notification).name() == "ADD_TO_SELECT_LIST") {
            repaint();

        } else if (((NotificationType) notification).name() == "CLEAR_ALL_FROM_SELECT_LIST") {
            repaint();

        } else if(((NotificationType) notification).name() == "COORDS_CHANGED"){
            repaint();
        } else if(((NotificationType) notification).name() == "COLOR_CHANGED"){
            repaint();
        } else if(((NotificationType) notification).name() == "STROKE_CHANGED"){
            repaint();
        } else if(((NotificationType) notification).name() == "TEXT_CHANGED"){
            repaint();
        } else  if(((NotificationType) notification).name() == "KEY_PRESSED"){
            repaint();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (zoom) {

            AffineTransform at = new AffineTransform();

            double xR = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yR = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zDiv = zf / p;

            xOffset = (zDiv) * (xOffset) + (1 - zDiv) * xR;
            yOffset = (zDiv) * (yOffset) + (1 - zDiv) * yR;

            at.translate(xOffset, yOffset);
            at.scale(zf, zf);

            p = zf;
            g2.transform(at);
            zoom = false;
        }


        Graphics2D gselected = (Graphics2D) g;



        for (ElementView elementView : this.elementViewList) {
            if (elementView instanceof ConceptPainter) {
                elementView.paint((Graphics2D) g);
                if (selectionModel.getElementList().contains(elementView.getElement())) {

                    gselected.setColor(new Color(200, 221, 242));
                    gselected.drawRect(elementView.getShape().getBounds().x,
                            elementView.getShape().getBounds().y,
                            elementView.getShape().getBounds().width,
                            elementView.getShape().getBounds().height);


                    for (Handle e : Handle.values()) {
                        paintSelectionHandle(gselected, getHandlePoint(new Point(elementView.getShape().getBounds().x, elementView.getShape().getBounds().y), new Dimension(elementView.getShape().getBounds().width, elementView.getShape().getBounds().height), e));
                    }
                    elementView.paint(gselected);
                }
            }
        }

        for (ElementView elementView : this.getElementViewList()) {
            if (elementView instanceof LinkPainter) {

                if(((Link)elementView.getElement()).getFrom() == null && ((Link)elementView.getElement()).getTo() == null){
                    ((Link) elementView.getElement()).setFrom(this.getConcept( ((Link) elementView.getElement()).getSourcePosition()));
                    ((Link) elementView.getElement()).setTo(this.getConcept( ((Link) elementView.getElement()).getDestinationPosition()));

                    //this.getConcept(((Link) elementView.getElement()).getDestinationPosition()).addLinkTo((Link) elementView.getElement());
                    //this.getConcept(((Link) elementView.getElement()).getSourcePosition()).addLinkFrom((Link) elementView.getElement());
                }
                elementView.paint((Graphics2D) g);
                if (selectionModel.getElementList().contains(elementView.getElement())) {
                    gselected.setColor(new Color(200 , 221 , 242));

                    int x1 = elementView.getShape().getBounds().x;
                    int x2 = elementView.getShape().getBounds().x + elementView.getShape().getBounds().width;
                    int y1 = elementView.getShape().getBounds().y ;
                    int y2 = elementView.getShape().getBounds().y + elementView.getShape().getBounds().height;

                    paintLinkSectionHandle(gselected, new Point(((x1 + x2) / 2), ((y1 + y2) / 2)));
                    elementView.paint((Graphics2D) gselected);
                }
            }
        }

        for (ElementView elementView : this.getElementViewList()) {
            if (elementView instanceof LinkPainter) {

                if(((Link)elementView.getElement()).getFrom() == null && ((Link)elementView.getElement()).getTo() == null){
                    ((Link) elementView.getElement()).setFrom(this.getConcept( ((Link) elementView.getElement()).getSourcePosition()));
                    ((Link) elementView.getElement()).setTo(this.getConcept( ((Link) elementView.getElement()).getDestinationPosition()));

                    //this.getConcept(((Link) elementView.getElement()).getDestinationPosition()).addLinkTo((Link) elementView.getElement());
                    //this.getConcept(((Link) elementView.getElement()).getSourcePosition()).addLinkFrom((Link) elementView.getElement());

                }
                elementView.paint((Graphics2D) g);
                if (selectionModel.getElementList().contains(elementView.getElement())) {
                    gselected.setColor(new Color(200 , 221 , 242));

                    int x1 = elementView.getShape().getBounds().x;
                    int x2 = elementView.getShape().getBounds().x + elementView.getShape().getBounds().width;
                    int y1 = elementView.getShape().getBounds().y ;
                    int y2 = elementView.getShape().getBounds().y + elementView.getShape().getBounds().height;

                    paintLinkSectionHandle(gselected, new Point(((x1 + x2) / 2), ((y1 + y2) / 2)));
                    elementView.paint((Graphics2D) gselected);
                }
            }
        }

        for (ElementView elementView : this.elementViewList) {
            if (elementView instanceof ConceptPainter) {
                elementView.paint((Graphics2D) g);
                if (selectionModel.getElementList().contains(elementView.getElement())) {

                    gselected.setColor(new Color(200 , 221 , 242));
                    gselected.drawRect(elementView.getShape().getBounds().x,
                            elementView.getShape().getBounds().y,
                            elementView.getShape().getBounds().width,
                            elementView.getShape().getBounds().height);


                    for (Handle e : Handle.values()) {
                        paintSelectionHandle(gselected, getHandlePoint(new Point(elementView.getShape().getBounds().x, elementView.getShape().getBounds().y), new Dimension(elementView.getShape().getBounds().width, elementView.getShape().getBounds().height), e));
                    }
                    elementView.paint(gselected);
                }

            }
        }

        rectangleView.paint((Graphics2D) g);


        //Logika da ne moze da se crta element preko elementa!
        // for(int i = 0 ; i < this.elementViewList.size() ; i++){
        //for(int j = i + 1 ; j < this.elementViewList.size() ; j++){
        //    if(this.elementViewList.get(i).getShape().getBounds().intersects(this.getElementViewList().get(j).getShape().getBounds())){
        //        if((this.elementViewList.get(i) instanceof ConceptPainter && this.elementViewList.get(j) instanceof ConceptPainter) || (this.elementViewList.get(i) instanceof LinkPainter && this.elementViewList.get(j) instanceof LinkPainter)) {
        //           this.map.removeChildByHashCode(this.getElementViewList().get(j).getElement().hashCode());
        //          this.map.updateAfterChildRemove();
        //          repaint();
        //    }
        //  }
        ///}
    }



    private void addToElementViewList() {

        Element elementToAdd = this.map.getElementToAdd();

        elementToAdd.addSubscriber(this);

        ElementView elementView = returnElementType(elementToAdd);
        this.getElementViewList().add(elementView);

    }

    private void removeFromElementViewList() {

        Element elementToDelete = this.map.getElementToDelete();
        for (Iterator<ElementView> it = this.elementViewList.iterator(); it.hasNext(); ) {
            ElementView elementView = it.next();
            if (elementView.getElement().hashCode() == elementToDelete.hashCode()) {
                it.remove();
            }
        }
    }




    private ElementView returnElementType(Element elementToAdd) {

        if (elementToAdd instanceof Concept) {
            return new ConceptPainter((Element) elementToAdd);
        } else if (elementToAdd instanceof Link) {
            return new LinkPainter((Link) elementToAdd , this);
        }

        return null;
    }

    private void paintLinkSectionHandle(Graphics2D gselected, Point point) {
        double size = handleSize;
        gselected.fill(new Rectangle2D.Double(Math.abs(point.x), Math.abs(point.y) - 3.5, size, size));
    }

    public void paintSelectionHandle(Graphics2D gselected, Point2D position) {
        double size = handleSize;
        gselected.fill(new Rectangle2D.Double(position.getX() - size / 2, position.getY() - size / 2,
                size, size));

    }

    private Point2D getHandlePoint(Point2D topLeft, Dimension2D size, Handle handlePosition) {
        double x = 0, y = 0;

        // Doređivanje y koordinate

        // Ako su gornji hendlovi
        if (handlePosition == Handle.NorthWest || handlePosition == Handle.North || handlePosition == Handle.NorthEast) {
            y = topLeft.getY();
        }
        // Ako su centralni po y osi
        if (handlePosition == Handle.East || handlePosition == Handle.West) {
            y = topLeft.getY() + size.getHeight() / 2;
        }
        //Ako su donji
        if (handlePosition == Handle.SouthWest || handlePosition == Handle.South || handlePosition == Handle.SouthEast) {
            y = topLeft.getY() + size.getHeight();
        }

        // Određivanje x koordinate

        // Ako su levi
        if (handlePosition == Handle.NorthWest || handlePosition == Handle.West || handlePosition == Handle.SouthWest) {
            x = topLeft.getX();
        }
        // ako su centralni po x osi
        if (handlePosition == Handle.North || handlePosition == Handle.South) {
            x = topLeft.getX() + size.getWidth() / 2;
        }
        // ako su desni
        if (handlePosition == Handle.NorthEast || handlePosition == Handle.East || handlePosition == Handle.SouthEast) {
            x = topLeft.getX() + size.getWidth();
        }

        return new Point2D.Double(x, y);
    }

    public boolean containsPoint(Point point) {

            for (ElementView elementView : this.getElementViewList()) {
                if (elementView.getShape().contains(point.x , point.y)) {
                    return true;
                }
            }
        return  false;
    }


    public Concept getConcept(Point point) {
        for (ElementView elementView : this.getElementViewList()) {
            if (elementView.elementAt(elementView, point) && elementView.getElement() instanceof Concept) {
                return (Concept) elementView.getElement();
            }
        }
        return null;
    }


}

