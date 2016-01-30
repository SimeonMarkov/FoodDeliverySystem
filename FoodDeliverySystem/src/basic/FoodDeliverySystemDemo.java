package basic;

import java.util.ArrayList;
import java.util.Collections;


public class FoodDeliverySystemDemo {
	
	public static void main(String[] args) {
		
		//from this instance,we can have access to multiple restaurants
		Site site = Site.getSiteInstance();
		
		//create 2 restaurants
		Restaurant djumaq = new Restaurant("Djumaq", new Address(Neighbourhood.DRUZHBA ,"\"ul.Atanas Manchev 52\""), "0889203441");
		Restaurant priKiko = new Restaurant("Pri Kiko", new Address(Neighbourhood.OBORISHTE ,"\"bul.Hristo Botev 154\""), "0884392112");
		
		//add the restaurants to the site
		site.addRestaurant(djumaq);
		site.addRestaurant(priKiko);
		
		System.out.println(djumaq.getAddress());
		
		//create some Meals(meals)
		Meal meal1 = new Meal("Pile s oriz", 5.60, MealType.CHICKEN);
		Meal meal2 = new Meal("Pile bez oriz", 5.60, MealType.CHICKEN);
		Meal meal3 = new Meal("Super mandja s grozde", 2.30, MealType.ALAMINUT);
		Meal someMeal = new Meal("Grozdova", 8.30, MealType.STRONG_ALCOHOL);
		
		System.out.println("------------------");
		
		djumaq.addMeal(meal1);
		djumaq.addMeal(meal2);
		djumaq.addMeal(meal3);
		
		priKiko.addMeal(meal1);

		//printing a meal with its name,ingredients,price and category
		//TODO:see the TODO: for addMeal() in Restaurant class and fix the empty ingredients list
		System.out.println(meal2);
		
		System.out.println("Na \"Djumaq\" menuto:");
		System.out.println(djumaq.getMeals());
		System.out.println("Chakai da go sortirame malko po cena,de:");
		Collections.sort(djumaq.getMeals());
		System.out.println(djumaq.getMeals());
		
		User user1 = User.register("username", "password", "simeon.markov1994@gmail.com", "Simeon", "Markov", new ArrayList<Address>(), true, "0889342343", "Where", "There");
		System.out.println(user1.logIn());
		
		user1.search(someMeal);
	}
}
