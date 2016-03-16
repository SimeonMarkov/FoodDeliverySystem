package model;

import java.util.ArrayList;

import model.dao.IMealDAO;
import model.dao.IMealDAO.DataSource;

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
	private long id;

	public Ingredient() {
		name = "";
	}

	public Ingredient(String name) {
		this.name = name;
	}
	public Ingredient(long id,String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public static ArrayList<Ingredient>  getAllIngredients() {
		return IMealDAO.getDAO(DataSource.DB).getAllIngredients();
	}
	

}
