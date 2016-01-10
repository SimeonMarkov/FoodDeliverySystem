package basic;

import java.util.List;


public class User {
	
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private List<Address> addressesList;
	private boolean isRegistered;
	private String mobilePhone;
	private List<Order> ordersArchiveList;
	private List<Restaurant> favoriteRestaurantsList;
	private List<Product> favoriteMealsList;
	private Cart cart;
	
	private int favMealsNumber = 0;
	
	
	
	public User(String username, String password, String email,
			String firstName, String lastName, List<Address> addressesList,
			boolean isRegistered, String mobilePhone,
			List<Order> ordersArchiveList,
			List<Restaurant> favoriteRestaurantsList,
			List<Product> favoriteMealsList, Cart cart) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressesList = addressesList;
		this.isRegistered = isRegistered;
		this.mobilePhone = mobilePhone;
		this.ordersArchiveList = ordersArchiveList;
		this.favoriteRestaurantsList = favoriteRestaurantsList;
		this.favoriteMealsList = favoriteMealsList;
		this.cart = cart;
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


	public List<Address> getAddresses() {
		return this.addressesList;
	}


	public void setAddress(Address address) {
		this.addressesList.add(address);
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


	public List<Order> getOrdersArchive() {
		return this.ordersArchiveList;
	}


	public void setOrdersArchive(Order order) {
		this.ordersArchiveList.add(order);
	}


	public List<Restaurant> getFavoriteRestaurants() {
		return this.favoriteRestaurantsList;
	}


	public void setFavoriteRestaurants(Restaurant restaurant) {
		this.favoriteRestaurantsList.add(restaurant);
	}


	public List<Product> getFavoriteMeals() {
		return this.favoriteMealsList;
	}


	public void setFavoriteMeals(Product meal) {
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
	
	
	void rateProduct(Product product, int rating){
		product.setRating(rating);
		product.setTimesRated();
	}
	
	void search(){
		
	}
}