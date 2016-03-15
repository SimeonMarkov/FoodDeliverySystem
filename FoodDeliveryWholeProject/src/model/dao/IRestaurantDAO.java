package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Meal;
import model.MealType;
import model.Order;
import model.Restaurant;
import model.RestaurantType;
import model.User;
import model.dao.IUserDAO.DataSource;

public interface IRestaurantDAO {

	enum DataSource {
		DB, JSON, XML, CSV
	}

	static IRestaurantDAO getDAO(DataSource ds) {
		switch (ds) {
		case DB:
			return DBRestaurantDAO.getInstance();
		default:
			throw new IllegalArgumentException();
		}
	}

	List<Restaurant> getAllRestaurants();

	List<Restaurant> getAllRestaurantsByType(long id);

	List<Restaurant> getAllRestaurantsByHood(long id);

	List<Restaurant> getAllRestaurantsByHoodByType(long typeId, long hoodId);
	
	List<RestaurantType> getAllRestaurantTypes();
	
	List<Meal> getAllRestaurantMeals(long id);
	
	List<MealType> getAllMealTypesByRest(long id);
}
