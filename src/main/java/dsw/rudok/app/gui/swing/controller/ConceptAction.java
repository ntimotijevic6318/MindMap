package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ConceptAction extends  AbstractRudokAction{

    public ConceptAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/concept.png"));
        putValue(NAME, "Pojam");
        putValue(SHORT_DESCRIPTION, "Pojam");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectExplorerView.getInstance().getProjectViewToShow().startConceptState();
    }
}
