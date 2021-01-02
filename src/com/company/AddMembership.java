package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class AddMembership{
    private JPanel addMembershipPanel = new JPanel();

    private JLabel startDateTitle;
    private JLabel isPaidTitle;
    private JLabel membershipTypeTitle;
    private JLabel startDateValue;
    private JButton addButton;
    private JComboBox selectStudentCmb;
    private JCheckBox isPaidCheckBox;
    String[] memberships = {"12 ay", "6 ay"};
    private JComboBox selectTypeCmb;
    private JLabel dateFormat;
    private JPanel membershipPanel;

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
