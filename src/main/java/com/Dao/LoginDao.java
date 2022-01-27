package com.Dao;

import com.Bean.Login;

import javax.servlet.http.HttpServlet;

import com.Connection.DBConnection;

import java.sql.*;

public class LoginDao extends HttpServlet {

    public static Boolean validateLogin(Login login) {
        try {
            Connection con = DBConnection.getConnection();
            String qr = "select userLoginId,password from UserLogin";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qr);
            while (rs.next()) {
                //check email password correct.
                if (rs.getString("userLoginId").equalsIgnoreCase(login.getEmail())
                        && rs.getString("password").equals(login.getPassword())) {
                    return true;

                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;


    }
}
