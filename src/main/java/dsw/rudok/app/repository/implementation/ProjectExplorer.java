package dsw.rudok.app.repository.implementation;


import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;
import dsw.rudok.app.observer.IPublisher;
import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectExplorer extends MapNodeComposite   {

    Project projectToAdd;
    Project projectToDelete;
    Project projectToShow;

    List<ISubscriber> subscribers;

    String nameOfProject;
    int counter =  0;


    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {

            if (child != null && child instanceof Project) {
                projectToAdd = (Project) child;
                if (!this.getChildren().contains(projectToAdd)) {
                    this.getChildren().add(projectToAdd);
                    notifySubscribers(NotificationType.ADD);
                }
            }
        }


    @Override
    public void removeChildren() {
        this.getChildren().clear();
    }

    @Override
    public void removeChildByName(String name) {
            if (name.equals(getChildByName(name).getName())) {
                MapNode node = getChildByName(name);
                this.getChildren().remove(node);
                projectToDelete = (Project) node;
                notifySubscribers(NotificationType.DELETE);
            }
        }

    public void updateProjectExplorerViewOnProjectDoubleClick() {
       MapTreeItem selected =  MainFrame.getInstance().getMapTree().getSelectedNode();
       projectToShow = (Project) selected.getMapNode();
       nameOfProject = selected.getMapNode().getName();
       notifySubscribers(NotificationType.DOUBLE_CLICK);
     }

    public String getCounter() {
        counter++;
        return String.valueOf(counter);
    }
}
