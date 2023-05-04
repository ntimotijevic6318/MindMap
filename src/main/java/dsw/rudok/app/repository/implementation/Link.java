package dsw.rudok.app.repository.implementation;

import dsw.rudok.app.gui.swing.view.ConceptPainter;
import dsw.rudok.app.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Link extends Element {

   transient Point sourcePosition;
   transient Point destinationPosition;

    transient Point sourceMoved;
    transient Point destinationMoved;

    transient Concept from;
    transient Concept to;



    public Link(String name, MapNode parent, Color elementColor, Color strokeColor, int stroke) {
        super(name, parent, elementColor,  strokeColor , stroke);
    }

    public  void setDestinationPosition(Point destinationPoint){
        this.destinationPosition = destinationPoint;
        this.notifySubscribers(NotificationType.COORDS_CHANGED);
    }

    public void setSourcePosition(Point sourcePosition){
        this.sourcePosition = sourcePosition;
        this.notifySubscribers(NotificationType.COORDS_CHANGED);
    }


}
