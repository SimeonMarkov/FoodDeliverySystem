package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ingredient;
import model.Meal;
import model.MealType;



public interface IMealDAO {
	enum DataSource {
		DB, JSON, XML, CSV
	}
	
	
	static IMealDAO getDAO(DataSource ds) {
		switch (ds) {
		case DB:
			return DBMealDAO.getInstance();
		default:
			throw new IllegalArgumentException();
		}
	}
	
	Meal getMeal(long id);
	
	ArrayList<MealType> getMealTypes();
	
	ArrayList<Ingredient> getAllIngredients();
	
 	ArrayList<Meal> getSearchResult(long restID,long mealTypeID,double price,ArrayList<Long> ingredients);
		
	
}
