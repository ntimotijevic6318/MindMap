package dsw.rudok.app.gui.swing.tree;


import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.tree.controller.MapTreeSelectionListener;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.tree.model.MapTreeModel;
import dsw.rudok.app.gui.swing.tree.view.MapTreeView;
import dsw.rudok.app.gui.swing.view.*;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.Map;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

@Getter
@Setter

public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private MapTreeModel treeModel;
    ProjectExplorerView projectExplorerView;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new MapTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    //dodavanje dece
    @Override
    public void addChild(MapNode child, MapTreeItem parent) {


        if (parent.getMapNode() instanceof ProjectExplorer) {
            projectExplorerView = ProjectExplorerView.getInstance();
            projectExplorerView.setPublisher(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());
        }


        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        parent.add(new MapTreeItem(child));

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }


    @Override
    public void deleteNode(MapTreeItem selected) {

        if (selected.getMapNode() instanceof MapNodeComposite) {
            ((MapNodeComposite) selected.getMapNode()).removeChildren();
            ((MapNodeComposite) ((selected.getMapNode().getParent()))).removeChildByName(selected.getMapNode().getName());
        } else if (selected.getMapNode() instanceof MapNode) {
            ((Map) selected.getMapNode().getParent()).removeChildByHashCode(selected.getMapNode().hashCode());
        }


        selected.removeFromParent();
        SwingUtilities.updateComponentTreeUI(treeView);

        //Disable buttons because nothing is selected!
        MainFrame.getInstance().getActionManager().getDeleteAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getAddAuthorAction().setEnabled(false);
    }

    @Override
    public void loadProject(Project project) {
        //Dohvatamo root
        MapNodeComposite mapNode = (MapNodeComposite) treeModel.getRoot().getMapNode();

        //setujemo da je parent root
        project.setParent(mapNode);

        addChild(project , treeModel.getRoot());

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);


        ProjectView projectView = null;

        for(ProjectView pw : ProjectExplorerView.getInstance().getProjectViewList()){
            if(pw .getProject().hashCode() == project.hashCode()){
                projectView = pw;
            }
        }

        ApplicationFramework.getInstance().getSerializer().addMaps(project);
        ApplicationFramework.getInstance().getSerializer().addElements(projectView);

        ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.PROJECT_SUCCESIFULL_LOADED);
    }



    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }


    //Metode vezane za stablo
    public MapTreeItem getParent() {

        JTabbedPane jtb = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane();
        int index = jtb.getSelectedIndex();

        MapTreeItem rootExplorer = (MapTreeItem) MainFrame.getInstance().getProjectExplorer().getModel().getRoot();
        MapTreeItem project = null;
        MapTreeItem m = null;


        for (int i = 0; i < rootExplorer.getChildCount(); i++) {
            if (rootExplorer.getChildAt(i).toString().equals(ProjectExplorerView.getInstance().getProjectViewToShow().getProject().getName())) {
                project = (MapTreeItem) rootExplorer.getChildAt(i);
            }
        }


        for (int i = 0; i < project.getChildCount(); i++) {
            if (project.getChildAt(i).toString().equals( ((MapView)jtb.getComponentAt(index)).getMap().getName()) ) {
                m = (MapTreeItem) project.getChildAt(i);
            }
        }


        return m;

    }

    public MapTreeItem getLastAddedComponent() {

        MapTreeItem rootExplorer = (MapTreeItem) MainFrame.getInstance().getProjectExplorer().getModel().getRoot();
        MapTreeItem project = null;
        MapTreeItem m = null;


        for (int i = 0; i < rootExplorer.getChildCount(); i++) {
            if (rootExplorer.getChildAt(i).toString().equals(ProjectExplorerView.getInstance().getProjectViewToShow().getProject().getName())) {
                project = (MapTreeItem) rootExplorer.getChildAt(i);
            }
        }

        JTabbedPane jtb = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane();
        int index = jtb.getSelectedIndex();

        for (int i = 0; i < project.getChildCount(); i++) {
            if (project.getChildAt(i).toString().equals(((MapView) jtb.getComponentAt(index)).getMap().getName())) {
                {
                    m = (MapTreeItem) project.getChildAt(i);
                }
            }
        }
        return (MapTreeItem) m.getLastChild();

    }

    public MapTreeItem getChild(int hashCode, MapTreeItem parent) {

        for(int i = 0 ; i < parent.getChildCount() ; i++){

            if(((MapTreeItem)parent.getChildAt(i)).getMapNode().hashCode() == hashCode) {
                return (MapTreeItem) parent.getChildAt(i);
            }
        }

        return null;
    }

    public MapTreeItem findProjectMapTree(Project project) {
       MapTreeItem root = treeModel.getRoot();
       for(int i= 0 ; i   < root.getChildCount() ; i++){
           if(root.getChildAt(i).toString().equals(project.getName())){
              return (MapTreeItem) root.getChildAt(i);
           }
       }
       return null ;
    }

    public MapTreeItem findMapTree(MapView mapV , Project project) {

        MapTreeItem p = findProjectMapTree(project);

        for(int i=0 ; i<p.getChildCount(); i++){
            if(p.getChildAt(i).toString().equals(mapV.getMap().getName())){
                return (MapTreeItem) p.getChildAt(i);
            }
        }
      return null;
    }

    public void deleteMapTreeItem(MapTreeItem parent , MapNode child){

        for(int i = 0 ; i < parent.getChildCount() ; i++){
            if(((MapTreeItem)parent.getChildAt(i)).getMapNode().hashCode() == child.hashCode()){
                parent.remove(i);
            }
        }

        SwingUtilities.updateComponentTreeUI(treeView);

    }

}

