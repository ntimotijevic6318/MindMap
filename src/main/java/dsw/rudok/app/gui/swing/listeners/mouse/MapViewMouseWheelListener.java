package dsw.rudok.app.gui.swing.listeners.mouse;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import java.awt.event.*;


public class MapViewMouseWheelListener  implements MouseWheelListener {

    MapView mapView ;

    public MapViewMouseWheelListener(MapView mapView){
        this.mapView = mapView;
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if(ProjectExplorerView.getInstance().getProjectViewToShow().getStateManager().getCurrent() == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NEED_TO_SELECT_STATE);
            return;
        }


        ProjectExplorerView.getInstance().getProjectViewToShow().mouseWheel(e , mapView);
    }
}
