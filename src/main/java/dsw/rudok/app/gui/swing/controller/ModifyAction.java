package dsw.rudok.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ModifyAction extends AbstractRudokAction {

    public ModifyAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F11, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/modify.png"));
        putValue(NAME, "Podesavanje boje");
        putValue(SHORT_DESCRIPTION, "Podesavanje boje i debljine linije");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // MainFrame.getInstance().add(new TextEditor(ProjectExplorerView.getInstance().getProjectViewToShow()) , BorderLayout.SOUTH);
        //MainFrame.getInstance().revalidate();
    }
}
