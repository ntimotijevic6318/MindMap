package dsw.rudok.app.gui.swing.tree;

import dsw.rudok.app.repository.composite.MapNode;


public abstract  class NodeFactory {

    public MapNode getNode(MapNode parent){
      MapNode mapNode = createMapNode(parent);
      return mapNode;
    }

    public abstract MapNode createMapNode(MapNode parent);
}
