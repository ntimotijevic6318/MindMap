package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.implementation.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadTemplate extends  AbstractRudokAction {


    JPanel contentPane ;
    public LoadTemplate() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
         putValue(SMALL_ICON, loadIcon("/images/load_template_action.png"));
        putValue(NAME, "Load template");
        putValue(SHORT_DESCRIPTION, "Load template");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {



//        int code=JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li zelite da izaberete sablon iz galerije?","?",JOptionPane.YES_NO_OPTION);
//
//        if (code==JOptionPane.YES_OPTION){
//
//            JFrame galery = new JFrame();
//            galery.setVisible(true);
//            galery.setSize(500 , 500);
//            galery.setLocationRelativeTo(null);
//            galery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            galery.setTitle("Gallery");
//
//            contentPane = new JPanel(new CardLayout(20, 20));
//
//            String rootFolder = "./src/main/resources/templates";
//            String rootFolderTemplatesJson = "./src/main/resources/templates_on";
//            String searchFor = "png";
//            walkDirTreeAndSearch(rootFolder, searchFor);
//
//
//            JPanel buttonPanel = new JPanel();
//            final JButton previousButton = new JButton("PREVIOUS");
//            previousButton.setBackground(Color.BLACK);
//            previousButton.setForeground(Color.WHITE);
//            final JButton nextButton = new JButton("NEXT");
//            nextButton.setBackground(Color.RED);
//            nextButton.setForeground(Color.WHITE);
//            buttonPanel.add(previousButton);
//            buttonPanel.add(nextButton);
//
//            JButton save = new JButton("Apply Template");
//
//            /* Adding the ActionListeners to the JButton,
//             * so that the user can see the next Card or
//             * come back to the previous Card, as desired.
//             */
//            previousButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent ae) {
//                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
//                    cardLayout.previous(contentPane);
//                }
//            });
//            nextButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent ae) {
//                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
//                    cardLayout.next(contentPane);
//                }
//            });
//
//            save.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                walkDirTreeAndSearchJ(rootFolderTemplatesJson , "json" );
//                galery.dispose();
//                }
//            });
//
//            // Adding the contentPane (JPanel) and buttonPanel to JFrame.
//
//            galery.add(contentPane , BorderLayout.CENTER);
//            galery.add(buttonPanel, BorderLayout.PAGE_END);
//            galery.add(save , BorderLayout.PAGE_START);
//
//            //MainFrame.getInstance().revalidate();
//            // this.revalidate();
//
//
//        }

            JFileChooser jfc = new JFileChooser();

            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = jfc.getSelectedFile();


                    Map map = ApplicationFramework.getInstance().getSerializer().loadMap(file);
                    int index = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getSelectedIndex();
                    MapView mapView = (MapView) ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getComponentAt(index);


                    for(MapNode mapNode : map.getChildren()){
                        mapView.getMap().addChild(mapNode);
                    }


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }


    private void walkDirTreeAndSearchJ(String rootFolderTemplatesJson, String json) {
        try {
            Files.walk(Paths.get(rootFolderTemplatesJson), FileVisitOption.FOLLOW_LINKS).filter(t -> {
                return t.toString().contains(json);
            }).forEach(path -> {


                String name = String.valueOf(Paths.get(String.valueOf(path)).getFileName());
                System.out.println(name);

                File file = new File("./src/main/resources/templates_on/" + name);
                Map map = ApplicationFramework.getInstance().getSerializer().loadMap(file);
                int index = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getSelectedIndex();
                MapView mapView = (MapView) ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getComponentAt(index);


                for(MapNode mapNode : map.getChildren()){
                    mapView.getMap().addChild(mapNode);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void walkDirTreeAndSearch(String rootFolder, String searchFor) {

        try {
            Files.walk(Paths.get(rootFolder), FileVisitOption.FOLLOW_LINKS).filter(t -> {
                return t.toString().contains(searchFor);
            }).forEach(path -> {

                String name = String.valueOf(Paths.get(String.valueOf(path)).getFileName());

                BufferedImage myPicture = null;
                try {
                    myPicture = ImageIO.read(new FileInputStream("D:\\InteliJWork\\rm\\src\\main\\resources\\templates\\" + name));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                contentPane.add(picLabel);

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }



