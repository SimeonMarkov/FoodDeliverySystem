package basic;
//comment in Address class this is intellij here gi4og fre
//comment in addressBranch
public class Address {
	private Neighbourhood neighbourhood;
	private String fullAddress;
	
	public Address(Neighbourhood neighbourhood, String fullAddress) {
		this.setNeighbourhood(neighbourhood);
		this.setFullAddress(fullAddress);
	}
	public Neighbourhood getNeighbourhood() {
		return neighbourhood;
	}
	private void setNeighbourhood(Neighbourhood neighbourhood) {
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
	
	public String toString(){ //TODO:Overriden toString() for addresses
		return "Neighbourhood: " + this.neighbourhood + "\n Address: " + this.fullAddress;
	}
	
}
