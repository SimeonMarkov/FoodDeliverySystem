package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;
import model.dao.DBUserDAO;
import model.dao.IUserDAO;
import model.dao.IUserDAO.DataSource;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim(); 
		String password = request.getParameter("password"); 
		String email = request.getParameter("email").trim(); 
		String secretQuestion = request.getParameter("question").trim(); 
		String secretAnswer = request.getParameter("answer").trim();
		HttpSession session = request.getSession();
		try{
				
			for(User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()){
				if(u.getUsername().equals(username) && u.getEmail().equals(email)){
					session.setAttribute("usernameError", true);
					session.setAttribute("usedEmailError", true);
					response.sendRedirect("html/sign_up.jsp");
					return;
				}
				else if(u.getUsername().equals(username) && !u.getEmail().equals(email)){
					session.setAttribute("usernameError", true);
					response.sendRedirect("html/sign_up.jsp");
					return;
				}
				else if(!u.getUsername().equals(username) && u.getEmail().equals(email)){
					session.setAttribute("usedEmailError", true);
					response.sendRedirect("html/sign_up.jsp");
					return;
				}
			}
				
			IUserDAO userDao = DBUserDAO.getInstance();
			User newUser = new User(username, password, email, secretQuestion, secretAnswer);
			String neighbourhood = request.getParameter("neighbourhoodOptions");
			String fullAddress = request.getParameter("fullAddress");
			Address choosenAddress = new Address(neighbourhood, fullAddress);
			session.setAttribute("loggedUser", newUser);
			userDao.addUser((User)request.getSession().getAttribute("loggedUser"));
			userDao.addAddress((User)request.getSession().getAttribute("loggedUser"),neighbourhood,fullAddress);
			session.setAttribute("addr", DBUserDAO.getInstance().selectAddresses((User)request.getSession().getAttribute("loggedUser")));
			System.out.println(request.getSession().getAttribute("loggedUser") + " signed up");
			System.out.println(((User)request.getSession().getAttribute("loggedUser")).getUsername() + " logged in with address " + ((User)request.getSession().getAttribute("loggedUser")).getChoosenAddress());
			System.out.println(DBUserDAO.getInstance().selectAddresses(newUser));
			response.sendRedirect("html/chooseaddress.jsp");
			
			
		}
		catch(SQLException e){
			//response.sendRedirect("html/ShowError.jsp");
		}
	}
	
	private static boolean isPasswordStrong(String password){
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";
	    return password.matches(pattern);
	}
	
	private static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
}