package basic;

import java.util.ArrayList;



public class Meal implements Comparable<Meal>{ 
	private String name;
	private double price;
	private MealType category;
	private double rating;
	private int timesRated;
	private ArrayList<Product> ingredients ; 
	public Meal(String name, double price, MealType category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.timesRated = 0;
		this.ingredients = new ArrayList<Product>();
	}
	
	public void setTimesRated(){
		this.timesRated++;
	}
	
	public double getRating(){
		return this.rating / this.timesRated;
	}
	
	public void setRating(double rating){
		this.rating += rating;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public MealType getCategory() {
		return category;
	}
	public void setCategory(MealType category) {
		this.category = category;
	}

	@Override
	public int compareTo(Meal o) {
		return (int)(price - o.price);
	}
	
	public String toString(){
		return name + "\n Category: " + category + "\n Ingredients: " + ingredients + "\n Price: " + price;
	}
	
	public void addIngredients(Product product){
		this.ingredients.add(product);
	}

}
