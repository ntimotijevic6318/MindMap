package dsw.rudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;


public class ProjectViewToolbar extends JToolBar {

     public ProjectViewToolbar(){
         super(VERTICAL);

         setFloatable(false);

         add(add(MainFrame.getInstance().getActionManager().getConceptAction()));
         add(add (MainFrame.getInstance().getActionManager().getLinkAction()));
         add(add (MainFrame.getInstance().getActionManager().getSelectionAction()));
         add(add (MainFrame.getInstance().getActionManager().getRemoveAction()));
         add(add (MainFrame.getInstance().getActionManager().getMoveAction()));

     }
}
