package com.Controller;

import com.Bean.Login;
import com.Bean.Party;
import com.Dao.Mail;
import com.Dao.RegistrationDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private com.Dao.RegistrationDao RegistrationDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Party party = new Party();
        Login login = new Login();

        party.setFirstName(firstName);
        party.setLastName(lastName);
        party.setCity(city);
        party.setZip(zip);
        party.setState(state);
        party.setCountry(country);
        party.setPhone(phone);

        login.setEmail(email);
        login.setPassword(password);
            try {
                RegistrationDao register = new RegistrationDao();
                boolean success = register.saverUser(party, login);
                out.println("Register Successfully");
                HttpSession session=request.getSession(true);
                session.getAttribute("Register Successfully");
                response.sendRedirect("Home.jsp");
            } catch (ClassNotFoundException | SQLException e ) {
                e.printStackTrace();
                out.println(e);
            }

        }
        }




