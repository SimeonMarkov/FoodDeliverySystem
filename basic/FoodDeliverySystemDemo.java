package basic;

import java.util.Collections;


public class FoodDeliverySystemDemo {
	
	public static void main(String[] args) {
		
		//from this instance,we can have access to multiple restaurants
		Site site = Site.getSiteInstance();
		
		//create 2 restaurants
		Restaurant djumaq = new Restaurant("Djumaq", new Address("Stud.grad","ul.Atanas Manchev 52"), "0889203441");
		Restaurant priKiko = new Restaurant("Pri Kiko", new Address("Center","bul.Hristo Botev 154"), "0884392112");
		
		//add the restaurants to the site
		site.addRestaurant(djumaq);
		site.addRestaurant(priKiko);
		
		
		//create some Meals(meals)
		Meal meal1 = new Meal("Pile s oriz", 5.60, MealType.CHICKEN);
		Meal meal2 = new Meal("Pile bez oriz", 5.60, MealType.CHICKEN);
		Meal meal3 = new Meal("Super mandja s grozde", 2.30, MealType.ALAMINUT);
		Meal someMeal = new Meal("Grozdova", 8.30, MealType.STRONG_ALCOHOL);
		
		
		
		djumaq.addMeal(meal1);
		djumaq.addMeal(meal2);
		djumaq.addMeal(meal3);
		
		priKiko.addMeal(meal1);
		
		
		System.out.println("Na \"Djumaq\" menuto:");
		System.out.println(djumaq.getMeals());
		System.out.println("Chakai da go sortirame malko po cena,de:");
		Collections.sort(djumaq.getMeals());
		System.out.println(djumaq.getMeals());
	}
}
