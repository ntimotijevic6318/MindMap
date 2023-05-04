package dsw.rudok.app.gui.swing.view.dialogs;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractDialog extends JDialog {

    public AbstractDialog(Frame parent, String message, boolean modal) {
        super(parent, message, modal);
        initializeDialog();
    }

    abstract public void initializeDialog();
}
