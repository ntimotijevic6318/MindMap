package dsw.rudok.app.state.concrete;

import dsw.rudok.app.command.concrete.AddConceptCommand;
import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;

import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.MapView;

import dsw.rudok.app.repository.implementation.Concept;

import dsw.rudok.app.state.State;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

@NoArgsConstructor
public class ConceptState extends State {


    String text;
    Font font;

    @Override
    public void mousePressed(int x, int y, MapView mapView) {

        mapView.getSelectionModel().clearList();

        Color elementColor = new Color(255, 255, 255);
        Color strokeColor = new Color(227, 176, 25);
        Color textColor = new Color(0 , 0  , 0);


        Point position = new Point(x,y);




        if(mapView.getElementViewList().isEmpty()){

            font = new Font("Arial" ,Font.BOLD , 20);
            text = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite ime glavnog pojma:");

            if (text == null) {
                return;
            }

            while (text.trim().isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CONCEPT_MUST_HAVE_NAME);
                text = JOptionPane.showInputDialog(MainFrame.getInstance(), "Glavni pojam mora imati ime:");
                if (text == null)
                    return;
            }

            Concept concept = new Concept(text.trim()
                    , mapView.getMap()
                    , elementColor, strokeColor
                    , 3, new Point((int) mapView.getBounds().getCenterX() - 50, (int) mapView.getBounds().getCenterY() - 50), calculateDimension(), textColor);

            concept.setText(text);
            concept.setFont(font);


            AddConceptCommand addConceptCommand = new AddConceptCommand(concept);
            mapView.getCommandManager().addCommand(addConceptCommand);

        }else {

            font = new Font("Arial" , Font.PLAIN , 10);
            text = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite ime pojma:");


            if (text == null) {
                return;
            }

            while (text.trim().isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CONCEPT_MUST_HAVE_NAME);
                text = JOptionPane.showInputDialog(MainFrame.getInstance(), "Pojam momra imati ime:");
                if (text == null)
                    return;
            }

            Concept concept = new Concept(text.trim()
                    , mapView.getMap()
                    , elementColor, strokeColor
                    , 3, position, calculateDimension() , textColor);


            concept.setText(text);
            concept.setFont(font);

            AddConceptCommand addConceptCommand = new AddConceptCommand(concept);
            mapView.getCommandManager().addCommand(addConceptCommand);
        }
    }

    private Dimension calculateDimension(){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);

        int textWidth = (int) (font.getStringBounds(this.text, frc).getWidth());
        int textHeight = (int) (font.getStringBounds(this.text, frc).getHeight());

        return new Dimension(textWidth , textHeight);
    }

}
