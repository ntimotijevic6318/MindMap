package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveAction extends AbstractRudokAction{

    public RemoveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F8, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Brisanje");
        putValue(SHORT_DESCRIPTION, "Brisanje");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectExplorerView.getInstance().getProjectViewToShow().startRemoveState();
    }
}
