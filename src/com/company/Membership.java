package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Membership  implements IDBOperation{
    private int studentId, recordId;
    private Date created, modified, startDate, endDate;
    private boolean statusId, isPaid;
    private int membershipTypeId;

    private Connection con = null;
    private Statement statement = null;
    private Database db = new Database();

    public int getRecordId() { return recordId; }

    public void setRecordId(int recordId) { this.recordId = recordId; }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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
        sql.append( "(STUDENT_ID, STARTED_DATE, STATUS, ISPAID, MEMBERSHIP_TYPE_ID)");
        sql.append(" VALUES(");
        sql.append(studentId);
        sql.append(",");
        sql.append(startDate);
        sql.append(",");
        sql.append(statusId);
        sql.append(",");
        sql.append(isPaid);
        sql.append(",");
        sql.append(membershipTypeId);
        sql.append(")");
        System.out.println(sql);
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
        sql.append( "UPDATE MEMBERSHIP SET ");
        sql.append( "STUDENT_ID= ");
        sql.append(studentId);
        sql.append(",");
        sql.append("STARTED_DATE = ");
        sql.append(startDate);
        sql.append(",");
        sql.append("END_DATE = ");
        sql.append(endDate);
        sql.append(",");
        sql.append("STATUS = ");
        sql.append(statusId);
        sql.append(",");
        sql.append("ISPAID = ");
        sql.append(isPaid);
        sql.append(",");
        sql.append("MEMBERSHIP_TYPE_ID = ");
        sql.append(membershipTypeId);
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
        String sql = "DELETE FROM MEMBERSHIP WHERE STUDENT_ID = " + studentId;
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
        String sql = "SELECT * FROM MEMBERSHIP WHERE RECORD_ID = " + recordId;
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


    public void LoadFromResultSet(ResultSet rs) {
        if (rs == null)
            return;
        try {
            while (rs.next()) {
                studentId = rs.getInt("STUDENT_ID");
                startDate = rs.getDate("STARTED_DATE");
                endDate = rs.getDate("END_DATE");
                statusId = rs.getBoolean("STATUS");
                isPaid = rs.getBoolean("ISPAID");
                membershipTypeId = rs.getInt("MEMBERSHIP_TYPE_ID");

            }
        } catch (Exception ex) {
            return;
        }
    }
        /*girilen student num için öğrencinin kayıt bilgilerini ekrana getirir madde-5*/
    public List<Membership> getMembershipsByStudentNum(int studentNum){
        List<Membership> lstMembership = new ArrayList<Membership>();

        String sql = "SELECT * FROM MEMBERSHIP M, STUDENT S WHERE M.STUDENT_ID = S.RECORD_ID AND S.STUDENT_NUM = " + studentNum;
        try {
            con = db.getCon();
            statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result != null){
                while (result.next()) {
                    Membership m = new Membership();
                    m.studentId = result.getInt("STUDENT_ID");
                    m.startDate = result.getDate("STARTED_DATE");
                    m.endDate = result.getDate("END_DATE");
                    m.statusId = result.getBoolean("STATUS");
                    m.isPaid = result.getBoolean("ISPAID");
                    m.membershipTypeId = result.getInt("MEMBERSHIP_TYPE_ID");
                    lstMembership.add(m);
                }

            }
        } catch (SQLException throwables) {
            return lstMembership;
        }


        return lstMembership;

    }



}
