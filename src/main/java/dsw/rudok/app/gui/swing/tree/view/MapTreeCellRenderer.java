package dsw.rudok.app.gui.swing.tree.view;

import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.repository.implementation.*;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

@NoArgsConstructor
public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

            super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
            URL imageURL = null;


            if (((MapTreeItem)value).getMapNode() instanceof ProjectExplorer) {
                imageURL = getClass().getResource("/images/project_explorer.png");
            }
            else if (((MapTreeItem)value).getMapNode() instanceof Project) {
                imageURL = getClass().getResource("/images/project.png");
            }
            else if(((MapTreeItem)value).getMapNode() instanceof Map) {
                imageURL = getClass().getResource("/images/map.png");
            }
            else if(((MapTreeItem)value).getMapNode() instanceof Link){
                imageURL = getClass().getResource("/images/icon/line.png");
            }
            else if(((MapTreeItem)value).getMapNode() instanceof Concept){
                imageURL = getClass().getResource("/images/icon/topic.png");
            }


            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);




            return this;

        }

}


