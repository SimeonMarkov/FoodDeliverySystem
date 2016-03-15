package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Restaurant;
import model.RestaurantType;
import model.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<ArrayList<Restaurant>> rows = new ArrayList<>();
		ArrayList<Restaurant> rests = null;
		if (request.getSession().getAttribute("loggedUser") == null) {
			if (request.getParameter("restId") != null) {
				long restId = Long.parseLong(request.getParameter("restId"));
				rests = Restaurant.getAllRestaurantsByType(restId);
			} else {
				rests = Restaurant.getAllRestaurants();
			}
		} // else {
		// User u = (User)request.getSession().getAttribute("loggedUser");
		// if (request.getParameter("restId") != null) {
		// long restId = Long.parseLong(request.getParameter("restId"));
		// rests = Restaurant.getAllRestaurantsByHoodByType(restId,u.lo);
		// } else {
		// rests = Restaurant.getAllRestaurants();
		// }
		// }

		int col = 0;
		ArrayList<Restaurant> colArray = null;
		for (Restaurant r : rests) {
			if (col == 0) {
				colArray = new ArrayList<>();
				rows.add(colArray);
				col++;
			} else {
				col = 0;
			}
			colArray.add(r);
		}
		request.setAttribute("restRows", rows);
		request.setAttribute("restTypes", RestaurantType.getAllRestTypes());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
