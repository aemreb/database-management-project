package com.company;

import java.sql.*;

public class Database {
    private String userName = "postgres";
    private String password = "compteng";
    private String databaseName = "DivingSchoolDB";
    private String host = "localhost";
    private int port = 5432;
    private static Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    String url = "jdbc:postgresql://" + host + ":"
            + port + "/" + databaseName;
    public Database(){


    }

    public Connection getCon() {
        if(con!=null)
            return con;
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Not Found");
            System.exit(0);
        }

        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception ex) {
            System.out.println("Connection Failed" + ex.getMessage());
            System.exit(0);
        }
        return con;
    }
}
