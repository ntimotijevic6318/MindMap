package dsw.rudok.app.state.concrete;

import dsw.rudok.app.gui.swing.view.ElementView;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Concept;
import dsw.rudok.app.repository.implementation.Element;
import dsw.rudok.app.state.State;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;


@NoArgsConstructor
public class SelectionState extends State {

    Point sourcePoint;
    Element element;

    @Override
    public void mousePressed(int x, int y, MapView mapView) {
        this.sourcePoint = new Point(x, y);
        if (mapView.containsPoint(sourcePoint)) {
            this.element = mapView.getConcept(sourcePoint);
            mapView.getSelectionModel().clearList();
            mapView.getSelectionModel().addToElementList(element);
        } else {
            mapView.getSelectionModel().clearList();
            mapView.getRectangleView().getRectangle().setSourcePoint(new Point(x, y));
        }

    }

    @Override
    public void mouseDragged(int x, int y, MapView mapView) {

        int width = Math.abs(x - this.sourcePoint.x);
        int height = Math.abs(y - this.sourcePoint.y);

        if (sourcePoint.x < x && sourcePoint.y > y) {
            mapView.getRectangleView().getRectangle().setSourcePoint(new Point(sourcePoint.x, sourcePoint.y - height));
        }

        if (sourcePoint.x > x && sourcePoint.y > y) {
            mapView.getRectangleView().getRectangle().setSourcePoint(new Point(sourcePoint.x - width, sourcePoint.y - height));
        }

        if (sourcePoint.x > x && sourcePoint.y < y) {
            mapView.getRectangleView().getRectangle().setSourcePoint(new Point(sourcePoint.x - width, sourcePoint.y));
        }

        mapView.getRectangleView().getRectangle().setWidth(width);
        mapView.getRectangleView().getRectangle().setHeight(height);

        mapView.repaint();
    }

    @Override
    public void mouseReleased(int x, int y, MapView mapView) {

        for (ElementView elementView : mapView.getElementViewList()) {
            if (mapView.getRectangleView().getShape().intersects(elementView.getShape().getBounds())) {
                mapView.getSelectionModel().addToElementList(elementView.getElement());
            }
        }

        mapView.getRectangleView().getRectangle().setWidth(0);
        mapView.getRectangleView().getRectangle().setHeight(0);


       mapView.repaint();
    }

}