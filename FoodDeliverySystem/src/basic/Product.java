package basic;

public class Product {

	private ProductNames name;
	private int uniqueID;
	private static int uniqueIdModifier = 0;

	public Product(ProductNames name) {
		this.name = name;
		this.uniqueID = uniqueIdModifier++;
	}

}
