package basic;


public class Product {
	private String name;
	private String price;
	private String category;
	private double rating;
	private int timesRated;
	public Product(String name, String price, String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public void setTimesRated(){
		this.timesRated++;
	}
	
	public double getRating(){
		return this.rating / this.timesRated;
	}
	
	public void setRating(double rating){
		this.rating += rating;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
