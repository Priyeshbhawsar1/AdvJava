package com.Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.Connection.DBConnection;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession=request.getSession();
        try{
            Connection con = DBConnection.getConnection();
            String partyId=(String) httpSession.getAttribute("partyId");
            String firstName = request.getParameter("firstName");
            String lastName  = request.getParameter("lastName");
            String city      = request.getParameter("city");
            String zip       = request.getParameter("zip");
            String state     = request.getParameter("state");
            String country   = request.getParameter("country");
            String phone     = request.getParameter("phone");

            String qr= "UPDATE Party SET firstName = ?, lastName = ?, city = ?, zip = ?, state = ?, " +
                    "country = ?, phone = ? WHERE partyId =" + partyId;

            PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1,firstName);
            ps.setString(2,lastName);
            ps.setString(3,city );
            ps.setString(4,zip);
            ps.setString(5, state);
            ps.setString(6,country);
            ps.setString(7, phone);
            ps.executeUpdate();
            RequestDispatcher rd= request.getRequestDispatcher("profile.jsp");
            rd.include(request,response);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
