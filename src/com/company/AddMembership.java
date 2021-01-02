package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class AddMembership extends JFrame{
    private JPanel addMembershipPanel;
    private JLabel startDateTitle;
    private JLabel isPaidTitle;
    private JLabel membershipTypeTitle;
    private JLabel startDateValue;
    private JButton addButton;
    private JComboBox selectStudentCmb;
    private JCheckBox isPaidCheckBox;
    private JComboBox selectTypeCmb;
    private JLabel dateFormat;

    public JFrame frame = new JFrame();
    private Membership mem = new Membership();
    private Student std = new Student();
    String[] str = {"12 ay", "6 ay"};
    public AddMembership(){

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
            frame.getContentPane().add(new AddMembership().addMembershipPanel);
            selectTypeCmb.addItem(str);
            frame.pack();
            frame.setVisible(true);
            //FillCombos();


        }

    private void FillCombos() {
        Student student=new Student();
        ResultSet rsStudent= student.GetAllStudent();


        try {
            String[] students=new String[rsStudent.getRow()];
            for(int i=0;i<students.length;i++) {

                students[i] = (rsStudent.getString(0));
                rsStudent.next();

            }

            selectStudentCmb=new JComboBox(students);


        selectTypeCmb.addItem("12 Ay");
        selectTypeCmb.addItem("6 Ay");
        }
        catch (Exception ex){

        }
    }


}
