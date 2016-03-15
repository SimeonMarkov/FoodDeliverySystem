package model;

import java.util.ArrayList;

import model.dao.IRestaurantDAO;
import model.dao.IRestaurantDAO.DataSource;

public class MealType {
	private long id;
	private String type;

	public MealType(long id, String type) {
		this.id = id;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	public static ArrayList<MealType> getAllMealTypesByRest(long id) {
		return (ArrayList<MealType>) IRestaurantDAO.getDAO(DataSource.DB).getAllMealTypesByRest(id);
	}
}
