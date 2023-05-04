package dsw.rudok.app.gui.swing.listeners.mouse;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MapViewMouseMotionListener implements MouseMotionListener {

    MapView mapView;

    public MapViewMouseMotionListener(MapView mapView) {
    this.mapView = mapView;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        ProjectExplorerView.getInstance().getProjectViewToShow().mouseDragged(e.getX(), e.getY(), mapView);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
