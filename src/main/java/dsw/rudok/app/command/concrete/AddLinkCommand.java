package dsw.rudok.app.command.concrete;

import dsw.rudok.app.command.Command;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Link;

public class AddLinkCommand extends Command {


    Link link;

    public AddLinkCommand(Link link) {
        this.link = link;

    }

    @Override
    public void doCommand() {
        MainFrame.getInstance().getMapTree().addChild(link, MainFrame.getInstance().getMapTree().getParent());
    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getMapTree().deleteNode(MainFrame.getInstance().getMapTree().getChild(link.hashCode() , MainFrame.getInstance().getMapTree().getParent()));
    }
}
