package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRudokAction {

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete_action.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected =  MainFrame.getInstance().getMapTree().getSelectedNode();

        if(selected.getMapNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NODE_CANNOT_BE_DELETED);
            return;
        }

        MainFrame.getInstance().getMapTree().deleteNode(selected);
    }
}
