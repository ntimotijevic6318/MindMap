package dsw.rudok.app.gui.swing.view;
import dsw.rudok.app.repository.implementation.MyRectangle;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;



@Getter
@Setter
public class  RectangleView extends JPanel {

    MyRectangle rectangle;
    Shape shape;
    JPanel content;

    public RectangleView(MyRectangle rectangle) {
        this.rectangle = rectangle;

    }



    public void paint(Graphics2D g) {

        shape = new Rectangle(rectangle.getSourcePoint().x,  rectangle.getSourcePoint().y ,  rectangle.getWidth(),  rectangle.getHeight());

        Stroke dashed = new BasicStroke((float) 0.1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0);

        g.setStroke(dashed);
        g.setPaint(Color.white);
        g.setBackground(Color.blue);

        g.draw(getShape());
    }

}
