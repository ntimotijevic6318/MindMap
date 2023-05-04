package dsw.rudok.app.gui.swing.tree.controller;

import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;
import dsw.rudok.app.gui.swing.view.ProjectView;
import dsw.rudok.app.repository.implementation.Map;
import dsw.rudok.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;



public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        MainFrame.getInstance().getActionManager().getDeleteAction().setEnabled(true);
        MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(true);

        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Map){
            MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
            Map map = (Map) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();

            for(ProjectView projectView : ProjectExplorerView.getInstance().projectViewList){
                if(projectView.getProject().getName().equals(map.getParent().getName())){
                   int index = findTabByName(map.getName() , projectView.getJTabbedPane());
                   projectView.getJTabbedPane().setSelectedIndex(index);
                }
            }
        }

        //za autora & save & save as instanceof Project
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project){
            MainFrame.getInstance().getActionManager().getAddAuthorAction().setEnabled(true);
            MainFrame.getInstance().getActionManager().getSaveAction().setEnabled(true);
            MainFrame.getInstance().getActionManager().getSaveAsAction().setEnabled(true);
        }else{
            MainFrame.getInstance().getActionManager().getAddAuthorAction().setEnabled(false);
            MainFrame.getInstance().getActionManager().getSaveAction().setEnabled(false);
            MainFrame.getInstance().getActionManager().getSaveAsAction().setEnabled(false);
        }

       //instanceof Map
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Map && ProjectExplorerView.getInstance().getProjectViewToShow() != null && ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getTabCount() > 0) {
                MainFrame.getInstance().getActionManager().getExportAction().setEnabled(true);
                MainFrame.getInstance().getActionManager().getTemplateSaveAsAction().setEnabled(true);
                MainFrame.getInstance().getActionManager().getLoadTemplate().setEnabled(true);
            } else {
                MainFrame.getInstance().getActionManager().getExportAction().setEnabled(false);
                MainFrame.getInstance().getActionManager().getTemplateSaveAsAction().setEnabled(false);
                MainFrame.getInstance().getActionManager().getLoadTemplate().setEnabled(false);
            }
        }


    private int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }

        return -1;
    }
}


