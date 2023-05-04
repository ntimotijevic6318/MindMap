package dsw.rudok.app.command.concrete;

import dsw.rudok.app.command.Command;
import dsw.rudok.app.gui.swing.view.ElementView;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Concept;
import dsw.rudok.app.repository.implementation.Link;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class MoveElementCommand extends Command {

    MapView mapView;

    Point startPoint;
    Point diff;
    Point difference;

    public MoveElementCommand(MapView mapView) {
        this.mapView = mapView;
    }


    @Override
    public void doCommand() {


    }


    @Override
    public void undoCommand() {
        for (ElementView elementView : mapView.getElementViewList()) {

                if (elementView.getElement() instanceof Concept) {
                    ((Concept) elementView.getElement()).setPosition(new Point(((Concept)elementView.getElement()).getPosition().x - diff.x , ((Concept)elementView.getElement()).getPosition().y - diff.y));
                } else if(elementView.getElement() instanceof Link){
                    ((Link) elementView.getElement()).setSourcePosition(((Link) elementView.getElement()).getFrom().centerOfFrom());
                    ((Link) elementView.getElement()).setDestinationPosition(((Link) elementView.getElement()).getTo().centerOfTo());
                }

            }
        }

    }
