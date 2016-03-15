package model;

import java.util.ArrayList;

import model.dao.IRestaurantDAO;
import model.dao.IRestaurantDAO.DataSource;

public class RestaurantType {

	private long id;
	private String name;

	public RestaurantType(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public static ArrayList<RestaurantType> getAllRestTypes() {
		return (ArrayList<RestaurantType>) IRestaurantDAO.getDAO(DataSource.DB).getAllRestaurantTypes();
	}
	//repush
}
