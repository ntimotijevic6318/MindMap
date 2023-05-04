package dsw.rudok.app.state;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Map;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;


public abstract class State  {


    public void mousePressed(int x, int y, MapView mapView){

    }

    public  void mouseDragged(int x, int y, MapView mapView){

    }

    public void mouseReleased(int x , int y , MapView mapView){

    }


    public void mouseWell(MouseWheelEvent e , MapView mapView) {
    }
}
