package dsw.rudok.app.gui.swing.view;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
        add (MainFrame.getInstance().getActionManager().getDeleteAction());
        add (new Line()) ;
        add (MainFrame.getInstance().getActionManager().getInfoAction());
        add (MainFrame.getInstance().getActionManager().getAddAuthorAction());
        add (MainFrame.getInstance().getActionManager().getZoomAction());
        add (new Line()) ;
        add (MainFrame.getInstance().getActionManager().getUndoAction());
        add (MainFrame.getInstance().getActionManager().getRedoAction());
        add (new Line()) ;
        add (MainFrame.getInstance().getActionManager().getSaveAction());
        add (MainFrame.getInstance().getActionManager().getSaveAsAction());
        add (new Line()) ;
        add (MainFrame.getInstance().getActionManager().getOpenAction());
        add (new Line()) ;
        add (MainFrame.getInstance().getActionManager().getExportAction());
        add (MainFrame.getInstance().getActionManager().getTemplateSaveAsAction());
        add (MainFrame.getInstance().getActionManager().getLoadTemplate());

        MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getDeleteAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getExportAction().setEnabled(false);
    }
}
