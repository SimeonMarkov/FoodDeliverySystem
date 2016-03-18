package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import model.User;

/**
 * Servlet implementation class ChooseServlet
 */
@WebServlet("/ChooseServlet")
public class ChooseServlet extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Choose URL" + request.getSession().getAttribute("URL"));
		((User)request.getSession().getAttribute("loggedUser")).setChoosenAddress(Address.getAddressByID(Long.parseLong(request.getParameter("addressId"))));
		

		if (request.getSession().getAttribute("URL") != null) {
			response.sendRedirect((String) request.getSession().getAttribute("URL"));
		} else {
			response.sendRedirect("html/home.jsp");
		}

	}

}
