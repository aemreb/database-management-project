package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MembershipInfo extends JFrame{
    private JPanel panelMain;
    private JPanel membershipInfoPanel;
    private JLabel isimTitle;
    private JLabel isimValue;
    private JLabel soyisimTitle;
    private JLabel startDateTitle;
    private JLabel endDateTitle;
    private JLabel statusTitle;
    private JLabel isPaidTitle;
    private JLabel emailTitle;
    private JLabel soyisimValue;
    private JLabel endDateValue;
    private JLabel statusValue;
    private JLabel isPaidValue;
    private JLabel membershipTypeValue;
    private JTextField enterStudentInfoTextField;
    private JButton searchButton;
    private JLabel startDateValue;

    public JFrame frame = new JFrame();
    private String membershipInfo;

    private Membership mem = new Membership();
    private Student std = new Student();
    private MembershipType memtype = new MembershipType();

    public MembershipInfo(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                membershipInfo = enterStudentInfoTextField.getText();
                List<Membership> lstmem = mem.getMembershipsByStudentNum(Integer.parseInt(membershipInfo));
                mem = lstmem.get(0);
                std.Load(mem.getStudentId());
                isimValue.setText(std.getName());
                soyisimValue.setText(std.getSurname());
                startDateValue.setText(mem.getStartDate().toString());
                endDateValue.setText(mem.getEndDate().toString());
                if(mem.isStatusId())
                    statusValue.setText("AKTİF");
                else
                    statusValue.setText("PASİF");
                if(mem.isPaid())
                    isPaidValue.setText("ÖDENDİ");
                else
                    isPaidValue.setText("ÖDENMEDİ");
                memtype.Load(mem.getMembershipTypeId());
                membershipTypeValue.setText(memtype.getType());

            }
        });
    }

    public void load(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new MembershipInfo().membershipInfoPanel);
        frame.pack();
        frame.setVisible(true);


    }


}
