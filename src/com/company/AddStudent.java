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
    private JLabel messageLabel;

    public JFrame frame = new JFrame();

    private  Student std = new Student();

    public AddStudent(){

        gonderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                std.setName(isimField.getText());
                std.setSurname(soyisimField.getText());
                std.setIdentityNum(tcField.getText());
                std.setAge(Integer.parseInt(yasField.getText()));
                std.setPhone(telefonField.getText());
                std.setEmergencyPhone(emergencyField.getText());
                std.setEmail(emailField.getText());
                std.setAddress(adresField.getText());

                if(std.Insert()){
                    messageLabel.setText(std.getStudentNum() + " numaralı öğrenci eklendi.");
                }else{
                    messageLabel.setText("Öğrenci eklenemedi.");
                }



            }
        });
    }


    public void load(){
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new AddStudent().panelMain);
        frame.pack();
        frame.setVisible(true);


    }


}