package model;

public class Ingredient {

	// public enum IngredientsNames{
	// //List of products that we can choose from
	// TOMATOES,CHEESE,PORK,CHICKEN,CUCUMBERS,PICKLES,PAPRIKA
	// }
	// private IngredientsNames exactIngredient;
	//
	// private int uniqueID;
	// private static int uniqueIdModifier = 0;
	// public Ingredient(IngredientsNames name) {
	// this.exactIngredient = name;
	// this.uniqueID = uniqueIdModifier++;
	// }
	// public IngredientsNames getIngredientName(){
	// return exactIngredient;
	// }
	// public int getIngredientUniqueID(){
	// return uniqueID;
	// }
	private String name;

	public Ingredient() {
		name = "";
	}

	public Ingredient(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
