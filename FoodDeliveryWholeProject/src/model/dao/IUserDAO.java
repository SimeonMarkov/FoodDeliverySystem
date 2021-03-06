package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Meal;
import model.Neighbourhood;
import model.Order;
import model.User;

public interface IUserDAO {
	
	enum DataSource {
		DB, JSON, XML, CSV
	}
	
	public boolean addUser(User user);
	
	List<User> getAllUsers() throws SQLException;
	
	static IUserDAO getDAO(DataSource ds) {
		switch (ds) {
		case DB:
			return DBUserDAO.getInstance();
		default:
			throw new IllegalArgumentException();
		}
	}

	void updateUser(User loggedUser, String newPassword, String email);

	public ArrayList<Order> getOrdersArchiveDB(String username);
	
	public ArrayList<Order> getAllOrdersArchive();

	public boolean addAddress(User newUser, long nId,String fullAddress);
	
	public ArrayList<Address> getAddresses(String username);
	
	public Address getAddressByID(long id);
	
	public void saveCart(String username,ArrayList<Meal> meals,long restId,double totalPrice,long addressId);

}
