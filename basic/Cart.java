package basic;

import java.util.List;

public class Cart {
	List<Product> products;
	


	public List<Product> getBasket() {
		return this.products;
	}
	
	void addToproducts(Product product){
		this.products.add(product);
	}
	void removeFromproducts(){
		this.products.remove(products.size() - 1);
	}
	
	void emptyproducts(){
		this.products.clear();
	}
	
	
}
