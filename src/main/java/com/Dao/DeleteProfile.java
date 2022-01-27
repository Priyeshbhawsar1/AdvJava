package com.Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import com.Connection.DBConnection;

@WebServlet("/DeleteProfile")
public class DeleteProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            String partyId = (String) session.getAttribute("partyId");
            try {
                Connection con = DBConnection.getConnection();

                String qr = "DELETE UserLogin , Party  FROM UserLogin  INNER JOIN Party " +
                        "WHERE UserLogin.partyId= Party.partyId and Party.partyId =" + partyId;

                Statement st = con.createStatement();
                st.executeUpdate(qr);
                session.invalidate();
                request.setAttribute("deleteMsg", "Profile Deleted Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("Delete.jsp");
                rd.include(request, response);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else
            response.sendRedirect("Register.jsp");
    }
}


