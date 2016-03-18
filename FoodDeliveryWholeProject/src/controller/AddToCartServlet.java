package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Meal;
import model.User;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("addtocart: " + request.getSession().getAttribute("restorantId"));
		
		String mealId = request.getParameter("mealId");
		User u = (User) request.getSession().getAttribute("loggedUser");
		u.addProductInCart(Meal.getMealByID(Long.parseLong(mealId)));
		response.setContentType("application/json");
		response.getWriter().write("{\"count\": " + u.getCartSize() + "}");
		

	}

}
