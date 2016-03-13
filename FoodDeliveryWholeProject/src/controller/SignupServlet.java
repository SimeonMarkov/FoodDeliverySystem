package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.DBNeighbourhoodDAO;
import model.dao.DBUserDAO;
import model.dao.IUserDAO;
import model.dao.INeighbourhoodDAO;
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
		try{
			for(User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()){
				if(u.getUsername().equals(username) || u.getEmail().equals(email)){
					if(u.getUsername().equals(username)){
						request.setAttribute("usernameError", true);
						if((Boolean) request.getAttribute("usernameError")){
							request.getRequestDispatcher("html/sign_up.jsp").forward(request, response);
							return;
						}
					}
					else{
						request.setAttribute("emailError", true);
						if((Boolean) request.getAttribute("emailError")){
							request.getRequestDispatcher("html/sign_up.jsp").forward(request, response);
							return;
						}
					}
				} 
			}
				
			IUserDAO userDao = DBUserDAO.getInstance();
			User newUser = new User(username, password, email, secretQuestion, secretAnswer);
			userDao.addUser(newUser);
			String neighbourhood = request.getParameter("neighbourhoodOptions");
			String fullAddress = request.getParameter("fullAddress");
			userDao.addAddress(newUser,neighbourhood,fullAddress);
			request.getSession().setAttribute("loggedUser", newUser);
			response.sendRedirect("html/layout.html");
			
			
		}
		catch(SQLException e){
			response.sendRedirect("html/ShowError.jsp");
		}
	}
}