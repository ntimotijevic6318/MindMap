package dsw.rudok.app.core;

import dsw.rudok.app.command.CommandManager;

public interface Gui {

    void start();

    public void enableRedoAction();
    public void enableUndoAction();

    public void disableRedoAction();
    public void disableUndoAction();

}
