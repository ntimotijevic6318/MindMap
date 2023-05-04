package dsw.rudok.app.repository.composite;

import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import lombok.*;

@Getter
@Setter
public abstract class MapNode {

    private String name;

    @ToString.Exclude
    public transient MapNode parent;
    private transient MapTreeItem mapTreeItem;

    public MapNode(String name,  MapNode  parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MapNode) {
            MapNode otherObj = (MapNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }



}
