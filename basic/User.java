package basic;

import java.util.ArrayList;


public class User {
	
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private ArrayList<Address> addressesArrayList;
	private boolean isRegistered;
	private String mobilePhone;
	private ArrayList<Order> ordersArchiveArrayList;
	private ArrayList<Restaurant> favoriteRestaurantsList;
	private ArrayList<Meal> favoriteMealsList;
	private Cart cart;
	
	
	private int favMealsNumber = 0;
	
	
	
	public User(String username, String password, String email,
			String firstName, String lastName, ArrayList<Address> addressesArrayList,
			boolean isRegistered, String mobilePhone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressesArrayList = addressesArrayList;
		this.isRegistered = isRegistered;
		this.mobilePhone = mobilePhone;
	}

	
	public String getUsername() {
		return this.username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return this.firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return this.lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public ArrayList<Address> getAddresses() {
		return this.addressesArrayList;
	}


	public void setAddress(Address address) {
		this.addressesArrayList.add(address);
	}


	public boolean isRegistered() {
		return this.isRegistered;
	}


	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}


	public String getMobilePhone() {
		return this.mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public ArrayList<Order> getOrdersArchive() {
		return this.ordersArchiveArrayList;
	}


	public void setOrdersArchive(Order order) {
		this.ordersArchiveArrayList.add(order);
	}


	public ArrayList<Restaurant> getFavoriteRestaurants() {
		return this.favoriteRestaurantsList;
	}


	public void setFavoriteRestaurants(Restaurant restaurant) {
		this.favoriteRestaurantsList.add(restaurant);
	}


	public ArrayList<Meal> getFavoriteMeals() {
		return this.favoriteMealsList;
	}


	public void setFavoriteMeals(Meal meal) {
		this.favoriteMealsList.add(meal);
	}



	public int getFavMealsNumber() {
		return this.favMealsNumber;
	}


	public void setFavMealsNumber(int favMealsNumber) {
		this.favMealsNumber = favMealsNumber;
	}


	void register(){
		//TODO: how does one make a registration
	}
	
	void logIn(){
		//TODO: how does one login in his profile
	}
	
	
	void rateMeal(Meal Meal, int rating){
		Meal.setRating(rating);
		Meal.setTimesRated();
	}
	
	void search(Meal Meal){
		boolean isMeal = false;
		for(Restaurant restaurant:Site.getAllRestaurants()){
			if(restaurant.getMeals().contains(Meal)){
				isMeal = true;
				System.out.println(Meal);
			}
		}
		if(!isMeal){
			System.out.println("No " + Meal + " found.");
		}
	}


	public Cart getCart() {
		return cart;
	}

}