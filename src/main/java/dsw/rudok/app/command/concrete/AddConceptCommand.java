package dsw.rudok.app.command.concrete;
import dsw.rudok.app.command.Command;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Concept;


public class AddConceptCommand extends Command {

    Concept concept;


    public AddConceptCommand(Concept concept) {
        this.concept = concept;
    }

    @Override
    public void doCommand() {
        MainFrame.getInstance().getMapTree().addChild(concept , MainFrame.getInstance().getMapTree().getParent());
    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getMapTree().deleteNode(MainFrame.getInstance().getMapTree().getChild(concept.hashCode() , MainFrame.getInstance().getMapTree().getParent()));
    }
}
