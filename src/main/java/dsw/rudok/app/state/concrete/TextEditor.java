package dsw.rudok.app.state.concrete;

import dsw.rudok.app.gui.swing.view.ElementView;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.gui.swing.view.MapView;
import dsw.rudok.app.repository.implementation.Concept;
import dsw.rudok.app.repository.implementation.Link;
import dsw.rudok.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;


public class TextEditor extends JLayeredPane implements ActionListener {

    MapView mapView;

    TextEditor textEditor = this;
    JTextArea textArea;

    JScrollPane scrollPane;

    JLabel fontLabel;
    JLabel strokeLabel;

    JSpinner fontSizeSpinner;
    JSpinner strokeSizeSpinner;

    JComboBox fontBox;

    Color color;

    JColorChooser colorChooser ;

    JButton elementColorButton;
    JButton strokeColorButton;
    JButton fontColor;

    public TextEditor(MapView mapView){


        this.mapView = mapView;
        setLayout(new BorderLayout());


        this.setSize((int) (MainFrame.getInstance().getWidth()/1.1), MainFrame.getInstance().getHeight());
        this.setLocation(MainFrame.getInstance().getX() + MainFrame.getInstance().getWidth() , MainFrame.getInstance().getY());


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(false);
        textArea.setFont(new Font("Arial",Font.PLAIN,20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450,450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontLabel = new JLabel("F:");
        this.fontBox = new JComboBox(fonts);
        strokeLabel =  new JLabel("S:");


        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50,25));
        fontSizeSpinner.setValue(12);


        fontSizeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                for(ElementView elementView : textEditor.mapView.getElementViewList()){

                    if(mapView.getSelectionModel().getElementList().contains(elementView.getElement())){
                        if(elementView.getElement() instanceof  Concept) {
                            ((Concept) elementView.getElement()).setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (Integer) fontSizeSpinner.getValue()));
                        }
                    }

                }

                ((Project)mapView.getMap().getParent()).changed = true;

                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));

            }
        });

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                for(ElementView elementView : textEditor.mapView.getElementViewList()){
                    if(textEditor.mapView.getSelectionModel().getElementList().contains(elementView.getElement())){
                        ((Concept)elementView.getElement()).setText(textArea.getText().trim() , textArea.getFont());
                        ((Project)mapView.getMap().getParent()).changed = true;
                    }
                }
            }
        });


        strokeSizeSpinner = new JSpinner();
        strokeSizeSpinner.setPreferredSize(new Dimension(50,25));
        strokeSizeSpinner.setValue(1);


        elementColorButton = new JButton("Fill");
        elementColorButton.addActionListener(this);


        strokeColorButton = new JButton("Stroke");
        strokeColorButton.addActionListener(this);


        fontColor = new JButton("Font");
        fontColor.addActionListener(this);


        fontBox.addActionListener(this);
        fontBox.setSelectedItem("Arial");


        textArea.setSize(new Dimension(160 , 0));


        JPanel left = new JPanel();

        left.add(fontLabel);
        left.add(fontSizeSpinner);
        left.add(strokeLabel);
        left.add(strokeSizeSpinner);
        left.add(elementColorButton);
        left.add(strokeColorButton);
        left.add(fontColor);



        this.add(left , BorderLayout.WEST);
        this.add(fontBox , BorderLayout.CENTER);
        this.add(textArea , BorderLayout.EAST);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==elementColorButton) {
             colorChooser = new JColorChooser();
             color = colorChooser.showDialog(null, "Choose a color", null);

            for(ElementView elementView : textEditor.mapView.getElementViewList()){
                if(textEditor.mapView.getSelectionModel().getElementList().contains(elementView.getElement())) {
                    elementView.getElement().setElementColor(color);
                }
            }

            ((Project)mapView.getMap().getParent()).changed = true;

            textArea.setForeground(color);
        }

        if(e.getSource() == strokeColorButton){
            colorChooser = new JColorChooser();
            color = colorChooser.showDialog(null, "Choose a stroke color", null);

            for(ElementView elementView : textEditor.mapView.getElementViewList()){
                if(textEditor.mapView.getSelectionModel().getElementList().contains(elementView.getElement())) {
                    elementView.getElement().setStrokeColor(color);
                }
            }

            ((Project)mapView.getMap().getParent()).changed = true;

            textArea.setForeground(color);
        }

        if(e.getSource() == fontColor){
            colorChooser = new JColorChooser();
            color = colorChooser.showDialog(null, "Choose a font color", null);

            for(ElementView elementView : textEditor.mapView.getElementViewList()) {
                if (textEditor.mapView.getSelectionModel().getElementList().contains(elementView.getElement())) {
                    if(elementView.getElement() instanceof Concept) {
                        ((Concept) elementView.getElement()).setFontColor(color);
                    }
                }
            }
            ((Project)mapView.getMap().getParent()).changed = true;

        }

        if(e.getSource()==fontBox) {

            textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));

            for (ElementView elementView : textEditor.mapView.getElementViewList()) {
                if (textEditor.mapView.getSelectionModel().getElementList().contains(elementView.getElement())) {
                    if(elementView.getElement() instanceof  Concept){
                        ((Concept) elementView.getElement()).setFont(new Font(textArea.getFont().getFamily() , Font.PLAIN , (Integer) fontSizeSpinner.getValue()));
                    }
                }
            }

            ((Project)mapView.getMap().getParent()).changed = true;

        }

        //Logika


                    strokeSizeSpinner.addChangeListener(new ChangeListener() {

                        @Override
                        public void stateChanged(ChangeEvent e) {
                            for(ElementView elementView : textEditor.mapView.getElementViewList()) {
                                if (textEditor.mapView.getSelectionModel().getElementList().contains(elementView.getElement())) {
                                    elementView.getElement().setStroke((Integer) strokeSizeSpinner.getValue());
                                }
                            }

                            ((Project)mapView.getMap().getParent()).changed = true;

                        }
                    });
            }
        }
