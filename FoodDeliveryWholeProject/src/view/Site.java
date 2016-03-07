package view;

import java.util.ArrayList;

public class Site { //TODO: we can use this site class to keep multiple restaurants in it;that's why we use Singleton for it - we need only 1 instance of the site
	private static Site site = new Site();
	private static ArrayList<Restaurant> allrestaurants;
	
	private Site(){
		Site.allrestaurants = new ArrayList<Restaurant>();
	}
	
	public static Site getSiteInstance(){
		if(site == null){
			site = new Site();
		}
		return site;
	}
	
	public static ArrayList<Restaurant> getAllRestaurants(){
		return allrestaurants;
	}
	
	public void addRestaurant(Restaurant restaurant){
		Site.allrestaurants.add(restaurant);
	}
}
