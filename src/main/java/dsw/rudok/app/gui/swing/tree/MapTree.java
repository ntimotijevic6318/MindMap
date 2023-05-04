package dsw.rudok.app.gui.swing.tree;

import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.tree.view.MapTreeView;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;

import javax.swing.*;

public interface MapTree {

    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapNode child , MapTreeItem parent);
    MapTreeItem getSelectedNode();
    void deleteNode(MapTreeItem selected);

    void loadProject(Project node);

}
