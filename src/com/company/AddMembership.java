package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AddMembership{
    private JPanel addMembershipPanel = new JPanel();

    private JLabel startDateTitle;
    private JLabel isPaidTitle;
    private JLabel membershipTypeTitle;
    private JButton addButton;
    private JComboBox selectStudentCmb;
    private JCheckBox isPaidCheckBox;
    String[] memberships = {"12 Ay", "6 Ay"};
    private JComboBox selectTypeCmb;
    private JLabel dateFormat;
    private JPanel membershipPanel;
    private JTextField startDateField;
    private JLabel isPaidLabel;

    public JFrame frame = new JFrame();
    private Membership mem = new Membership();
    private Student std = new Student();

    public AddMembership(){

        FillCombos(selectStudentCmb, std.GetAllStudent());
        FillCombos(selectTypeCmb, memberships);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                String selectedStd;
                selectedStd = selectStudentCmb.getSelectedItem().toString();
                String[] str = selectedStd.split(" ");
                std.LoadWithStdNum(Integer.parseInt(str[0]));
                mem.setStudentId(std.getRecordId());
                Date date= null;
                date = Timestamp.valueOf(startDateField.getText().toString() + " " + LocalTime.now());
                mem.setStartDate(date);
                mem.setPaid(isPaidCheckBox.isSelected());
                if(isPaidCheckBox.isSelected())
                    isPaidLabel.setText("Üyelik Aktif Edildi!");
                String selectedType;
                selectedType = selectTypeCmb.getSelectedItem().toString();
                if(selectedType.compareTo("12 Ay")==0){
                    mem.setMembershipTypeId(20);
                }else{
                    mem.setMembershipTypeId(10);
                }
                if(mem.Insert())
                    isPaidLabel.setText("Kayıt Eklendi!");
                else
                    isPaidLabel.setText("Kayıt Eklenemedi!");
            }

        });

    }

        public void load(){
            frame.setPreferredSize(new Dimension(500, 500));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //frame.getContentPane().add(new AddMembership().addMembershipPanel);
            frame.getContentPane().add(membershipPanel);
            frame.pack();
            frame.setVisible(true);


        }

    private void FillCombos(JComboBox combo, String[] titles) {
        for(String str : titles) {
            combo.addItem(str);
        }
    }
}
