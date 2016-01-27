package basic;

public class Address {
	private String neighbourhood;
	private String fullAddress;
	public Address(String neighbourhood, String fullAddress) {
		this.neighbourhood = neighbourhood;
		this.fullAddress = fullAddress;
	}
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	
	
}
