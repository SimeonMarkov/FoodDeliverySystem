package basic;
import java.text.*;
import java.util.*;


public class Order {
	private ArrayList<Meal> meals;
	private String date;
	private double price;
	public Order(ArrayList<Meal> meals, String date, double price) {
		this.meals = meals;
		this.date = date;
		this.price = price;
	}
	public ArrayList<Meal> getMeals() {
		return meals;
	}
	public void addMeal(Meal meal) {
		this.meals.add(meal);
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
	public void setPrice(double price) {
		this.price = price;
	}
	

	
}
