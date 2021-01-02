package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BloodType implements IDBOperation{
    private int recordId;
    private String bloodType;

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }


    @Override
    public boolean Insert() {

        StringBuilder sql = new StringBuilder();
        sql.append( "INSERT INTO BLOOD_TYPE ");
        sql.append( "(BLOOD_TYPE)");
        sql.append(" VALUES(");
        sql.append(bloodType);
        sql.append(")");


        try {
            con = db.getCon();
            statement =  con.createStatement();
            boolean result = statement.execute(sql.toString());
            return result;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean Update() {
        StringBuilder sql = new StringBuilder();
        sql.append( "UPDATE BLOOD_TYPE SET ");
        sql.append("BLOOD_TYPE = ");
        sql.append(bloodType);
        sql.append(" WHERE RECORD_ID = ");
        sql.append(recordId);


        try {
            con = db.getCon();
            statement =  con.createStatement();
            boolean result = statement.execute(sql.toString());
            return result;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean Delete() {
        String sql = "DELETE FROM BLOOD_TYPE WHERE RECORD_ID = " + recordId;
        try {
            con = db.getCon();
            statement =  con.createStatement();
            boolean result = statement.execute(sql.toString());
            return result;
        } catch (SQLException throwables) {
            return false;
        }
    }


    @Override
    public boolean Load(int recordId) {
        String sql = "SELECT * FROM BLOOD_TYPE WHERE RECORD ID = " + recordId;
        try {
            con = db.getCon();
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                LoadFromResultSet(result);
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }


        return false;
    }


    public void LoadFromResultSet(ResultSet rs){
        if(rs == null)
            return;
        try{
            while(rs.next()) {
                recordId = rs.getInt("RECORD_ID");
                bloodType = rs.getString("BLOOD_TYPE");

            }
        }catch(Exception ex){
            return;
        }

    }
}