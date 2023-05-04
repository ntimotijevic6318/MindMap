package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectionAction extends  AbstractRudokAction{

    public SelectionAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F7, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/selection.png"));
        putValue(NAME, "Selekcija");
        putValue(SHORT_DESCRIPTION, "Selekcija");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectExplorerView.getInstance().getProjectViewToShow().startSelectionState();
    }
}
