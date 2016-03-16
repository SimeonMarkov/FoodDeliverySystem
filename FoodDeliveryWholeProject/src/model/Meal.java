package model;

import java.util.ArrayList;

import model.dao.DBMealDAO;

public class Meal implements Comparable<Meal> {
	
	// public enum MealType {
	// SALAD,SOUP,PASTA,PIZZA,ALAMINUT,BBQ,FISH,BREAD,SOUCE,DESERT,DRINKS,VEGETERIAN,OSNOVNO,
	// // POD VIDOVE ZA NQKOI OT IASTIATA S VID MESO ILI BBQ
	// PORK,CHICKEN,BEEF,DUCK,
	// // POD VID ZA PASTA
	// SPAGHETTI,LASAGNA,
	// // POD VID DRINKS
	// BEER,WINE,STRONG_ALCOHOL,NON_ALCOHOL
	//
	//
	//
	// }
	//
	
	private long mealId;
	private String name;
	private double price;
	private String category;
	//private double rating;
	//private int timesRated;
	private ArrayList<Ingredient> ingredients;
	

	public Meal(String name, double price, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
		//this.timesRated = 0;
		this.ingredients = new ArrayList<Ingredient>();
	}
	public Meal() {
		ingredients = new ArrayList<>();
	}

//	public void setTimesRated() {
//		this.timesRated++;
//	}
	public long getMealId() {
		return mealId;
	}
	public Meal setMealId(long mealId) {
		this.mealId = mealId;
		return this;
	}
	
	
	public static Meal getMealByID(long id) {
		return DBMealDAO.getInstance().getMeal(id);
	}
	
	public static ArrayList<Meal> getSearchResult(long restID, long mealTypeID, double price, ArrayList<Long> ingredients){
		return DBMealDAO.getInstance().getSearchResult(restID, mealTypeID, price, ingredients);
	}
//
//	public double getRating() {
//		return this.rating / this.timesRated;
//	}
//
//	public void setRating(double rating) {
//		this.rating += rating;
//	}

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

	public String getCategory() {
		return category;
	}

	public Meal setCategory(String category) {
		this.category = category;
		return this;
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
