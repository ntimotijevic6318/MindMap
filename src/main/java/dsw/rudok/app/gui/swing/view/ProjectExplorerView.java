package dsw.rudok.app.gui.swing.view;

import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.implementation.NotificationType;
import dsw.rudok.app.repository.implementation.Project;
import dsw.rudok.app.repository.implementation.ProjectExplorer;
import dsw.rudok.app.state.concrete.TextEditor;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectExplorerView extends JPanel  implements  ISubscriber {

    private ProjectExplorer projectExplorer;
    private static ProjectExplorerView instance = null;

    public List<ProjectView> projectViewList;
    ProjectView projectViewToShow = null;

    private ProjectExplorerView() {
        projectViewList = new ArrayList<ProjectView>();
        setLayout(new BorderLayout());
    }

    @Override
    public void update(Object notification) {


        if(((NotificationType) notification).name() == "ADD"){
            addToProjectViewList();
        }

        else if(((NotificationType) notification).name() == "DELETE"){
            removeFromProjectViewList();
        }

        else if(((NotificationType) notification).name() == "DOUBLE_CLICK")
        {
            showCurrentOpenedProject();
            if(ProjectExplorerView.getInstance().projectViewToShow.getJTabbedPane().getTabCount() > 0){
                MainFrame.getInstance().getActionManager().getExportAction().setEnabled(true);
                MainFrame.getInstance().getActionManager().getTemplateSaveAsAction().setEnabled(true);
                MainFrame.getInstance().getActionManager().getLoadTemplate().setEnabled(true);
            }
        }
    }

    public static ProjectExplorerView getInstance(){
        if(instance == null){
            instance = new ProjectExplorerView();
        }
        return instance;
    }

    public void setPublisher(ProjectExplorer mapNode) {
        mapNode.addSubscriber(this);
        projectExplorer = mapNode;
    }

    private void addToProjectViewList(){
        Project projectToAdd = projectExplorer.getProjectToAdd();
        ProjectView projectView = new ProjectView(projectToAdd);
        this.getProjectViewList().add(projectView);
    }

    private void removeFromProjectViewList(){
        Project projectToDelete = this.projectExplorer.getProjectToDelete();
        for(ProjectView projectView : this.projectViewList){
            if(projectView.getProject().getName().equals(projectToDelete.getName())){
                this.projectViewList.remove(projectView);
                break;
            }
        }

        if(this.getProjectViewToShow() != null){
            if(projectToDelete == this.getProjectViewToShow().getProject()){
                this.removeAll();
                this.updateUI();
            }
        }
    }

    private void showCurrentOpenedProject(){

        Project project = projectExplorer.getProjectToShow();


        for (ProjectView projectView : projectViewList) {
            if (projectView.getProject().hashCode() == project.hashCode()) {
                this.projectViewToShow = projectView;
            }
        }

        this.removeAll();
        this.add(projectViewToShow, BorderLayout.CENTER);
        this.updateUI();


    }

}
