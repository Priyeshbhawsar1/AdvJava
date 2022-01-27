package com.Dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Connection.DBConnection;

@WebServlet("/Search")
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");


        try {
            Connection con = DBConnection.getConnection();
            String searchName = "select firstName, lastName from Party";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(searchName);

            ArrayList<String> searchResult = new ArrayList<>();

            // Check if name entered by the user matches inside the database.
            while (rs.next()){
                if(rs.getString("firstName").equalsIgnoreCase(firstName)
                        && rs.getString("lastName").equalsIgnoreCase(lastName)){

                    // Fetches profile from DB based on user input
                    String profileQuery = "SELECT * FROM adv.Party Natural Join adv.UserLogin where firstName ="
                            +firstName+ "AND lastName =" +lastName;
                    Statement statement1 = con.createStatement();
                    System.out.println("Profile Query Executed");
                    ResultSet result = statement1.executeQuery(profileQuery);

                    if(result.next()){
                        String email = result.getString("userLoginId");
                        String city = result.getString("city");
                        String zip = result.getString("zip");
                        String state = result.getString("state");
                        String country = result.getString("country");
                        String phone = result.getString("phone");

                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("email", email);
                        httpSession.setAttribute("firstName", firstName);
                        httpSession.setAttribute("lastName", lastName);
                        httpSession.setAttribute("city", city);
                        httpSession.setAttribute("zip", zip);
                        httpSession.setAttribute("state", state);
                        httpSession.setAttribute("country", country);
                        httpSession.setAttribute("phone", phone);
                    }

                    RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                    rd.forward(request, response);
                } // If condition closed
            } // While Loop Closed
            PrintWriter out = response.getWriter();
            out.println("No records found");

            request.setAttribute("searchResult", searchResult);
            request.getRequestDispatcher("result.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
