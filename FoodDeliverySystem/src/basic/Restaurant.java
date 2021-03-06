package basic;

import java.util.ArrayList;

public class Restaurant {
	private String name;
	private Address address;
	private String phoneNumber;
	private ArrayList<Meal> meals;
	private ArrayList<Neighbourhood> servedRegion;
	
	
    
	
	public Restaurant(String name, Address address, String phoneNumber) {
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
		this.meals = new ArrayList<Meal>();
	}
    
	public void addToServedRegion(Neighbourhood nbh) {
		this.servedRegion.add(nbh);
	}
	public ArrayList<Neighbourhood> getServedRegion() {
		return this.servedRegion;
	}
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		if(name != null){
			this.name = name;
		}
	}
	public Address getAddress() {
		return address;
	}
	private void setAddress(Address address) {
		if(address != null){
			this.address = address;
		}
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	private void setPhoneNumber(String phoneNumber) {
		if(phoneNumber.matches("08[0-9]{8}")){
			this.phoneNumber = phoneNumber;
		}
	}
	
	public ArrayList<Meal> getMeals(){
		return this.meals;
	}
	
	public void addMeal(Meal meal){
		//TODO: how to add the ingredients for our meal first,then add the meal itself to the restaurant's menu?
		this.meals.add(meal);
	}
	
	public void removeMeal(Meal meal){
		this.meals.remove(meal);
	}
	

	
	
}
