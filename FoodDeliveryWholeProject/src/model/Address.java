package model;

public class Address {
	private long addressId;
	private long neighbourhoodId;
	private String neighbourhood;
	private String fullAddress;

	public Address() {
	// addni addressid/kvartalid/imenata na dvete
	}

	public Address(String neighbourhood, String fullAddress) {
		this.setNeighbourhood(neighbourhood);
		this.setFullAddress(fullAddress);
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public Address setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
		return this;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public Address setFullAddress(String fullAddress) {
		if (fullAddress != null) {
			this.fullAddress = fullAddress;
		}
		return this;
	}
	
	
	public long getAddressId() {
		return addressId;
	}

	public Address setAddressId(long addressId) {
		this.addressId = addressId;
		return this;
	}

	public long getNeighbourhoodId() {
		return neighbourhoodId;
	}

	public Address setNeighbourhoodId(long neighbourhoodId) {
		this.neighbourhoodId = neighbourhoodId;
		return this;
	}

	public String toString() {
		return "Neighbourhood: " + this.neighbourhood + "\n Address: " + this.fullAddress;
	}

}
