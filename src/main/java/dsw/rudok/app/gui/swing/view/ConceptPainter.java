package dsw.rudok.app.gui.swing.view;

import dsw.rudok.app.repository.implementation.Concept;
import dsw.rudok.app.repository.implementation.Element;
import lombok.Getter;
import lombok.Setter;


import java.awt.*;

import java.awt.geom.Ellipse2D;



@Getter
@Setter
public class ConceptPainter extends ElementView {

    Concept concept;

    public ConceptPainter(Element element){
        super(element);
        this.concept = (Concept) element;
    }

    @Override
    public void paint(Graphics2D g) {

        shape = new Ellipse2D.Double(concept.getPosition().x , concept.getPosition().y , concept.getSize().width + 12 , concept.getSize().height + 12);

        g.setColor(concept.getElementColor());
        g.setFont(concept.getFont());

        g.fill(getShape());


        g.setColor(concept.getTextColor());
        g.drawString(concept.getText() , concept.getPosition().x + 6, (int) (concept.getPosition().y + shape.getBounds().getHeight()/1.5));

        g.setColor(concept.getStrokeColor());
        g.setStroke(new BasicStroke(concept.getStroke()));

        g.draw(getShape());
    }


    @Override
    public boolean elementAt(ElementView elementView, Point pos)
    {
        return elementView.getShape().contains(pos.x , pos.y);
    }

    @Override
    public Point centerPointOfShape() {
        return new Point((int) this.shape.getBounds().getCenterX(), (int) this.shape.getBounds().getCenterY());
    }

    public Concept getConcept(){
        return this.concept ;
    }


}

