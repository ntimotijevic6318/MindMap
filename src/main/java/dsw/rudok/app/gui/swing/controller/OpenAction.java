package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractRudokAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/open_action.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open");
    }

    public void actionPerformed(ActionEvent arg0) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                MapTreeItem root = MainFrame.getInstance().getMapTree().getTreeModel().getRoot();
                for(int i=0 ; i < root.getChildCount() ; i++){
                    if(root.getChildAt(i).toString().equals(p.getName())){
                        ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.PROJECT_ALREADY_EXIST);
                        return;
                    }
                }
                MainFrame.getInstance().getMapTree().loadProject(p);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
