package basic;
import java.text.*;
import java.util.*;


public class Order {
	private List<Product> meals;
	private String date;
	private double price;
	public Order(List<Product> meals, String date, double price) {
		super();
		this.meals = meals;
		this.date = date;
		this.price = price;
	}
	public List<Product> getMeals() {
		return meals;
	}
	public void addMeal(Product meal) {
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
