package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindow {
    private JTextField yeniDeğeriGirinTextField;
    private JButton guncelleButton;
    private JPanel dialogPanel;

    private JFrame frame = new JFrame();

    public DialogWindow(){

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(dialogPanel);
        frame.pack();
        frame.setVisible(true);

        guncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();


            }
        });


    }




}
