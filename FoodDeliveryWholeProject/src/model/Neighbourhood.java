package model;

public class Neighbourhood {
	
	private String name;
	private long id;
	
	public Neighbourhood() {
	}

	public String getName() {
		return name;
	}

	public Neighbourhood setName(String name) {
		this.name = name;
		return this;
	}

	public long getId() {
		return id;
	}

	public Neighbourhood setId(long id) {
		this.id = id;
		return this;
	}
	
	
}
