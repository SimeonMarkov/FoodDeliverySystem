package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.DBUserDAO;
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
		
		try {
			if((User) session.getAttribute("failLog") == null){
				session.setAttribute("missingUser", true);
				response.sendRedirect("html/login.jsp");
			}
			else{
				for (User u : IUserDAO.getDAO(DataSource.DB).getAllUsers()) {
					if (u.getUsername().equals(((User) session.getAttribute("failLog")).getUsername())) {
						if (u.getSecretAnswer().equals(request.getParameter("answer"))){
							sendEmail(response, session);
							session.setAttribute("email", u.getEmail());
							response.sendRedirect("html/email_verification.jsp");
							response.setHeader("Refresh", "1; url=html/login.html");
							return;
						} else {
							session.setAttribute("wrongAnswer", true);
							response.sendRedirect("html/lostpass.jsp");
							return;
						}
					} 
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sendEmail(HttpServletResponse resp, HttpSession session) {
		String result = null;
		// Recipient's email ID needs to be mentioned.
		final String from = "simeon.markov1994@gmail.com";

		// Sender's email ID needs to be mentioned
		String to = ((User) session.getAttribute("failLog")).getEmail();

		final String password = "BadjanakA;34889413";
		final String newUserPassword = UUID.randomUUID().toString()
				.substring(0, UUID.randomUUID().toString().indexOf("-"));
		DBUserDAO.getInstance().updateUser(
				(User) session.getAttribute("failLog"), newUserPassword, to);

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session ses = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});

		try {

			Message message = new MimeMessage(ses);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Testing Subject");
			message.setText("Здравейте,"
					+ ((User) session.getAttribute("failLog")).getUsername()
					+ ".\nНовата Ви парола е " + newUserPassword);

			System.out.println("Sending");

			Transport.send(message);

			System.out.println("Done");
			result = "An email with a new password has been sent to "
					+ ((User) session.getAttribute("failLog")).getEmail();

		} catch (MessagingException e) {
			e.printStackTrace();
			try {
				resp.getWriter().println("Oops,something went wrong!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
