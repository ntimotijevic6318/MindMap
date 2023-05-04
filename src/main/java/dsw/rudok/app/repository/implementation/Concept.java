package dsw.rudok.app.repository.implementation;

import dsw.rudok.app.repository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Concept extends Element {

   transient Dimension size;
   transient Point position;

   transient Font font;
   transient String text;

   transient Color textColor;
   transient Color strokeColor;

    transient  List<Link> linksTo;
    transient  List<Link> linksFrom;


    public Concept(String name , MapNode parent , Color elementColor , Color strokeColor , int stroke , Point position , Dimension size, Color textColor){
        super(name , parent , elementColor , strokeColor, stroke);

        this.size = size;
        this.position = position;

        this.textColor = textColor;
        this.strokeColor = strokeColor;

        linksFrom = new ArrayList<>();
        linksTo = new ArrayList<>();
    }

    public void setPosition(Point point){
        this.position = point;
        this.notifySubscribers(NotificationType.COORDS_CHANGED);
    }

    public void addLinkTo(Link link)
    {
        this.linksTo.add(link);
    }

    public void addLinkFrom(Link link){
       this.linksFrom.add(link);
    }

    public void setFont(Font font){

        this.font = font;

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);

        int textWidth = (int)(this.font.getStringBounds(text , frc).getWidth());
        int textHeight = (int)(this.font.getStringBounds(text, frc).getHeight());

        this.size = new Dimension(textWidth , textHeight);

        notifySubscribers(NotificationType.TEXT_CHANGED);
    }

    public void setFontColor(Color color) {
     this.textColor = color;
     notifySubscribers(NotificationType.COLOR_CHANGED);
    }

    public void setText(String text , Font font){

        this.font = font;

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);

        int textWidth = (int)(this.font.getStringBounds(text , frc).getWidth());
        int textHeight = (int)(this.font.getStringBounds(text, frc).getHeight());

        this.size = new Dimension(textWidth , textHeight);
        notifySubscribers(NotificationType.TEXT_CHANGED);

        this.text = text;
        notifySubscribers(NotificationType.KEY_PRESSED);
    }


    public void removeFromTo(Link link) {
        this.linksTo.remove(link);
    }

    public void removeFrom(Link link) {
        this.linksFrom.remove(link);

    }

    public Point centerOfFrom() {
        return new Point(this.getPosition().x + this.getSize().width , this.getPosition().y + this.getSize().height) ;
    }

    public Point centerOfTo() {
        return new Point(this.getPosition().x + this.getSize().width , this.getPosition().y + this.getSize().height);
    }
}
