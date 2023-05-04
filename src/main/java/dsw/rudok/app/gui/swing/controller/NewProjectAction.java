package dsw.rudok.app.gui.swing.controller;



import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.*;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.Map;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



public class NewProjectAction extends AbstractRudokAction {

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add_action.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    public void actionPerformed(ActionEvent arg0) {

        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        MapNode parent = selected.getMapNode();

        //znaci da pokusavamo da dodamo u leaf
        if(returnNodeFactory(selected) == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CANNOT_ADD_CHILD_TO_LEAF);
            return;
        }

        NodeFactory nf = returnNodeFactory(selected);
        MapNode mapNode = nf.getNode(parent);



        MainFrame.getInstance().getMapTree().addChild(mapNode , selected);
    }

    private static NodeFactory returnNodeFactory(MapTreeItem selected) {

        MapNode mapNode = selected.getMapNode();
        if(mapNode instanceof ProjectExplorer) return new ProjectFactory();
        else if(mapNode instanceof Project) return new MapFactory();
        return null;
    }
}
