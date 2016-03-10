package model;

import java.text.*;
import java.util.*;

public class Order {
	private ArrayList<Meal> meals;

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
