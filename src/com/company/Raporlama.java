package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Raporlama {
    private JButton bloodTypeButton;
    private JTextField bloodTypeField;
    private JTextField recordIdField;
    private JButton recordIdButton;
    private JTextField membershipTypeField;
    private JButton membershipTypeButton;
    private JLabel displayLabel;
    private JLabel bloodTypeLabel;
    private JLabel recordIdLabel;
    private JLabel membershipTypeLabel;
    private JPanel raporlamaPanel;
    private JFrame frame = new JFrame();


    public Raporlama(){

        recordIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        membershipTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        bloodTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void load(){
        frame.setPreferredSize(new Dimension(700, 700));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(raporlamaPanel);
        frame.pack();
        frame.setVisible(true);


    }
}
