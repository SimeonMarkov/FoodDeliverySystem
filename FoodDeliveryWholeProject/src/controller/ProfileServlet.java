package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.IUserDAO;
import model.dao.IUserDAO.DataSource;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5931480995663622821L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("Old password");
		String newPass = request.getParameter("New password");
		String retypePass = request.getParameter("Retype new password");
		String email = request.getParameter("email");
		if(newPass.equals(retypePass)){
			try {
				for(User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()){
					if(u.getUsername().equals(username) && u.getPassword().equals(oldPassword)){
						IUserDAO.getDAO(DataSource.DB).updateUser(u,retypePass,email);
						PrintWriter out = response.getWriter();
						request.getSession().removeAttribute("wrongPass");
						request.getSession().removeAttribute("mismatchPass");
						response.setHeader("Refresh", "0.2;url=html/layout.html");
						return;
					}
				}
				
				PrintWriter out = response.getWriter();
				request.getSession().setAttribute("wrongPass", true);
				response.setHeader("Refresh", "0.2;url=html/profile.jsp");
				
				
				
				
			} catch (SQLException e) {
				System.out.println("Mistake in update!");
				e.printStackTrace();
			}
		}
		else{
			PrintWriter out = response.getWriter();
			request.getSession().setAttribute("mismatchPass", true);
			response.setHeader("Refresh", "0.2;url=html/profile.jsp");
		}
		
	}
}
