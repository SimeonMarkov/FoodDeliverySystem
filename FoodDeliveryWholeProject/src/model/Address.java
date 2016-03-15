package model;

public class Address {
	private String neighbourhood;
	private String fullAddress;
	
	public Address(String neighbourhood, String fullAddress) {
		this.setNeighbourhood(neighbourhood);
		this.setFullAddress(fullAddress);
	}
	public String getNeighbourhood() {
		return neighbourhood;
	}
	private void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	private void setFullAddress(String fullAddress) {
		if(fullAddress != null){
			this.fullAddress = fullAddress;
		}
	}
	
	public String toString(){ 
		return "Neighbourhood: " + this.neighbourhood + "\n Address: " + this.fullAddress;
	}
	
}
