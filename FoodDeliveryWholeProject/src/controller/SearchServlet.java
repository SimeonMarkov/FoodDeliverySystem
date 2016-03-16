package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ingredient;
import model.Meal;
import model.MealType;
import model.Restaurant;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initData(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initData(request, response);
		String priceS = request.getParameter("price").replace(',', '.');
		double price = 99999;
		if (priceS.length() > 0) {
			try {
					price = Double.parseDouble(priceS);
			} catch (NumberFormatException e) {
				request.setAttribute("errorPrice", true);
				return;
			}
			if(price<=0) {
				request.setAttribute("errorPrice", true);
				return;
			}
		}
		
		if(price==99999) {
			price=-1;
		}
		
		long mtParamID = Long.parseLong(request.getParameter("mtValue"));
		long restParamID = Long.parseLong(request.getParameter("restValue"));
		ArrayList<Long> ingredientIDs = new ArrayList<>();
		String[] results = request.getParameterValues("c1");
		if (results != null) {
			for (int i = 0; i < results.length; i++) {
				ingredientIDs.add(Long.parseLong(results[i]));
			}
		}
		request.setAttribute("results", Meal.getSearchResult(restParamID, mtParamID, price, ingredientIDs));

	}

	private void initData(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("restoranti", Restaurant.getAllRestaurants());
		request.setAttribute("mealTypes", MealType.getAllMealTypes());
		ArrayList<ArrayList<Ingredient>> sortedIngredients = new ArrayList<>();
		int count = 0;
		ArrayList<Ingredient> row = null;
		for (Ingredient i : Ingredient.getAllIngredients()) {
			if (count % 6 == 0) {
				row = new ArrayList<>();
				sortedIngredients.add(row);
			}
			row.add(i);
			count++;
		}
		request.setAttribute("ingredients", sortedIngredients);

	}

}
