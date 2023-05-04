package dsw.rudok.app.gui.swing.listeners.mouse;

import dsw.rudok.app.gui.swing.view.dialogs.AddAuthorDialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

    AddAuthorDialog addAuthorDialog;

    public MyMouseListener(AddAuthorDialog addAuthorDialog) {
        this.addAuthorDialog  = addAuthorDialog;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      addAuthorDialog.getProject().updateProjectViewOnAuthorNameSet(addAuthorDialog.getField().getText());
      addAuthorDialog.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
