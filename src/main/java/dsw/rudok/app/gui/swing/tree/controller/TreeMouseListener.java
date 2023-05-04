package dsw.rudok.app.gui.swing.tree.controller;

import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.repository.implementation.NotificationType;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreeMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2) {
            MapTreeItem selected =  MainFrame.getInstance().getMapTree().getSelectedNode();
            if (selected.getMapNode() instanceof Project) {
                ((Project)selected.getMapNode()).notifySubscribers(NotificationType.DOUBLE_CLICK);
                ((ProjectExplorer)selected.getMapNode().getParent()).updateProjectExplorerViewOnProjectDoubleClick();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
