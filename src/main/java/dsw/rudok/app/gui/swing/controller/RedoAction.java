package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends  AbstractRudokAction{

    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/redo_action.png"));
        putValue(NAME, "Redo Action");
        putValue(SHORT_DESCRIPTION, "Redo Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getSelectedIndex();
        MapView mapView = (MapView) ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getComponentAt(index);
        mapView.getCommandManager().doCommand();
    }
}
