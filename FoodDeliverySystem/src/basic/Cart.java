package basic;

import java.util.ArrayList;

public class Cart {
	ArrayList<Meal> products;
	double totalPrice;

	public ArrayList<Meal> getBasket() {
		return this.products;
	}

	void addToproducts(Meal product) {
		this.products.add(product);
		this.totalPrice += product.getPrice();
	}

	void removeFromproducts(Meal meal) {
		this.products.remove(meal);
		this.totalPrice -= meal.getPrice();
	}

	void emptyCart() {
		this.products.clear();
		this.totalPrice = 0;
	}

	double getTotalPrice() {
		return totalPrice;
	}

}
