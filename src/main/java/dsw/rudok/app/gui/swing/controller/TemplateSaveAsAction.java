package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.Main;
import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.repository.implementation.Map;
import dsw.rudok.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class TemplateSaveAsAction extends AbstractRudokAction{

    public  TemplateSaveAsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
         putValue(SMALL_ICON, loadIcon("/images/save_template.png"));
        putValue(NAME, "Save template");
        putValue(SHORT_DESCRIPTION, "Save template");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Boolean old = UIManager.getBoolean("FileChooser.readOnly");
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        JFileChooser fc = new JFileChooser(".");
        UIManager.put("FileChooser.readOnly", old);


        JFileChooser jfc = new JFileChooser();


        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Map)){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NEED_TO_SAVE_ONLY_MAP);
            return;
        }

        Map map = (Map) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        File mapFile = null;

        jfc.setSelectedFile(new File(map.getName() + ".json"));


        if (map.getFilePath() == null || map.getFilePath().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                mapFile  = jfc.getSelectedFile();
                map.setFilePath(mapFile.getPath());
            } else {
                return;
            }
        }

        ApplicationFramework.getInstance().getSerializer().saveMap(map);
        //MainFrame.getInstance().getActionManager().getExportAction().actionPerformed(e);
    }
}
