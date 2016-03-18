package model;

import java.util.ArrayList;
import java.util.Collections;

import model.dao.IRestaurantDAO;
import model.dao.IRestaurantDAO.DataSource;

public class Restaurant {
	private String name;
	private Address address;
	private String phoneNumber;
	private String email;
	private ArrayList<String> types;
	private ArrayList<Meal> meals;
	private ArrayList<Neighbourhood> servedRegion;
	private long restId;
	private String photoBytes;

	public Restaurant() {
		meals = new ArrayList<>();
		servedRegion = new ArrayList<>();
		types = new ArrayList<>();
	}

	public Restaurant(String name, Address address, String phoneNumber, String email) {
		this();
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
	}

	public void addType(String s) {
		this.types.add(s);
	}
	public static ArrayList<Restaurant> getAllRestaurants() {
		return (ArrayList<Restaurant>) IRestaurantDAO.getDAO(DataSource.DB).getAllRestaurants();
	}
	public static ArrayList<Restaurant> getAllRestaurantsByType(long id) {
		return (ArrayList<Restaurant>) IRestaurantDAO.getDAO(DataSource.DB).getAllRestaurantsByType(id);
	}
	public static ArrayList<Restaurant> getAllRestaurantsByHoodByType(long id,long hoodId) {
		return (ArrayList<Restaurant>) IRestaurantDAO.getDAO(DataSource.DB).getAllRestaurantsByHoodByType(id,hoodId);
	}
	public static ArrayList<Restaurant> getAllRestaurantsByHood(long id) {
		return (ArrayList<Restaurant>) IRestaurantDAO.getDAO(DataSource.DB).getAllRestaurantsByHood(id);
	}
	public static ArrayList<Meal> getAllMeals(long id) {
		return (ArrayList<Meal>) IRestaurantDAO.getDAO(DataSource.DB).getAllRestaurantMeals(id);
	}
	
	public void refreshServedRegion(){
		
	}
	public String getTypes() {
		return String.join(", ", types);
	}

	public long getRestId() {
		return restId;
	}
	

	public String getPhotoBytes() {
		return photoBytes;
	}

	public Restaurant setPhotoBytes(String photoBytes) {
		this.photoBytes = photoBytes;
		return this;
	}

	public Restaurant setRestId(long restId) {
		this.restId = restId;
		return this;
	}

	private void setEmail(String email) {
		// TODO:validate email!!!
		this.email = email;

	}

	public void addToServedRegion(Neighbourhood nbh) {
		this.servedRegion.add(nbh);
	}

	public ArrayList<Neighbourhood> getServedRegion() {
		return this.servedRegion;
	}

	public String getName() {
		return name;
	}

	public Restaurant setName(String name) {
		if (name != null) {
			this.name = name;
		}
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public Restaurant setAddress(Address address) {
		if (address != null) {
			this.address = address;
		}
		return this;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	private void setPhoneNumber(String phoneNumber) {
		if (phoneNumber.matches("08[0-9]{8}")) {
			this.phoneNumber = phoneNumber;
		}
	}

	public ArrayList<Meal> getMeals() {
		return (ArrayList<Meal>) Collections.unmodifiableList(this.meals);
	}

	public void addMeal(Meal meal) {
		// TODO: how to add the ingredients for our meal first,then add the meal
		// itself to the restaurant's menu?
		this.meals.add(meal);
	}

	public void removeMeal(Meal meal) {
		this.meals.remove(meal);
	}

}
