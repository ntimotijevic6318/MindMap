package dsw.rudok.app.gui.swing.tree;

import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.implementation.Element;
import dsw.rudok.app.repository.implementation.Map;


public class ElementFactory extends NodeFactory {

    @Override
    public MapNode createMapNode(MapNode parent) {
        //return new Element("Element " + ( ((Map) parent).getCounter()) , parent);
        return  null;
    }
}
