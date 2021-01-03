package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Raporlama {
    private JButton bloodTypeButton;
    private JTextField recordIdField;
    private JButton recordIdButton;
    private JButton membershipTypeButton;
    private JLabel displayLabel;
    private JLabel bloodTypeLabel;
    private JLabel recordIdLabel;
    private JLabel membershipTypeLabel;
    private JPanel raporlamaPanel;
    private JComboBox bloodCmb;
    private JComboBox memTypeCmb;
    private JFrame frame = new JFrame();

    String[] blood_str = {"1 - A RH+", "2 - A RH-", "3 - B RH+", "4 - B RH-", "5 - AB RH+", "6 - AB RH-", "7 - 0 RH+", "8 - 0 RH-"};
    String [] memType = {"12 Ay", "6 Ay"};
    Student std = new Student();
    Employee em = new Employee();
    Membership mem = new Membership();

    public Raporlama(){

        for(String str : blood_str)
            bloodCmb.addItem(str);

        for(String str : memType)
            memTypeCmb.addItem(str);

        recordIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                em.getEmployeeInfo(recordIdField.getText().toString());
                displayLabel.setText(em.getName() + " " + em.getSurname() + " adlı çalışanın telefon numarası:  " +
                        em.getPhone() +" ve mevcut izin günü sayısı: "+ em.getOffDay());
            }
        });

        membershipTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer rs;
                rs = mem.getIncome(memTypeCmb.getSelectedItem() == "12 Ay" ? 20:10);
                displayLabel.setText(memTypeCmb.getSelectedItem().toString() + "'lık Üyeliklerin Kuruma Toplam Getirisi: "+
                        rs.toString()+"₺");

            }
        });

        bloodTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String blood = bloodCmb.getSelectedItem().toString().split(" ")[0];
                Integer avgAge = std.getAvgOfAge(blood);
                displayLabel.setText("Kan grubu " + bloodCmb.getSelectedItem().toString().split(" ")[2] +
                        " " + bloodCmb.getSelectedItem().toString().split(" ")[3]
                        + " olan öğrencilerin yaş ortalaması: " + avgAge.toString());
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
