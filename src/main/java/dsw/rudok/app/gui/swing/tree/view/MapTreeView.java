package dsw.rudok.app.gui.swing.tree.view;

import dsw.rudok.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.rudok.app.gui.swing.tree.controller.MapTreeSelectionListener;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {


    public MapTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        MapTreeCellRenderer ruTreeCellRenderer = new MapTreeCellRenderer();
        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }

}
