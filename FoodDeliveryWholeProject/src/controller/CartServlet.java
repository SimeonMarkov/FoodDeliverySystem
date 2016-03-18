package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Meal;
import model.User;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getSession().getAttribute("loggedUser"));
		System.out.println(request.getSession().getAttribute("restorantId"));
		
		
		((User)request.getSession().getAttribute("loggedUser")).saveCartToDB((long)request.getSession().getAttribute("restorantId"));
		
		response.sendRedirect("html/home.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println(request.getParameter("qty"));
		System.out.println(request.getParameter("mealId"));
		
		long mealId = Long.parseLong(request.getParameter("mealId"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		User u = (User) request.getSession().getAttribute("loggedUser");
		
		if(qty>0) {
			u.addProductInCart(Meal.getMealByID(mealId));
		} else if(qty<0){
			u.removeProductFromCart(mealId);
		} else {
			u.removeAllProductsById(mealId);
		}
		
		response.setContentType("application/json");
		String totalPrice = String.format("%1$,.2f", u.getTotalPriceOfCart()).replace(",", ".");
		
		response.getWriter().write("{\"count\": " + u.getCartSize() +  ",\"id\":"+ mealId+ ",\"qty\": "+ u.getMealQuantity(mealId) +",\"total\": \""+ totalPrice +
				"\",\"remove\": "+ qty+"}");
		System.out.println("{\"count\": " + u.getCartSize() +  ",\"id\":"+ mealId+ ",\"qty\": "+ u.getMealQuantity(mealId) +",\"total\": \""+ totalPrice +
				"\",\"remove\": "+ qty+"}");
	}

}
