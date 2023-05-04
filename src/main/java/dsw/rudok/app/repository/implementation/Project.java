package dsw.rudok.app.repository.implementation;



import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends MapNodeComposite  {

    public String filePath;
    public boolean changed = true;

    public transient Map mapToAdd;
    public transient Map mapToDelete;

    public String authorName = "";

    public transient  Project currentOpenedProject = null;

    public transient  int counter = 0;


    public Project(String name, MapNode parent) {
        super(name, parent);
    }


    @Override
    public void addChild(MapNode child) {

        if (child != null && child instanceof Map) {
            mapToAdd = (Map) child;
            if (!this.getChildren().contains(mapToAdd)) {
                this.getChildren().add(mapToAdd);
                notifySubscribers(NotificationType.ADD);
            }
        }

        changed = true;
    }

    @Override
    public void removeChildren() {
     this.getChildren().clear();

     changed = true;
    }

    @Override
    public void removeChildByName(String name) {

            if (name.equals(getChildByName(name).getName())) {
                MapNode node = getChildByName(name);
                this.getChildren().remove(node);
                mapToDelete = (Map) node;
                notifySubscribers(NotificationType.DELETE);
            }

            changed = true;
    }


    public String getCounter() {
        counter++;
        return String.valueOf(counter);
    }

    public void updateProjectViewOnAuthorNameSet(String name) {
        this.authorName = name;
        notifySubscribers(NotificationType.AUTHOR_ADDED_OR_CHANGED);
        changed = true;
    }


}
