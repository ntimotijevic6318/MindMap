package dsw.rudok.app.gui.swing.view;

import dsw.rudok.app.repository.implementation.Element;
import dsw.rudok.app.repository.implementation.Link;

import java.awt.*;
import java.awt.geom.*;



public class LinkPainter extends ElementView  {

    Link link;
    MapView mapView;


    public LinkPainter(Element element , MapView mapView) {
        super(element);
        this.link = (Link) element;
        this.mapView = mapView;
    }

    @Override
    public void paint(Graphics2D g) {

        shape = new Line2D.Double(link.getFrom().centerOfFrom().x , link.getFrom().centerOfFrom().y, link.getDestinationPosition().x , link.getDestinationPosition().y);

        g.setColor(link.getElementColor());
        g.fill(getShape());

        g.draw(getShape());

        g.setColor(link.getStrokeColor());
        g.setStroke(new BasicStroke(link.getStroke()));


        g.draw(getShape());


        if(this.link.getTo() != null){
            link.setDestinationPosition(link.getTo().centerOfTo());
        }




    }


    @Override
    public boolean elementAt(ElementView elementView, Point pos)
    {
      return elementView.getShape().getBounds2D().contains(pos.x , pos.y);
    }

    @Override
    public Point centerPointOfShape() {
        return new Point((int) this.shape.getBounds().getCenterX(), (int) this.shape.getBounds().getCenterY());
    }


}
