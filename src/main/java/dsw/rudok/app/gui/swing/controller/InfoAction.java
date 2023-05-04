package dsw.rudok.app.gui.swing.controller;



import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.dialogs.AbstractDialog;
import dsw.rudok.app.gui.swing.view.dialogs.InfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractRudokAction {

    public InfoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info_action.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    public void actionPerformed(ActionEvent arg0) {
        AbstractDialog infoDialog = new InfoDialog(MainFrame.getInstance(), "Info Dialog" , true);
        infoDialog.setVisible(true);
    }


}
