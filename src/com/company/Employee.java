package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.lang.String;

public class Employee implements IDBOperation {
    private int recordId;
    private int typeId;
    private Date createdDateTime;
    private Date modifiedDateTime;
    private String identityNum;
    private String name;
    private String surname;
    private int age;
    private String phone;
    private String emergencyPhone;
    private String email;
    private String adress;
    private long salary;
    private Integer offDay;

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();


    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Date modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Integer getOffDay() {
        return offDay;
    }

    public void setOffDay(Integer offDay) {
        this.offDay = offDay;
    }


    @Override
    public boolean Insert() {

        StringBuilder sql = new StringBuilder();
        sql.append( "INSERT INTO EMPLOYEE ");
        sql.append( "(NAME, SURNAME,IDENTITY_NUM, TYPE_ID, AGE, PHONE, EMERGENCY_PHONE, EMAIL, SALARY, OFFDAY)");
        sql.append(" VALUES('");
        sql.append(name);
        sql.append("','");
        sql.append(surname);
        sql.append("','");
        sql.append(identityNum);
        sql.append("',");
        sql.append(typeId);
        sql.append(",");
        sql.append(age);
        sql.append(",'");
        sql.append(phone);
        sql.append("','");
        sql.append(emergencyPhone);
        sql.append("','");
        sql.append(email);
        sql.append("',");
        sql.append(salary);
        sql.append(",");
        sql.append(offDay);
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
        sql.append( "UPDATE EMPLOYEE SET ");
        sql.append( "NAME = ");
        sql.append(name);
        sql.append(",");
        sql.append("SURNAME = ");
        sql.append(surname);
        sql.append(",");
        sql.append("IDENTITY_NUM = ");
        sql.append(identityNum);
        sql.append(",");
        sql.append("TYPE_ID = ");
        sql.append(typeId);
        sql.append(",");
        sql.append("AGE = ");
        sql.append(age);
        sql.append(",");
        sql.append("PHONE = ");
        sql.append(phone);
        sql.append(",");
        sql.append("EMERGENY_PHONE = ");
        sql.append(emergencyPhone);
        sql.append(",");
        sql.append("EMAIL = ");
        sql.append(email);
        sql.append(",");
        sql.append(salary);
        sql.append(",");
        sql.append(offDay);
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
        String sql = "DELETE FROM EMPLOYEE WHERE RECORD_ID = " + recordId;
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
        String sql = "SELECT * FROM EMPLOYEE WHERE RECORD ID = " + recordId;
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
                identityNum = rs.getString("IDENTITY_NUM");
                name = rs.getString("NAME");
                surname = rs.getString("SURNAME");
                typeId = rs.getInt("TYPE_ID");
                age = rs.getInt("AGE");
                phone = rs.getString("PHONE");
                emergencyPhone = rs.getString("EMERGENCY_PHONE");
                email = rs.getString("EMAIL");
                salary = rs.getInt("SALARY");
                offDay = rs.getInt("OFFDAY");
            }
        }catch(Exception ex){
            return;
        }

    }

    public ResultSet salaryReport(int minSalary){
        String sql = "SELECT SUM(SALARY), R.TYPE, E.TYPE_ID FROM EMPLOYEE E, RECORD_TYPE R WHERE E.TYPE_ID=R.RECORD_ID GROUP BY E.TYPE_ID, R.TYPE HAVING SUM(SALARY) > "+ minSalary;

        try {
            con = db.getCon();
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                return result;

            }
        } catch (SQLException throwables) {
            return null;
        }
        return null;
    }

    public boolean getEmployeeInfo(String record_id){
        ResultSet result;
        String sql = "SELECT get_emp_info(" + record_id + ")";

        try {
            con = db.getCon();
            statement = con.createStatement();
            result = statement.executeQuery(sql);
            if(result != null){
                result.next();
                String[] rs = result.getString("get_emp_info").replace("(", "").replace(")", "").split(",");
                name = rs[0];
                surname = rs[1];
                phone = rs[2];
                offDay = Integer.parseInt(rs[3]);

                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return false;
    }


}
