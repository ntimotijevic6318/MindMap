package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRudokAction{


    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
               KeyEvent.VK_Z , ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/undo_action.png"));
        putValue(NAME, "Undo Action");
        putValue(SHORT_DESCRIPTION, "UndoAction");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getSelectedIndex();
        MapView mapView = (MapView) ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getComponentAt(index);
        mapView.getCommandManager().undoCommand();
    }
}
