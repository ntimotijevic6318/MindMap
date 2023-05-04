package dsw.rudok.app.gui.swing.tree.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.Map;
import dsw.rudok.app.repository.implementation.NotificationType;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn =null;
    private JTextField edit=null;

    public MapTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);

        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }



    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }



    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof MapTreeItem))
            return;


        if(e.getActionCommand().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NAME_CANNOT_BE_EMPTY_STRING);
            return;
        }

        for(MapNode mapNode : ((MapNodeComposite)((MapTreeItem) clickedOn).getMapNode().getParent()).getChildren()){
            if(mapNode.getName().equalsIgnoreCase(e.getActionCommand().trim())){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CANNOT_NAME_NODE_WITH_EXISTING_NAME);
            return;
            }
        }

        MapTreeItem clicked = (MapTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

        if(clicked.getMapNode() instanceof  Project) {
            ((Project) clicked.getMapNode()).notifySubscribers(NotificationType.DOUBLE_CLICK);
            ((Project) clicked.getMapNode()).changed = true;
        }
        else if(clicked.getMapNode() instanceof Map){
            ((Project) clicked.getMapNode().getParent()).notifySubscribers(NotificationType.DOUBLE_CLICK);
            ((Project) clicked.getMapNode().getParent()).changed = true;
        }

    }



}
