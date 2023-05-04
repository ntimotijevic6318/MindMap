package dsw.rudok.app.gui.swing.tree;

import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.implementation.Map;
import dsw.rudok.app.repository.implementation.Project;


public class MapFactory extends NodeFactory {

    @Override
    public MapNode createMapNode(MapNode parent) {
        return new Map("Map " + ( ((Project) parent).getCounter()) , parent);
    }
}
