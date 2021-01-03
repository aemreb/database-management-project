package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TotalSalary {
    private JPanel TotalSalaryPanel;
    private JButton listeleButton;
    private JTextArea tarihTextArea;
    private JTextPane textPane1;
    private JFrame frame1 = new JFrame();

    private JFrame frame= new JFrame();

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public TotalSalary() {
        listeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = tarihTextArea.getText();
                String sql = "SELECT COUNT(MEM.*) , SUM(MEMT.PRICE)\n" +
                        "FROM MEMBERSHIP MEM, MEMBERSHIP_TYPE MEMT\n" +
                        "WHERE MEM.STARTED_DATE >= '"+date+" 00:00:00' AND MEM.ISPAID = TRUE and MEMT.RECORD_ID = MEM.RECORD_ID;";
                try {
                    con = db.getCon();
                    statement =  con.createStatement();
                    boolean result = statement.execute(sql);
                    if(!result)return ;
                    ResultSet resultset = statement.executeQuery(sql);
                    resultset.next();
                    String count = String.valueOf(resultset.getInt("COUNT"));
                    String sum = String.valueOf(resultset.getInt("SUM"));
                    textPane1.setText("Toplam sayı :"+count+",Toplam maas:"+sum);
                } catch (SQLException throwables) {
                    return ;
                }
            }
        });
    }

    public void load(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new TotalSalary().TotalSalaryPanel);
        frame.pack();
        frame.setVisible(true);


    }
}
