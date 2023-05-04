package dsw.rudok.app.repository.implementation;

import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MyRectangle implements IPublisher {


    List<ISubscriber> subscribers;


    Point sourcePoint = new Point(0 , 0);
    int width = 0;
    int height = 0 ;



    MyRectangle(){

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



}
