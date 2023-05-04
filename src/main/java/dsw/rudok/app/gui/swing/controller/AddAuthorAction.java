package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.dialogs.AbstractDialog;
import dsw.rudok.app.gui.swing.view.dialogs.AddAuthorDialog;
import dsw.rudok.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractRudokAction{

    public AddAuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add_author_action.png"));
        putValue(NAME, "Add Author");
        putValue(SHORT_DESCRIPTION, "Add Author");
        setEnabled(false);
    }



    @Override
    public void actionPerformed(ActionEvent e) {


            MapTreeItem selected =  MainFrame.getInstance().getMapTree().getSelectedNode();

            if (selected.getMapNode() instanceof Project) {
                AbstractDialog author = new AddAuthorDialog(MainFrame.getInstance(), "Unesite ime autora", true, (Project) selected.getMapNode());
                author.setVisible(true);
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NEED_TO_SELECT_PROJECT);
            }

        }
}
