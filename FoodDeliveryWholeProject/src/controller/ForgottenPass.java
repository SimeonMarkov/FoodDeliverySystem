package controller;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class ForgottenPass
 */
@WebServlet("/ForgottenPass")
public class ForgottenPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgottenPass() {
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
		HttpSession session = request.getSession();
		String answer = request.getParameter("answer"); //this is the value that WE ENTERED,NOT THE VALUE OF THE FAILED USER,WE WILL CHECK IF BOTH ARE EQUAL
		try {
			for(User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()){
				if(u.getUsername().equals(((User)session.getAttribute("failLog")).getUsername())){
					if(u.getSecretAnswer().equals(answer)){
						response.sendRedirect("html/email_verification.jsp");
						return;
					}
					else{
						session.setAttribute("wrongAnswer", true);
						response.sendRedirect("html/lostpass.jsp");
						return;
					}
				}
			}
			session.setAttribute("missingUser", true);
			response.sendRedirect("html/lostpass.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
