package dsw.rudok.app.repository.composite;


import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode implements IPublisher {



    protected  List<ISubscriber> subscribers;
    List<MapNode> children;

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    public MapNode getChildByName(String name) {
        for (MapNode child: this.getChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }


    @Override
    public void removeSubscriber(ISubscriber sub) {
        // TODO Auto-generated method stub
        if (sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        // TODO Auto-generated method stub
        if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for (ISubscriber listener : subscribers) {
            listener.update(notification);
        }
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        // TODO Auto-generated method stub
        if (sub == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);

    }









    public abstract void addChild(MapNode child);
    public abstract void removeChildren();
    public abstract void removeChildByName(String name);

}
