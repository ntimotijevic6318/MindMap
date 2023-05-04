package dsw.rudok.app.state.concrete;

import dsw.rudok.app.command.concrete.AddLinkCommand;
import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.*;
import dsw.rudok.app.repository.implementation.Concept;
import dsw.rudok.app.repository.implementation.Link;
import dsw.rudok.app.state.State;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;

@NoArgsConstructor
public class LinkState extends State {

    Point sourcePoint;
    Point destinationPoint;

    Link link;

    @Override
    public void mousePressed(int x, int y, MapView mapView) {

        this.sourcePoint = new Point(x, y);

        Color elementColor = new Color(153 , 20 , 38);

        if (mapView.containsPoint(sourcePoint)) {
            link = new Link("Temporary Link", mapView.getMap(), elementColor , Color.orange , 3);

            //link.setFrom((ConceptPainter) mapView.getConcept(sourcePoint));
            link.setFrom((Concept) mapView.getConcept(sourcePoint));
            ((Concept)(mapView.getConcept(sourcePoint))).addLinkFrom(link);

            link.setSourcePosition(sourcePoint);
            link.setDestinationPosition(sourcePoint);

            AddLinkCommand addLinkCommand = new AddLinkCommand(link);
            mapView.getCommandManager().addCommand(addLinkCommand);
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.LINE_MUST_HAVE_START_POINT);
        }

    }

    @Override
    public void mouseDragged(int x, int y, MapView map) {

        destinationPoint = new Point(x , y);

        if (link != null) {
            link.setDestinationPosition(destinationPoint);
        }

    }


    @Override
    public void mouseReleased(int x, int y, MapView mapView) {

        if (mapView.containsPoint(link.getDestinationPosition())) {

            this.link.setTo(((Concept) mapView.getConcept(destinationPoint)));
            ((Concept)((mapView.getConcept(destinationPoint)))).addLinkTo(link);

            if (link.getFrom().hashCode() == link.getTo().hashCode()) {
                mapView.getCommandManager().undoCommand();
                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.LINK_CANNOT_BE_INSIDE_ONE_ELEMENT);
                return;
            }

            link.setDestinationPosition(destinationPoint);

            this.link.setName(link.getFrom().getName() + " - " + link.getTo().getName());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectExplorer());

            this.link = null;

        } else {
            mapView.getCommandManager().undoCommand();
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.LINE_MUST_HAVE_END_POINT);
        }
    }



}
