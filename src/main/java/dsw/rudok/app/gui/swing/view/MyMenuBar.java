package dsw.rudok.app.gui.swing.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {



    public MyMenuBar() {

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        this.add(fileMenu);

    }


}
