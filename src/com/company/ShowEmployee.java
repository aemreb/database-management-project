package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


class View{
    String name;
    int offday;

    public View(String name, int offday) {
        this.name = name;
        this.offday = offday;
    }

    public String getName() {
        return name;
    }

    public int getOffday() {
        return offday;
    }
}

public class ShowEmployee {

    ArrayList<View> employees;
    JFrame frame;
    JPanel panel;
    JTable table;
    JScrollPane scrollpane;
    int i;

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public JScrollPane getScrollpane() {
        return scrollpane;
    }

    public ShowEmployee(){
         employees = new ArrayList<View>();
        this.i = i;
    }

    public void setEmployees(ArrayList<View> employees) {
        this.employees = employees;
    }

    public void ListOfEmployess(){
        String sql = "SELECT * FROM OFFDAY_REPORT",text;
        int offday;
        try {
            con = db.getCon();
            statement =  con.createStatement();
            boolean result = statement.execute(sql);
            if(!result) return ;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                text = resultSet.getString("?column?");
                offday = resultSet.getInt("offday");
                View view = new View(text,offday);
                employees.add(view);
            }
        } catch (SQLException throwables) {
            return ;
        }

    }

    public JPanel EmployeeTable(){
        ListOfEmployess();
        panel = new JPanel();
        String cells[][] = new String[employees.size()][3];
        String column[] = {"No","Name", "Offday"};
        setI(0);
        for (View aView : employees) {
            setI(getI() + 1);
            cells[getI() - 1] = new String[]{Integer.toString(getI()), aView.getName(), String.valueOf(aView.getOffday())};
        }
        table = new JTable(cells, column);
        table.setDefaultEditor(Object.class, null);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setBounds(0, 0, 600, 450);
        scrollpane = new JScrollPane(table);
        panel.add(scrollpane);
        panel.setBounds(0, 0, 740, 500);
        return panel;
    }


    public void load(){
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.getContentPane().add(new AddMembership().addMembershipPanel);
        frame.getContentPane().add(EmployeeTable());
        frame.pack();
        frame.setEnabled(true);
        frame.setVisible(true);
    }

}
