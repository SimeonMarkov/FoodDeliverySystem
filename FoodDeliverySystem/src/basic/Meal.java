package basic;

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
	     
	     
	     // TODO: MORE TO BE ADDED
	    
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

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Ingredient> getIgredients(){
		return ingredients;
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
		return (int) (price - o.price);
	}

	public String toString() {
		StringBuilder allIngredientsWithNames = new StringBuilder("[");
		if(!ingredients.isEmpty()){
			for(int i = 0; i < ingredients.size() - 1; i++){
				allIngredientsWithNames.append(ingredients.get(i).getIngredientName() + ",");
			}
			allIngredientsWithNames.append(ingredients.get(ingredients.size() - 1).getIngredientName()); 
		}
		allIngredientsWithNames.append("]");
		return name + "\n Category: " + category + "\n Ingredients: " + allIngredientsWithNames + "\n Price: " + price;
	}

	public void addIngredients(Ingredient product) {
		this.ingredients.add(product);
	}

}
