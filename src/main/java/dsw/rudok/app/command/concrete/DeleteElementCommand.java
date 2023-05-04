package dsw.rudok.app.command.concrete;

import dsw.rudok.app.command.Command;
import dsw.rudok.app.gui.swing.tree.MapTreeImplementation;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.*;
import dsw.rudok.app.repository.implementation.Concept;
import dsw.rudok.app.repository.implementation.Element;
import dsw.rudok.app.repository.implementation.Link;

import java.awt.*;
;

public class DeleteElementCommand extends Command {

    ElementView elementView;
    MapView mapView;
    Point sourcePoint;
    MapTreeImplementation mapTreeImplementation = MainFrame.getInstance().getMapTree();
    MapTreeItem parent = mapTreeImplementation.getParent();

    public DeleteElementCommand(ElementView elementView, MapView mapView, Point sourcePoint) {

        this.mapView = mapView;
        this.sourcePoint = sourcePoint;
        this.elementView = elementView;
    }

    @Override
    public void doCommand() {


        mapView.getElementViewList().remove(elementView);
        mapTreeImplementation.deleteMapTreeItem(parent , elementView.getElement());

        //brisanje linija
        for(int i=0 ; i<((Concept) elementView.getElement()).getLinksFrom().size() ; i++){
            Link link = ((Concept) elementView.getElement()).getLinksFrom().get(i);
            for(int j=0 ; j <mapView.getElementViewList().size() ; j++){
                if(link.hashCode() == mapView.getElementViewList().get(j).getElement().hashCode()){
                    mapTreeImplementation.deleteMapTreeItem(parent , link);
                    //((Link)mapView.getElementViewList().get(j).getElement()).getTo().getLinksTo().remove(link);
                    mapView.getElementViewList().remove(mapView.getElementViewList().get(j));
                 }
            }
        }


        for(int i=0 ; i<((Concept) elementView.getElement()).getLinksTo().size() ; i++){
            Link link = ((Concept) elementView.getElement()).getLinksTo().get(i);
            for(int j=0 ; j <mapView.getElementViewList().size() ; j++){
                if(link.hashCode() == mapView.getElementViewList().get(j).getElement().hashCode()){
                    mapTreeImplementation.deleteMapTreeItem(parent , link);
                    //((Link)mapView.getElementViewList().get(j).getElement()).getFrom().getLinksFrom().remove(link);
                    mapView.getElementViewList().remove(mapView.getElementViewList().get(j));
                 }
            }
        }



        mapView.repaint();

    }

    @Override
    public void undoCommand() {


        mapTreeImplementation.addChild(elementView.getElement() , mapTreeImplementation.getParent());


        for(int i= 0 ; i < ((Concept)elementView.getElement()).getLinksFrom().size() ; i++){
            mapTreeImplementation.addChild(((Concept)elementView.getElement()).getLinksFrom().get(i) , mapTreeImplementation.getParent());
        }

        for(int i= 0 ; i < ((Concept)elementView.getElement()).getLinksTo().size() ; i++){
           mapTreeImplementation.addChild(((Concept) elementView.getElement()).getLinksTo().get(i) , mapTreeImplementation.getParent());
        }


    }

}
