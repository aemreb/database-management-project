package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddStudent extends JFrame {

    private JPanel panelMain;
    private JTextField isimField;
    private JTextField soyisimField;
    private JTextField tcField;
    private JTextField yasField;
    private JTextField telefonField;
    private JTextField emergencyField;
    private JTextField emailField;
    private JTextField adresField;
    private JLabel isimTitle;
    private JLabel soyisimTitle;
    private JLabel tcTitle;
    private JLabel yasTitle;
    private JLabel telefonTitle;
    private JLabel emergencyTitle;
    private JLabel emailTitle;
    private JLabel adresTitle;
    private JButton gonderButton;
    public JFrame frame = new JFrame();
    public AddStudent(){

        gonderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
                frame.setVisible(false);
                frame.dispose();
                //???

            }
        });
    }

    private void close(){
        WindowEvent windowEventClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowEventClosing);
    }

    public void load(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new AddStudent().panelMain);
        frame.pack();
        frame.setVisible(true);


    }


}
