package dsw.rudok.app.state.concrete;

import dsw.rudok.app.command.concrete.DeleteElementCommand;
import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.MapTree;
import dsw.rudok.app.gui.swing.tree.MapTreeImplementation;
import dsw.rudok.app.gui.swing.view.*;
import dsw.rudok.app.repository.implementation.Concept;

import dsw.rudok.app.state.State;
import lombok.NoArgsConstructor;

import java.awt.*;



@NoArgsConstructor
public class RemoveState extends State {

    Point sourcePoint;
    ElementView elementView;

    @Override
    public void mousePressed(int x, int y, MapView mapView) {

        this.sourcePoint = new Point(x, y);

        Concept concept = mapView.getConcept(sourcePoint);

        if(concept == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NEED_TO_CLICK_ON_ELEMENT);
            return;
        }


        for(int i=0 ; i < mapView.getElementViewList().size() ;i++) {
            ElementView ew = mapView.getElementViewList().get(i);
            if (ew.getElement().hashCode() == concept.hashCode()) {
                this.elementView = ew;
            }
        }

        DeleteElementCommand deleteElementCommand = new DeleteElementCommand(elementView, mapView, sourcePoint);
        mapView.getCommandManager().addCommand(deleteElementCommand);
        }


}








