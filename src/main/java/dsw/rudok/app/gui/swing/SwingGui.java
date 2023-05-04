package dsw.rudok.app.gui.swing;

import dsw.rudok.app.command.CommandManager;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.core.Gui;

public class SwingGui implements Gui {

    private MainFrame instance;


    public SwingGui() {

    }

    @Override
    public void start() {

        disableRedoAction();
        disableUndoAction();


        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }



    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);

    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);


    }
    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

}
