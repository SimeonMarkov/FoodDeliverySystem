package basic;



public class Meal implements Comparable<Meal>{ //TODO: the old class Product is called Meal now;also, we created Enum called Product,for keeping ingredients as constants
	private String name;
	private double price;
	private String category;
	private double rating;
	private int timesRated;
	private Product[] ingredients; //TODO: A meal has ingredients
	public Meal(String name, double price, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.timesRated = 0;
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
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int compareTo(Meal o) {
		return (int)(price - o.price);
	}
	
	public String toString(){
		return name + " " + price + " " + category;
	}

}
