package dsw.rudok.app.repository.implementation;

import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Getter
public class Element extends MapNode implements IPublisher {

      Color elementColor;
      Color strokeColor;

    transient  int stroke;


    transient  List<ISubscriber> subscribers;

    public Element(String name, MapNode parent, Color elementColor , Color strokeColor , int stroke)
    {
        super(name, parent);
        this.elementColor = elementColor;
        this.strokeColor = strokeColor;
        this.stroke = stroke;
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

        System.out.println(subscribers);

    }

    public void setElementColor(Color color){
       this.elementColor  = color;
       notifySubscribers(NotificationType.COLOR_CHANGED);
    }


    public void setStrokeColor(Color color) {
    this.strokeColor = color;
    notifySubscribers(NotificationType.COORDS_CHANGED);
    }


    public void setStroke(int stroke){
     this.stroke = Math.abs(stroke);
     notifySubscribers(NotificationType.STROKE_CHANGED);
    }


}
