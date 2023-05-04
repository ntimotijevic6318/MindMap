package dsw.rudok.app.gui.swing.tree;

import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;


public class ProjectFactory extends NodeFactory{

    @Override
    public MapNode createMapNode(MapNode parent) {
        return new Project("Project " + ( ((ProjectExplorer) parent).getCounter()) , parent);
    }
}
