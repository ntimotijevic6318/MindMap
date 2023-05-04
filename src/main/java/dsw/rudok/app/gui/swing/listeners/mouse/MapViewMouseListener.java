package dsw.rudok.app.gui.swing.listeners.mouse;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public  class MapViewMouseListener implements MouseListener {

    protected MapView mapView;

    public MapViewMouseListener(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(ProjectExplorerView.getInstance().getProjectViewToShow().getStateManager().getCurrent() == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NEED_TO_SELECT_STATE);
            return;
        }

        if (e.getButton()==MouseEvent.BUTTON1) {
            ProjectExplorerView.getInstance().getProjectViewToShow().mousePressed(e.getX(), e.getY(), mapView);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e){
        ProjectExplorerView.getInstance().getProjectViewToShow().mouseReleased(e.getX(), e.getY(), mapView);
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
