package basic;

import java.util.ArrayList;

public class Cart {
	ArrayList<Meal> products;
	


	public ArrayList<Meal> getBasket() {
		return this.products;
	}
	
	void addToproducts(Meal product){
		this.products.add(product);
	}
	void removeFromproducts(Meal meal){
		this.products.remove(meal);
	}
	
	void emptyCart(){
		this.products.clear();
	}
	
	
}
