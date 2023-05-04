package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LinkAction extends AbstractRudokAction{

    public LinkAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/link.png"));
        putValue(NAME, "Veza");
        putValue(SHORT_DESCRIPTION, "Veza");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectExplorerView.getInstance().getProjectViewToShow().startLinkState();
    }
}
