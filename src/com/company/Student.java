package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements IDBOperation{
    private int recordId;
    private Date createdDateTime;
    private Date modifiedDateTime;
    private int studentNum;
    private String identityNum;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private String emergencyPhone;
    private String email;
    private String address;

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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

    public int getStudentNum() { return studentNum; }

    public void setStudentNum(int studentNum) { this.studentNum = studentNum; }

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

    public Integer getAge() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean Insert() {

        StringBuilder sql = new StringBuilder();
             sql.append( "INSERT INTO STUDENT ");
             sql.append( "(NAME, SURNAME,IDENTITY_NUM, AGE, PHONE, EMERGENCY_PHONE, EMAIL, ADDRESS, STUDENT_NUM)");
             sql.append(" VALUES('");
             sql.append(name);
             sql.append("','");
             sql.append(surname);
             sql.append("','");
             sql.append(identityNum);
             sql.append("',");
             sql.append(age);
             sql.append(",'");
             sql.append(phone);
             sql.append("','");
             sql.append(emergencyPhone);
             sql.append("','");
             sql.append(email);
             sql.append("','");
             sql.append(address);
             sql.append("',");
             sql.append("nextval('seq_studentnum')");
             sql.append(")");
        try {
            con = db.getCon();
            statement =  con.createStatement();
            int result = statement.executeUpdate(sql.toString());
            ResultSet rs = null;
            System.out.println(result);
            if(result>0) {
                con = db.getCon();
                statement =  con.createStatement();
                rs = statement.executeQuery("SELECT MAX(STUDENT_NUM) AS STUDENT_NUM FROM STUDENT");
                rs.next();
                studentNum = rs.getInt("STUDENT_NUM");
            }
            return result > 0;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean Update() {
        StringBuilder sql = new StringBuilder();
        sql.append( "UPDATE STUDENT SET ");
        sql.append("STUDENT_NUM = ");
        sql.append(studentNum);
        sql.append( ", NAME = '");
        sql.append(name);
        sql.append("',");
        sql.append("SURNAME = '");
        sql.append(surname);
        sql.append("',");
        sql.append("IDENTITY_NUM = '");
        sql.append(identityNum);
        sql.append("',");
        sql.append("AGE = ");
        sql.append(age);
        sql.append(",");
        sql.append("PHONE = '");
        sql.append(phone);
        sql.append("',");
        sql.append("EMERGENCY_PHONE = '");
        sql.append(emergencyPhone);
        sql.append("',");
        sql.append("EMAIL = '");
        sql.append(email);
        sql.append("',");
        sql.append("ADDRESS = '");
        sql.append(address);
        sql.append("' WHERE RECORD_ID = ");
        sql.append(recordId);
        try {
            con = db.getCon();
            statement =  con.createStatement();
            int result = statement.executeUpdate(sql.toString());
            System.out.println(result);
            return result > 0;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean Delete() {
        String sql = "DELETE FROM STUDENT WHERE RECORD_ID = " + recordId;
        try {
            con = db.getCon();
            statement =  con.createStatement();
            int result = statement.executeUpdate(sql.toString());

            return result > 0;
        } catch (SQLException throwables) {
            return false;
        }
    }


    @Override
    public boolean Load(int recordId) {
        String sql = "SELECT * FROM STUDENT WHERE RECORD_ID = " + recordId;
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


    public boolean LoadWithStdNum(int studentNum) {
        String sql = "SELECT * FROM STUDENT WHERE STUDENT_NUM = " + studentNum;
        try {
            con = db.getCon();
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            LoadFromResultSet(result);
            return true;

        } catch (SQLException throwables) {
            return false;
        }


    }

    public String[] GetAllStudent() {
        ResultSet result=null;
        String[] str = null;
        String sql = "SELECT STUDENT_NUM || ' ' || NAME ||' ' || SURNAME AS INFO FROM STUDENT ";
        try {
            con = db.getCon();
            statement = con.createStatement();
             result = statement.executeQuery(sql);
            if(result != null){

                str = new String[50];

                int i=0;
                while(result.next()){
                    str[i] = result.getString("INFO");
                    i++;
                }
               return  str;
            }
        } catch (SQLException throwables) {
            return str;
        }


        return str;
    }

    public int getAvgOfAge(String blood){
        ResultSet result;
        String sql = "SELECT bloodtype_avg(" + blood + ")";

        try {
            con = db.getCon();
            statement = con.createStatement();
            result = statement.executeQuery(sql);
            if(result != null){
                result.next();
                return result.getInt("bloodtype_avg");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }

        return 0;
    }

    public void LoadFromResultSet(ResultSet rs){

        if(rs == null)
            return;
        try{
            while(rs.next()) {
                recordId = rs.getInt("RECORD_ID");
                studentNum = rs.getInt("STUDENT_NUM");
                identityNum = rs.getString("IDENTITY_NUM");
                name = rs.getString("NAME");
                surname = rs.getString("SURNAME");
                age = rs.getInt("AGE");
                phone = rs.getString("PHONE");
                emergencyPhone = rs.getString("EMERGENCY_PHONE");
                email = rs.getString("EMAIL");
                address = rs.getString("ADDRESS");
            }
        }catch(Exception ex){
                return;
            }

    }




}
