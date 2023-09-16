package dsw.rudok.app.gui.swing.view;


import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.core.errhandler.Message;
import dsw.rudok.app.gui.swing.controller.ActionManager;
import dsw.rudok.app.gui.swing.listeners.window.MyWindowListener;
import dsw.rudok.app.gui.swing.tree.MapTreeImplementation;
import dsw.rudok.app.gui.swing.tree.controller.TreeMouseListener;
import dsw.rudok.app.gui.swing.tree.view.MapTreeCellRenderer;
import dsw.rudok.app.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {

    //Singleton
    private static MainFrame instance;


    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MapTreeImplementation mapTree;
    private ProjectView projectView;
    private JSplitPane jSplitPane ;
    public ProjectExplorerView projectExplorerView;
    private JTree projectExplorer;

    private MainFrame(){

    }

    private void initialise() {
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        initialiseGUI();
    }

    private void initialiseGUI() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gerumap");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new Toolbar();
        add(toolBar, BorderLayout.NORTH);

        projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());
        projectExplorer.addMouseListener(new TreeMouseListener());


        JScrollPane scroll=new JScrollPane(projectExplorer);
        projectExplorer.setCellRenderer(new MapTreeCellRenderer());


        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                MainFrame.getInstance().revalidate();
                MainFrame.getInstance().repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        scroll.setMinimumSize(new Dimension(200,150));
        ProjectExplorerView projectExplorerView = ProjectExplorerView.getInstance();

        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, ProjectExplorerView.getInstance());
        getContentPane().add(jSplitPane,BorderLayout.CENTER);
        jSplitPane.setDividerLocation(250);
        jSplitPane.setOneTouchExpandable(true);



        this.addWindowListener(new MyWindowListener());
    }

    public static MainFrame getInstance(){
        if(instance==null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    @Override
    public void update(Object notification) {
        Message message = (Message) notification;

        switch(message.getType().name()){

            case "ERROR" :
                JOptionPane optionPane = new JOptionPane(message.getContent() , JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                break;

            case "WARNING" :
                JOptionPane.showMessageDialog(this , message.getContent() , "Warning" , 2, null); break;

            case "NOTIFICATION" :
                JOptionPane.showMessageDialog(this , message.getContent() , "Notification" , 1 , null); break;

            default:
                throw new IllegalStateException("Unexpected value: " + message.getType());
        }
    }

}
