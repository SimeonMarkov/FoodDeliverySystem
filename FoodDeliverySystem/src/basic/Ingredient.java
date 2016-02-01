package basic;

public class Ingredient {

	public enum IngredientsNames{
		//List of products that we can choose from
		TOMATOES,CHEESE,PORK,CHICKEN,CUCUMBERS,PICKLES,PAPRIKA
	}
	
	private IngredientsNames exactIngredient;
	private int uniqueID;
	private static int uniqueIdModifier = 0;

	public Ingredient(IngredientsNames name) {
		this.exactIngredient = name;
		this.uniqueID = uniqueIdModifier++;
	}
	
	public IngredientsNames getIngredientName(){
		return exactIngredient;
	}
	
	public int getIngredientUniqueID(){
		return uniqueID;
	}

}
