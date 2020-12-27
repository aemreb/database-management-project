package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class HealthInfo implements IDBOperation{

    private int recordId, recordTypeId, recordValueId, height, weight;
    private int bloodType; //0: 0, 1: A, 2: B, 3: AB
    private Date created, modified;
    private boolean billOfHealth;
    private String chronicIllness, medications, allergy;

    private Connection con = null;
    private Statement statement = null;


    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getRecordType() {
        return recordTypeId;
    }

    public void setRecordType(int recordType) {
        this.recordTypeId = recordTypeId;
    }

    public int getRecordValueId() { return recordValueId; }

    public void setRecordValueId(int recordValueId) { this.recordValueId = recordValueId; }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBloodType() {
        return bloodType;
    }

    public void setBloodType(int bloodType) {
        this.bloodType = bloodType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public boolean isBillOfHealth() {
        return billOfHealth;
    }

    public void setBillOfHealth(boolean billOfHealth) {
        this.billOfHealth = billOfHealth;
    }

    public String getChronicIllness() {
        return chronicIllness;
    }

    public void setChronicIllness(String chronicIllness) {
        this.chronicIllness = chronicIllness;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }


    @Override
    public boolean Insert() {

        StringBuilder sql = new StringBuilder();
        sql.append( "INSERT INTO HEALTH_INFO ");
        sql.append( "(RECORD_TYPE_ID, RECORD_VALUE, HEIGHT, WEIGHT, BLOOD_TYPE_ID, BILL_OF_HEALTH)");
        sql.append(" VALUES(");
        sql.append(recordTypeId);
        sql.append(",");
        sql.append(height);
        sql.append(",");
        sql.append(weight);
        sql.append(",");
        sql.append(bloodType);
        sql.append(",");
        sql.append(billOfHealth);
        sql.append(")");

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
        sql.append( "UPDATE HEALTH_INFO SET ");
        sql.append( "RECORD_ID = ");
        sql.append(recordId);
        sql.append(",");
        sql.append("RECORD_TYPE = ");
        sql.append(recordTypeId);
        sql.append(",");
        sql.append("HEIGHT = ");
        sql.append(height);
        sql.append(",");
        sql.append("WEIGHT = ");
        sql.append(weight);
        sql.append(",");
        sql.append("BLOOD_TYPE_ID = ");
        sql.append(bloodType);
        sql.append(",");
        sql.append("BILL_OF_HEALTH = ");
        sql.append(billOfHealth);
        sql.append(" WHERE RECORD_ID = ");
        sql.append(recordId);

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
        String sql = "DELETE FROM HEALTH_INFO WHERE RECORD_ID = " + recordId;
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
        String sql = "SELECT * FROM HEALTH_INFO WHERE RECORD_ID = " + recordId;
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
                recordTypeId = rs.getInt("RECORD_TYPE");
                height = rs.getInt("HEIGHT");
                weight = rs.getInt("WEIGHT");
                bloodType = rs.getInt("BLOOD_TYPE_ID");
                billOfHealth = rs.getBoolean("BILL_OF_HEALTH");

            }
        }catch(Exception ex){
            return;
        }

    }
}

