package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Meal;
import model.MealType;
import model.Restaurant;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("loggedUser") == null) {
			request.setAttribute("notLogged", true);
			String url = request.getRequestURL().toString()+"?restId="+request.getParameter("restId");
			request.getSession().setAttribute("URL", url);
			//request.setAttribute("U", request.getRequestURL().toString());
			return;
		} else {
			request.getSession().removeAttribute("URL");
		}
		
		try {
			if (request.getParameter("restId") == null) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			response.sendRedirect("ShowError.jsp");
		}

		long restId = Long.parseLong(request.getParameter("restId"));
		request.getSession().setAttribute("restorantId", restId);
		request.setAttribute("mealType", MealType.getAllMealTypesByRest(restId));
		request.setAttribute("menu", Restaurant.getAllMeals(restId));
		// TODO listove sus menu by type_id
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
