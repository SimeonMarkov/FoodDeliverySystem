package model.dao;

import java.sql.SQLException;
import java.util.List;

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

}
