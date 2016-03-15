package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.IUserDAO;
import model.dao.IUserDAO.DataSource;

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
		HttpSession session = request.getSession();
		//TODO:what's the way to handle a fucking session? -> session.setMaxInactiveInterval(10);
		try {
			System.out.println(request.getSession().getId());
			for(User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()){
				if(u.getUsername().equals(username) && u.getPassword().equals(password)){
					session.setAttribute("loggedUser", u);
					session.removeAttribute("loginError");
					System.out.println(((User)session.getAttribute("loggedUser")).getUsername() + " logged in");
					response.sendRedirect("html/layout.html");
					return;
				}
			}
			session.setAttribute("loginError", true);
			for (User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()) {
				if (u.getUsername().equals(username)) {
					session.setAttribute("failLog", u);
					System.out.println(((User) session.getAttribute("failLog"))
							.getUsername() + " failed");
					response.sendRedirect("html/login.jsp");
					return;
				}
			} 
			System.out.println("No such user in database!");
			session.removeAttribute("failLog");
			response.sendRedirect("html/login.jsp");
		} catch (SQLException e) {
			response.sendRedirect("html/ShowError.jsp");
		}
	}

}
