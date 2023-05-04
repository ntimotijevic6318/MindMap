package dsw.rudok.app.state.concrete;

import dsw.rudok.app.command.concrete.MoveElementCommand;

import dsw.rudok.app.gui.swing.view.ElementView;

import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Concept;


import dsw.rudok.app.repository.implementation.Link;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.state.State;

import java.awt.*;

public class MoveState extends State {

    Point startPoint;
    Point sp;


    Point diff;
    MoveElementCommand moveElementCommand;


    @Override
    public void mousePressed(int x, int y, MapView mapView) {
    startPoint = new Point(x , y);
    sp = new Point(x, y);

    moveElementCommand = new MoveElementCommand(mapView);
    mapView.getCommandManager().addCommand(moveElementCommand);
    }

    @Override
    public void mouseDragged(int x, int y, MapView mapView) {

        Point difference = new Point(x - startPoint.x , y - startPoint.y);
        diff = new Point(x - sp.x , y - sp.y);

        for(ElementView elementView : mapView.getElementViewList()){

        if(mapView.getSelectionModel().getElementList().contains(elementView.getElement())) {

           if(elementView.getElement() instanceof Concept) {
               ((Concept) elementView.getElement()).setPosition(new Point(((Concept) elementView.getElement()).getPosition().x + difference.x, ((Concept) elementView.getElement()).getPosition().y + difference.y));
           }

       }

    }
    startPoint = new Point(x , y);
    }

    @Override
    public void mouseReleased(int x, int y, MapView mapView) {
        moveElementCommand.setDiff(diff);

        for(ElementView elementView : mapView.getElementViewList()){
            if(elementView.getElement() instanceof Link){
                ((Link) elementView.getElement()).setSourcePosition(((Link) elementView.getElement()).getFrom().centerOfFrom());
                ((Link) elementView.getElement()).setDestinationPosition(((Link) elementView.getElement()).getTo().centerOfTo());
            }
        }
        ((Project)mapView.getMap().getParent()).changed = true;

    }
}


