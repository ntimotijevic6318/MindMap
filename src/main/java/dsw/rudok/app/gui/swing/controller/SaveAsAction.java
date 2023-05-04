package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAsAction extends AbstractRudokAction{

    public SaveAsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
       putValue(SMALL_ICON, loadIcon("/images/save_as_action.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save as");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NEED_TO_SAVE_ONLY_PROJECT);
            return;
        }

        Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        File projectFile = null;

        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = jfc.getSelectedFile();
            project.setFilePath(projectFile.getPath());
        } else {
            return;
        }

        ApplicationFramework.getInstance().getSerializer().saveProject(project);
        project.setChanged(false);
    }
}
