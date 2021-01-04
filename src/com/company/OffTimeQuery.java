package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OffTimeQuery {
    private JPanel OffTimeQueryPanel;
    private JButton listeleButton;
    private JTextArea günTextArea;
    private JFrame frame1 = new JFrame();

    private JFrame frame= new JFrame();

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public OffTimeQuery() {
        listeleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = günTextArea.getText();
                String sql = "SELECT COUNT(EMP.OFFDAY) , REC.TYPE\n" +
                        "from EMPLOYEE EMP\n" +
                        "         join RECORD_TYPE REC\n" +
                        "              on EMP.TYPE_ID = REC.RECORD_ID\n" +
                        "group by REC.TYPE\n" +
                        "having COUNT (EMP.OFFDAY) > "+value+"\n" +
                        "order by COUNT (EMP.OFFDAY);";
                try {
                    con = db.getCon();
                    statement =  con.createStatement();
                    boolean result = statement.execute(sql);
                    if(!result)return ;
                    ResultSet resultset = statement.executeQuery(sql);
                    new OffTimeTable().load(resultset);
                } catch (SQLException throwables) {
                    return ;
                }
            }
        });
    }

    public void load(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new OffTimeQuery().OffTimeQueryPanel);
        frame.pack();
        frame.setVisible(true);


    }
}
