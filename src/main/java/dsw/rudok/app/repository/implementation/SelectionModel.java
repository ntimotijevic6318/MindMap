package dsw.rudok.app.repository.implementation;

import dsw.rudok.app.gui.swing.controller.AbstractRudokAction;
import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SelectionModel implements IPublisher {

    List<Element> elementList = new ArrayList<>();
    List<ISubscriber> subscribers;



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

    public void addToElementList(Element element) {
        this.elementList.add(element);
        notifySubscribers(NotificationType.ADD_TO_SELECT_LIST);
    }

    public void clearList() {
        this.elementList.clear();
        notifySubscribers(NotificationType.CLEAR_ALL_FROM_SELECT_LIST);
    }


}
