package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class ChooseServlet
 */
@WebServlet("/ChooseServlet")
public class ChooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String currentAddressSubmitted = request.getParameter("chosenAddress");
		System.out.println(currentAddressSubmitted);
		request.getSession().setAttribute("currentUseAddress", currentAddressSubmitted);
		System.out.println("Current address is " + request.getSession().getAttribute("currentUseAddress"));
		if(request.getSession().getAttribute("URL") != null){
			response.sendRedirect((String) request.getSession().getAttribute("URL"));
		}
		else{
			response.sendRedirect("html/home.jsp");
		}
		
	}

}
