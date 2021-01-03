package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IncomeforMember {
    private JPanel IncomeForMembr;
    private JLabel isimTitle;
    private JButton hesaplaButton;
    private JTextPane sonucTextPane;
    private JTextArea textArea1;
    private JFrame frame= new JFrame();

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public IncomeforMember() {
        hesaplaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String value = textArea1.getText();
                String sql = "SELECT INCOME( "+value+")";
                try {
                    con = db.getCon();
                    statement =  con.createStatement();
                    boolean result = statement.execute(sql);
                    if(!result)return ;
                    ResultSet resultset = statement.executeQuery(sql);
                    resultset.next();
                    sonucTextPane.setText(String.valueOf(resultset.getInt("INCOME")));
                } catch (SQLException throwables) {
                    return ;
                }
            }
        });
    }


    public void load(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new IncomeforMember().IncomeForMembr);
        frame.pack();
        frame.setVisible(true);


    }

}
