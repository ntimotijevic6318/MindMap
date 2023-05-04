package dsw.rudok.app.gui.swing.view.dialogs;

import dsw.rudok.app.gui.swing.listeners.mouse.MyMouseListener;
import dsw.rudok.app.repository.implementation.Project;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;


@Getter
@Setter

public class AddAuthorDialog extends AbstractDialog {

    Project project;
    JTextField field;

    public AddAuthorDialog(Frame parent, String message, boolean modal, Project mapNode) {
        super(parent, message, modal);
        this.project = mapNode;
    }

    @Override
    public void initializeDialog() {

        setSize(350,80);
        setLocationRelativeTo(getParent());

        JPanel author = new JPanel();
        author.setLayout(new BorderLayout());
        this.add(author , BorderLayout.CENTER);

        JPanel panelNorth = new JPanel();
        panelNorth.setSize(new Dimension(0 , 40));
        author.add(panelNorth , BorderLayout.NORTH);

        JPanel panelSouth = new JPanel();
        panelSouth.setSize(new Dimension(0 , 40));
        author.add(panelSouth , BorderLayout.SOUTH);

        JLabel authorName = new JLabel();
        //authorName.setText(project.getAuthorName());
        author.add(authorName , BorderLayout.WEST);

        field = new JTextField();
        field.setFont(field.getFont().deriveFont(Font.BOLD, 14f));
        author.add(field , BorderLayout.CENTER);


        Button save = new Button("Sačuvaj");
        save.setSize(new Dimension(40 , 0));

        save.addMouseListener(new MyMouseListener(this));

        author.add(save , BorderLayout.EAST);
    }
}
