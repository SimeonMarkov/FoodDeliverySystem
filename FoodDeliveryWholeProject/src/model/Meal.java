package model;

import java.util.ArrayList;

public class Meal implements Comparable<Meal> {
	
	public enum MealType {
	     SALAD,SOUP,PASTA,PIZZA,ALAMINUT,BBQ,FISH,BREAD,SOUCE,DESERT,DRINKS,VEGETERIAN,OSNOVNO,
	     // POD VIDOVE ZA NQKOI OT IASTIATA S VID MESO ILI BBQ
	     PORK,CHICKEN,BEEF,DUCK,
	     // POD VID ZA PASTA
	     SPAGHETTI,LASAGNA,
	     // POD VID DRINKS
	     BEER,WINE,STRONG_ALCOHOL,NON_ALCOHOL
	     
	     
	    
	}
	
	private String name;
	private double price;
	private MealType category;
	private double rating;
	private int timesRated;
	private ArrayList<Ingredient> ingredients;
	

	public Meal(String name, double price, MealType category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.timesRated = 0;
		this.ingredients = new ArrayList<Ingredient>();
	}
	public Meal() {
		ingredients = new ArrayList<>();
	}

	public void setTimesRated() {
		this.timesRated++;
	}

	public double getRating() {
		return this.rating / this.timesRated;
	}

	public void setRating(double rating) {
		this.rating += rating;
	}

	public String getName() {
		return this.name;
	}

	public Meal setName(String name) {
		this.name = name;
		return this;
	}

	public ArrayList<Ingredient> getIgredients(){
		return ingredients;
	}
	
	public double getPrice() {
		return price;
	}

	public Meal setPrice(double price) {
		this.price = price;
		return this;
	}

	public MealType getCategory() {
		return category;
	}

	public void setCategory(MealType category) {
		this.category = category;
	}

	@Override
	public int compareTo(Meal o) {
		return (int) (price - o.price);
	}
	public String ingradientsToString() {
		StringBuilder allIngredientsWithNames = new StringBuilder();
		String r = "";
		for(Ingredient i : ingredients){
			allIngredientsWithNames.append(r+i.getName());
			r=", ";
		}
		return allIngredientsWithNames.toString();
	}
	public String toString() {
		StringBuilder allIngredientsWithNames = new StringBuilder("[");
		if(!ingredients.isEmpty()){
			for(int i = 0; i < ingredients.size() - 1; i++){
				allIngredientsWithNames.append(ingredients.get(i).getName() + ",");
			}
			allIngredientsWithNames.append(ingredients.get(ingredients.size() - 1).getName()); 
		}
		allIngredientsWithNames.append("]");
		return name + "\n Category: " + category + "\n Ingredients: " + allIngredientsWithNames + "\n Price: " + price;
	}

	public void addIngredients(Ingredient product) {
		this.ingredients.add(product);
	}

}
