package model;

import java.text.*;
import java.util.*;

import model.dao.DBMealDAO;
import model.dao.DBUserDAO;

public class Order {
	private ArrayList<Meal> meals;
	private Restaurant restaurant;

	private Date date;
	private double price;

	public Order(ArrayList<Meal> meals, double price) {
		this.meals = meals;
		// setDate();
		this.price = price;
	}

	public Order() {
		this.meals = new ArrayList<>();
	}

	public Date getDate() {
		return date;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	public static ArrayList<Order> getAllOrders() {
		return DBUserDAO.getInstance().getAllOrdersArchive();
	}

	public Order setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		return this;
	}

	public Order setDate(Date date) {
		this.date = date;
		return this;
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// Date date = new Date();
		// this.date = dateFormat.format(date);
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public Order setPrice(double price) {
		this.price = price;
		return this;
	}

	public void addMeal(Meal m) {
		meals.add(m);
	}

}
