package model;

import java.text.*;
import java.util.*;

public class Order {
	private ArrayList<Meal> meals;
	private String date;
	private double price;

	public Order(ArrayList<Meal> meals, double price) {
		this.meals = meals;
		setDate();
		this.price = price;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public String getDate() {
		return date;
	}

	public void setDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.date = dateFormat.format(date);
	}

	public double getPrice() {
		return price;
	}

}
