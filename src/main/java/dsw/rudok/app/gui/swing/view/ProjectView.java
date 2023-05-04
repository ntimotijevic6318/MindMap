package dsw.rudok.app.gui.swing.view;

import dsw.rudok.app.command.CommandManager;
import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.gui.swing.listeners.mouse.MapViewMouseListener;
import dsw.rudok.app.gui.swing.listeners.mouse.MapViewMouseMotionListener;
import dsw.rudok.app.gui.swing.listeners.mouse.MapViewMouseWheelListener;
import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.implementation.*;
import dsw.rudok.app.state.StateManager;
import dsw.rudok.app.state.concrete.TextEditor;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ProjectView extends JPanel implements ISubscriber {

    Project project;
    JLabel projectName;

    List<MapView> mapViewList;
    JTabbedPane jTabbedPane;

    JLabel authorName;

    ProjectViewToolbar projectViewToolbar;

    StateManager stateManager = new StateManager();

    ProjectView projectView = this;

    MapView previous;

    public ProjectView(Project project) {

        this.project = project;

        setLayout(new BorderLayout());


        this.projectViewToolbar = new ProjectViewToolbar();
        this.add(projectViewToolbar , BorderLayout.EAST);


        project.addSubscriber(this);

        JPanel authorAndProjectName  = new JPanel();
        authorAndProjectName.setLayout(new BorderLayout());


        projectName = new JLabel();
        authorName = new JLabel();


        authorAndProjectName.add(projectName , BorderLayout.NORTH);
        authorAndProjectName.add(authorName , BorderLayout.SOUTH);

        jTabbedPane = new CustomJTabbedPane();

        JPanel center = new JPanel(new BorderLayout());
        center.add(authorAndProjectName , BorderLayout.NORTH);

        center.add(jTabbedPane , BorderLayout.CENTER);

        this.add(center , BorderLayout.CENTER);



        jTabbedPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if(jTabbedPane.isShowing()) {

                    int index = 1;

                    previous.getSelectionModel().getElementList().clear();

                    MapView mapViewCurrent = (MapView) jTabbedPane.getComponentAt(jTabbedPane.getSelectedIndex());

                    previous = mapViewCurrent;

                    TextEditor textEditor = new TextEditor(mapViewCurrent);

                    MainFrame.getInstance().add(textEditor , BorderLayout.SOUTH , index);
                    MainFrame.getInstance().revalidate();

                    index++;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        mapViewList = new ArrayList<>();
    }

    @Override
    public void update(Object notification) {

        if (((NotificationType) notification).name() == "ADD") {
            addToMapViewList();
        }
        else if (((NotificationType) notification).name() == "DELETE") {
            removeFromMapViewList();
        }
        if(((NotificationType) notification).name() == "ADD" || ((NotificationType) notification).name() == "DELETE" || ((NotificationType) notification).name() == "DOUBLE_CLICK") {
            updateAfterListChanged();
        }
        if(((NotificationType) notification).name() == "AUTHOR_ADDED_OR_CHANGED"){
         setAuthorToProject();
        }
    }

    private void addToMapViewList() {

        SelectionModel selectionModel = new SelectionModel();

        Map mapToAdd = this.project.getMapToAdd();

        RectangleView rectangleView = new RectangleView(mapToAdd.getRectangle());

        MapView mapView = null;
        mapView = new MapView(mapToAdd , selectionModel);
        mapView.setRectangleView(rectangleView);

        mapView.addMouseListener(new MapViewMouseListener(mapView));
        mapView.addMouseMotionListener(new MapViewMouseMotionListener(mapView));
        mapView.addMouseWheelListener(new MapViewMouseWheelListener(mapView));

        this.getMapViewList().add(mapView);


        if(this.getMapViewList().size() == 1){

            int index = 1;

            MapView mapViewCurrent = mapView ;
            previous = mapViewCurrent;

            TextEditor textEditor = new TextEditor(mapViewCurrent);


            MainFrame.getInstance().add(textEditor, BorderLayout.SOUTH, index);
            MainFrame.getInstance().revalidate();

            index++;
        }


        }

        private void removeFromMapViewList(){
            Map mapToDelete = this.project.getMapToDelete();
            for (MapView mapView : this.mapViewList) {
                if (mapView.getMap().getName().equals(mapToDelete.getName())) {
                    this.mapViewList.remove(mapView);
                    break;
                }
            }
        }

        private void updateAfterListChanged(){
            this.jTabbedPane.removeAll();
            String name = this.getProject().getName();
            projectName.setText(name);

            ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                    this.getClass().getResource("/images/icon/map.png")));

            for (MapView mapView : this.mapViewList) {
                this.jTabbedPane.addTab(mapView.getMap().getName() , icon , mapView , "Does nothing");
            }

        }

    private void setAuthorToProject() {
        this.authorName.setText(this.getProject().getAuthorName());
    }

    public void startConceptState(){
        this.stateManager.setConceptState();
    }

    public void startLinkState()
    {
        this.stateManager.setLinkState();
    }

    public void startSelectionState(){
        this.stateManager.setSelectionState();
    }

    public void startRemoveState(){
       this.stateManager.setRemoveState();
    }

    public void startMoveState(){this.stateManager.setMoveState();}

    public void startZoomState() {this.stateManager.startZoomState();}



    public void mousePressed(int x, int y, MapView mapView) {
        this.stateManager.getCurrent().mousePressed(x , y, mapView);
    }

    public void mouseDragged(int x, int y, MapView mapView) {
        this.stateManager.getCurrent().mouseDragged(x , y , mapView);
    }

    public void mouseReleased(int x, int y, MapView mapView)
    {
        this.stateManager.getCurrent().mouseReleased(x , y , mapView);
    }

    public void mouseWheel(MouseWheelEvent e ,MapView mapView) {
     this.stateManager.getCurrent().mouseWell(e , mapView);
    }



}

