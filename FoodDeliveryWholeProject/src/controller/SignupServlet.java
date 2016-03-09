package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username"); 
		String password = request.getParameter("password"); 
		String email = request.getParameter("email"); 
		String secretQuestion = request.getParameter("question"); 
		String secretAnswer = request.getParameter("answer"); 
		User newUser = new User(username, password, email, secretQuestion, secretAnswer);
		try{
			for(User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()){
				if(u.getUsername().equals(username) || u.getEmail().equals(email)){
					if(u.getUsername().equals(username)){
						request.setAttribute("usernameError", true);
						if((Boolean) request.getAttribute("usernameError")){
							response.sendRedirect("html/sign_up.jsp");
							return;
						}
					}
					if(u.getEmail().equals(email)){
						request.setAttribute("emailError", true);
						if((Boolean) request.getAttribute("emailError")){
							response.sendRedirect("html/sign_up.jsp");
							return;
						}
					}
				} 
			}
				
			IUserDAO dao = DBUserDAO.getInstance();
			System.out.println(dao);
			dao.addUser(newUser);
			response.sendRedirect("html/layout.html");
			
			
		}
		catch(SQLException e){
			response.getWriter().append("Something went wrong with the connection");
		}
	}
}