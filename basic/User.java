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
		this.username = username;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
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

	void register() {
		// TODO: how does one make a registration Rado : tova ne e li nashiq
		// konstruktor?
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
			retypePassword();
		}

		if (validLogIn)
			return "Komanda da minem v post-login stranicata";
		return "Invalid password/securedAnswer";

	}

	private void retypePassword() {
		int count = 0;
		do {
			System.out.println("Greshna parola, molq vuvedete korektna parola!");
			password = sc.nextLine();
			count++;
		} while (password.equals(getPassword()) || count == 3);
		if (password.equals(getPassword())) {
			validLogIn = true;
			return;
		} else {
			secureQuestion();
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

}