package dsw.rudok.app.gui.swing.view;


import dsw.rudok.app.repository.implementation.Element;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;


@Getter
@Setter
public abstract class ElementView extends JPanel {

    protected Shape shape;
    Element element;

    public ElementView(Element element) {
    this.element = element;
    }

    public abstract void paint(Graphics2D g);
    public abstract boolean elementAt(ElementView elementView, Point pos);
    public abstract Point centerPointOfShape();

}
