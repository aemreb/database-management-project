package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class OffDay{
    String type;
    int offdays;

    public OffDay(String type, int offdays) {
        this.type = type;
        this.offdays = offdays;
    }

    public String getType() {
        return type;
    }

    public int getOffDays() {
        return offdays;
    }
}

public class OffTimeTable {

    ArrayList<OffDay> offdays;
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

    public OffTimeTable(){
        offdays = new ArrayList<OffDay>();
        this.i = i;
    }

    public void setEmployees(ArrayList<OffDay> offdays) {
        this.offdays = offdays;
    }

    public void ListOfSalaries(ResultSet resultSet){
        int offday;
        String text;
        try {
            while (resultSet.next()){
                offday = resultSet.getInt("count");
                text = resultSet.getString("type");
                OffDay offDay = new OffDay(text,offday);
                offdays.add(offDay);
            }
        } catch (SQLException throwables) {
            return ;
        }

    }

    public JPanel OffDaysTable(ResultSet resultSet){
        ListOfSalaries(resultSet);
        panel = new JPanel();
        String cells[][] = new String[offdays.size()][3];
        String column[] = {"No","Offday", "Type"};
        setI(0);
        for (OffDay offDay : offdays) {
            setI(getI() + 1);
            cells[getI() - 1] = new String[]{Integer.toString(getI()), offDay.getType(), String.valueOf(offDay.getOffDays())};
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


    public void load(ResultSet resultSet){
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.getContentPane().add(new AddMembership().addMembershipPanel);
        frame.getContentPane().add(OffDaysTable(resultSet));
        frame.pack();
        frame.setEnabled(true);
        frame.setVisible(true);
    }

}
