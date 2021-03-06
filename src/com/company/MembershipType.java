package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MembershipType implements IDBOperation{
    private int recordId;
    private String type;
    private long price;

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    @Override
    public boolean Insert() {

        StringBuilder sql = new StringBuilder();
        sql.append( "INSERT INTO MEMBERSHIP_TYPE ");
        sql.append( "(TYPE,PRICE)");
        sql.append(" VALUES(");
        sql.append(type);
        sql.append(",");
        sql.append(price);
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
        sql.append( "UPDATE MEMBERSHIP_TYPE SET ");
        sql.append("TYPE = ");
        sql.append(type);
        sql.append(",");
        sql.append( "PRICE = ");
        sql.append(price);
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
        String sql = "DELETE FROM MEMBERSHIP_TYPE WHERE RECORD_ID = " + recordId;
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
        String sql = "SELECT * FROM MEMBERSHIP_TYPE WHERE RECORD_ID = " + recordId;
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
                type = rs.getString("TYPE");
                price = rs.getLong("PRICE");

            }
        }catch(Exception ex){
            return;
        }

    }
}


