package dsw.rudok.app.gui.swing.controller;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.errhandler.EventType;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.gui.swing.view.ProjectExplorerView;
import dsw.rudok.app.gui.swing.view.ProjectView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExportAction extends AbstractRudokAction {

    public ExportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/export_action.png"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int index = ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getSelectedIndex();
        MapView mapView = (MapView) ProjectExplorerView.getInstance().getProjectViewToShow().getJTabbedPane().getComponentAt(index);



        BufferedImage bImg = new BufferedImage(mapView.getWidth(), mapView.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        mapView.paintAll(cg);

        try {
            if (ImageIO.write(bImg, "png", new File("src/main/resources/templates" , mapView.getMap().getName() + ".png")))
            {
                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.PNG_CREATED) ;
            }
        } catch (IOException ioException) {
            //    TODO Auto-generated catch block
            ioException.printStackTrace();
        }
    }


}



