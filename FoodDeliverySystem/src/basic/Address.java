package basic;

public class Address {
	private Neighbourhood neighbourhood;
	private String fullAddress;
	
	public Address(Neighbourhood neighbourhood, String fullAddress) {
		this.neighbourhood = neighbourhood;
		this.fullAddress = fullAddress;
	}
	public Neighbourhood getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(Neighbourhood neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	
	
}
