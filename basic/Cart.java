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
	void removeFromproducts(){
		this.products.remove(products.size() - 1);
	}
	
	void emptyCart(){
		this.products.clear();
	}
	
	
}
