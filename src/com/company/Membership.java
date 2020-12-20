package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Membership  implements IDBOperation{
    private String studentId;
    private Date created, modified, startDate, endDate;
    private boolean statusId, isPaid;
    private int membershipTypeId;

    private Connection con = null;
    private Statement statement = null;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStatusId() {
        return statusId;
    }

    public void setStatusId(boolean statusId) {
        this.statusId = statusId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(int membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }


    @Override
    public boolean Insert() {

        StringBuilder sql = new StringBuilder();
        sql.append( "INSERT INTO MEMBERSHIP ");
        sql.append( "(STUDENT_ID, START_DATE, END_DATE, STATUS_ID, IS_PAID, MEMBERSHIP_TYPE_ID)");
        sql.append(studentId);
        sql.append(",");
        sql.append(startDate);
        sql.append(",");
        sql.append(endDate);
        sql.append(",");
        sql.append(statusId);
        sql.append(",");
        sql.append(isPaid);
        sql.append(",");
        sql.append(membershipTypeId);
        sql.append(",");


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
        sql.append( "UPDATE MEMBERSHIP SET ");
        sql.append( "STUDENT_ID= ");
        sql.append(studentId);
        sql.append(",");
        sql.append("START_DATE = ");
        sql.append(startDate);
        sql.append(",");
        sql.append("END_DATE = ");
        sql.append(endDate);
        sql.append(",");
        sql.append("STATUS_ID = ");
        sql.append(statusId);
        sql.append(",");
        sql.append("IS_PAID = ");
        sql.append(isPaid);
        sql.append(",");
        sql.append("MEMBERSHIP_TYPE_ID = ");
        sql.append(membershipTypeId);

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
        String sql = "DELETE FROM MEMBERSHIP WHERE STUDENT_ID = " + studentId;
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
        String sql = "SELECT * FROM MEMBERSHIP WHERE RECORD ID = " + recordId;
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


    public void LoadFromResultSet(ResultSet rs) {
        if (rs == null)
            return;
        try {
            while (rs.next()) {
                studentId = rs.getString("STUDENT_ID");
                startDate = rs.getDate("START_DATE");
                endDate = rs.getDate("END_DATE");
                statusId = rs.getBoolean("STATUS_ID");
                isPaid = rs.getBoolean("IS_PAID");
                membershipTypeId = rs.getInt("MEMBERSHIP_TYPE_ID");

            }
        } catch (Exception ex) {
            return;
        }
    }
}
