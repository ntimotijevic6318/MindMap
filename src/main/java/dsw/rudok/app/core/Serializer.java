package dsw.rudok.app.core;

import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.repository.implementation.Project;

import java.io.File;

public abstract class Serializer {

    protected abstract  Project loadProject(File file);
    protected abstract void saveProject(Project node);
    protected  abstract  void addMaps(Project project);

}
