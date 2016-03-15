package model;

import java.util.ArrayList;
import java.util.Scanner;

import model.dao.IUserDAO;
import model.dao.IUserDAO.DataSource;

public class User {

	private String username;
	private String password;
	private String securityQuestion;
	private String securityAnswer;
	private String email;
	private ArrayList<Address> addressesArrayList;
	private boolean isRegistered;
	private boolean validLogIn;
	private String mobilePhone;
	private ArrayList<Order> ordersArchiveArrayList;
	private ArrayList<Restaurant> favoriteRestaurantsList;
	private ArrayList<Meal> favoriteMealsList;
	private Cart cart;

	private int favMealsNumber = 0;

	public User(String username, String password, String email,
			String securityQuestion,
			String securityAnswer) {
		this();
		this.setUsername(username);
		this.setPassword(password);
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.email = email;
		this.setRegistered(isRegistered);
	}
	public User(){
		addressesArrayList = new ArrayList<>();
		ordersArchiveArrayList = new ArrayList<>();
		favoriteMealsList = new ArrayList<>();
		favoriteRestaurantsList = new ArrayList<>();
		cart = new Cart();
	}
	
	public class Cart {
		ArrayList<Meal> products;
		double totalPrice;
		
		private Cart() {
			products = new ArrayList<Meal>();
		}
	}
	public User(String uid) {
		this();
		username=uid;
	}
	public void refreshOrders() {
		ordersArchiveArrayList = IUserDAO.getDAO(DataSource.DB).getOrdersArchiveDB(username);
	}
	public ArrayList<Meal> getBasket() {
		return this.cart.products;
	}
	void addProductInCart(Meal product) {
		this.cart.products.add(product);
		this.cart.totalPrice += product.getPrice();
	}
	void removeProductFromCart(Meal meal) {
		this.cart.products.remove(meal);
		this.cart.totalPrice -= meal.getPrice();
	}
	void emptyCart() {
		this.cart.products.clear();
		this.cart.totalPrice = 0;
	}
	double getTotalPriceOfCart() {
		return cart.totalPrice;
	}
	

	public String getUsername() {
		return this.username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Address> getAddresses() {
		return this.addressesArrayList;
	}

	public User addAddress(Address address) {
		this.addressesArrayList.add(address);
		return this;
	}

	public boolean isRegistered() {
		return this.isRegistered;
	}

	private void setRegistered(boolean isRegistered) {
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
	
	void rateMeal(Meal Meal, int rating) {
		Meal.setRating(rating);
		Meal.setTimesRated();
	}

	
	public Cart getCart() {
		return cart;
	}

	public void makeOrder() {
		ordersArchiveArrayList.add(new Order(getBasket(), getTotalPriceOfCart()));
		emptyCart();
	}
	public String getSecretQuestion() {
		return this.securityQuestion;
	}
	public String getSecretAnswer() {
		return this.securityAnswer;
	}

}