package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Login;
import Dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);


        boolean Success = LoginDao.validateLogin(login);
        if (Success) {

            HttpSession hs = request.getSession(true);
            hs.setAttribute("email", login.getEmail());
            hs.setAttribute("password", login.getPassword());
            //hs.setAttribute("partyId", login.getPartyId());


            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
            rd.forward(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
            rd.forward(request, response);
        }
    }

}
