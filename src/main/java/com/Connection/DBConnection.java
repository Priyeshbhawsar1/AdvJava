package com.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/adv", "priyesh", "123456");
            System.out.println("Connection Established");
            return con;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println(sqlException);

            return null;
        }

    }
}
