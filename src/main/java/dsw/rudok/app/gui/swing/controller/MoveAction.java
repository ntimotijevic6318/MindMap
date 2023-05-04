package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveAction extends AbstractRudokAction{

    public MoveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F9, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(NAME, "Pomeranje elementa");
        putValue(SHORT_DESCRIPTION, "Pomeranje elementa");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectExplorerView.getInstance().getProjectViewToShow().startMoveState();
    }
}
