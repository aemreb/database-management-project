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
        sql.append( "INSERT INTO BLOODTYPE ");
        sql.append( "(RECORD_ID, BLOOD_TYPE)");
        sql.append(recordId);
        sql.append(",");
        sql.append(bloodType);


        try {
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
        sql.append( "UPDATE BLOODTYPE SET ");
        sql.append( "RECORD_ID = ");
        sql.append(recordId);
        sql.append(",");
        sql.append("BLOOD_TYPE = ");
        sql.append(bloodType);


        try {
            statement =  con.createStatement();
            boolean result = statement.execute(sql.toString());
            return result;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean Delete() {
        String sql = "DELETE FROM BLOODTYPE WHERE RECORD_ID = " + recordId;
        try {
            statement =  con.createStatement();
            boolean result = statement.execute(sql.toString());
            return result;
        } catch (SQLException throwables) {
            return false;
        }
    }


    @Override
    public boolean Load(int recordId) {
        String sql = "SELECT * FROM BLOODTYPE WHERE RECORD ID = " + recordId;
        try {
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