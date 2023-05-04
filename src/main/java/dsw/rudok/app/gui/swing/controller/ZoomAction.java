package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomAction extends AbstractRudokAction{


    public ZoomAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F7, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom_in.png"));
        putValue(NAME, "Zoom");
        putValue(SHORT_DESCRIPTION, "Zoom");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(ProjectExplorerView.getInstance().getProjectViewToShow() != null && ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getTabCount() >= 1) {
            ProjectExplorerView.getInstance().getProjectViewToShow().startZoomState();
        }else{
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CANNOT_ZOOM_BECAUSE_JT_IS_NOT_SHOWING);
            return;
        }
    }

}
