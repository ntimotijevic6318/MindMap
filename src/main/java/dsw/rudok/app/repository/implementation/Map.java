package dsw.rudok.app.repository.implementation;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;

import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class Map extends MapNodeComposite  {


    transient MapNode currentProject;

    transient  Element elementToAdd;
    transient  Element elementToDelete;

    transient  MyRectangle rectangle = new MyRectangle();

    String filePath;


    public Map(String name, MapNode parent) {
        super(name, parent);
    }



    @Override
    public void addChild(MapNode child) {

            if (child != null && child instanceof Element) {
                elementToAdd = (Element) child;
                this.getChildren().add(elementToAdd);
                notifySubscribers(NotificationType.ADD);
            }

            ((Project) this.getParent()).changed = true;

    }

    @Override
    public void removeChildren() {
        this.getChildren().clear();

        ((Project) this.getParent()).changed = true;
    }

    @Override
    public void removeChildByName(String name) {
            if (name.equals(getChildByName(name).getName())) {
                MapNode node = getChildByName(name);
                this.getChildren().remove(node);
            }

        ((Project) this.getParent()).changed = true;
    }




    public void removeChildByHashCode(int hashCode) {

        for (Iterator<MapNode> it = this.getChildren().iterator(); it.hasNext(); ) {
            MapNode mapNode = it.next();
            if (mapNode.hashCode() == hashCode) {
                elementToDelete = (Element) mapNode;
                it.remove();
                notifySubscribers(NotificationType.DELETE);
            }
        }

        ((Project) this.getParent()).changed = true;

    }
}
