package dsw.rudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {

    @Override public void paint(Graphics g)
    {
        Graphics graphics = (Graphics2D) g ;
        //Get the current size of this component
        Dimension d = this.getSize();

        //draw in black
        graphics.setColor(Color.BLACK);

        //draw a centered horizontal line
        graphics.drawLine(d.width / 2, 0,
                d.width / 2, d.height);


    }

}
