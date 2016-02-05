package basic;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

	private String username;
	private String password;
	private String securityQuestion;
	private String securityAnswer;
	private String email;
	private String firstName;
	private String lastName;
	private ArrayList<Address> addressesArrayList;
	private boolean isRegistered;
	private boolean validLogIn;
	private String mobilePhone;
	private ArrayList<Order> ordersArchiveArrayList;
	private ArrayList<Restaurant> favoriteRestaurantsList;
	private ArrayList<Meal> favoriteMealsList;
	private Cart cart;

	private int favMealsNumber = 0;

	Scanner sc = new Scanner(System.in); // v imeto na testa da se vuvejda ot
											// nqkude parolata i t.n.

	private User(String username, String password, String email, String firstName, String lastName,
			ArrayList<Address> addressesArrayList, boolean isRegistered, String mobilePhone, String securityQuestion,
			String securityAnswer) {
		this.setUsername(username);
		this.setPassword(password);
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressesArrayList = addressesArrayList;
		this.setRegistered(isRegistered);
		this.mobilePhone = mobilePhone;
		this.cart = new Cart();
	}
	public class Cart {
		ArrayList<Meal> products;
		double totalPrice;
		Cart() {
			products = new ArrayList<Meal>();
		}
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

	public void addAddress(Address address) {
		this.addressesArrayList.add(address);
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

	static User register(String username, String password, String email, String firstName, String lastName,
			ArrayList<Address> addressesArrayList, boolean isRegistered, String mobilePhone, String securityQuestion,
			String securityAnswer) {
		// TODO: how does one make a registration Rado : tova ne e li nashiq
		// konstruktor?

		return new User(username, password, email, firstName, lastName, addressesArrayList, isRegistered, mobilePhone,
				securityQuestion, securityAnswer);
	}

	String logIn() {
		// TODO: rado: Proverete Login-a dali vurshi rabota ili sam izpysnal
		// neshto vajno
		validLogIn = true;
		System.out.println("molq vuvedete username");
		String username = sc.nextLine();
		if (!username.equals(getUsername())) {
			validLogIn = false;
			return "komanda za greshen username";
		}
		System.out.println("molq vuvedete parola");
		String password = sc.nextLine();
		if (!password.equals(getPassword())) {
			validLogIn = false;
			System.out.println("Greshna parola, molq vuvedete korektna parola!");
			retypePassword();
		}

		if (validLogIn)
			return "Komanda da minem v post-login stranicata";
		return "Invalid password/securedAnswer";

	}

	private void retypePassword() {
		int count = 1; // he already typed the password wrong once,that's why
						// he's RETYPING
		String password = "";
		do {
			password = sc.nextLine();
			if (!password.equals(getPassword())) {
				System.out.println("Greshna parola, molq vuvedete korektna parola!");
				count++;
			}
		} while (!password.equals(getPassword()) && count <= 2);
		if (password.equals(getPassword())) {
			validLogIn = true;
			return;
		} else {
			secureQuestion();
			retypePassword();
		}

	}

	private void secureQuestion() {
		System.out.println(securityQuestion);
		System.out.println("Molq vuvedete tainiqt otgovor");
		String answer = sc.nextLine();
		if (answer.equals(this.securityAnswer)) {
			System.out.println("Suzdaite nova parola");
			setPassword("nova parola"); // trqbva da se prepravqt povecheto set
										// methodi
		} else {
			System.out.println("greshen otgovor , blokirame ipaddress za N minuti");
		}

	}

	void rateMeal(Meal Meal, int rating) {
		Meal.setRating(rating);
		Meal.setTimesRated();
	}

	void search(Meal Meal) {
		boolean isMeal = false;
		for (Restaurant restaurant : Site.getAllRestaurants()) {
			if (restaurant.getMeals().contains(Meal)) {
				isMeal = true;
				System.out.println(restaurant.getName() + " : " + Meal);
			}
		}
		if (!isMeal) {
			System.out.println("No " + Meal + " found.");
		}
	}

	public Cart getCart() {
		return cart;
	}

	public void makeOrder() {
		ordersArchiveArrayList.add(new Order(getBasket(), getTotalPriceOfCart()));
		emptyCart();
	}

}