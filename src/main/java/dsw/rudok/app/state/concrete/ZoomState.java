package dsw.rudok.app.state.concrete;

import dsw.rudok.app.gui.swing.view.MapView;

import dsw.rudok.app.state.State;


import java.awt.event.MouseWheelEvent;


public class ZoomState extends State {

    @Override
    public void mouseWell(MouseWheelEvent e, MapView mapView) {

        mapView.zoom = true;

        if (e.getWheelRotation() < 0) {
            mapView.zf *= 1.1;
            mapView.repaint();
        }

        //Zoom out
        if (e.getWheelRotation() > 0) {
            mapView.zf /= 1.1;
            mapView.repaint();
        }

    }

}
