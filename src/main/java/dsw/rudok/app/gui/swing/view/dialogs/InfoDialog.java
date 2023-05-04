package dsw.rudok.app.gui.swing.view.dialogs;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends AbstractDialog{
    public InfoDialog(Frame parent, String message, boolean modal) {
        super(parent, message, modal);
    }

    @Override
    public void initializeDialog() {
        setSize(250,250);
        setLocationRelativeTo(getParent());

        JPanel information = new JPanel();
        information.setLayout(new BorderLayout());
        information.add(new Label("Nikola Timotijevic 63/18 RN"), BorderLayout.CENTER);

        this.add(information , BorderLayout.CENTER);
    }
}
