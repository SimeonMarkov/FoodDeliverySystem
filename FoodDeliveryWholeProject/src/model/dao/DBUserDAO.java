package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.db.DBManager;


public class DBUserDAO implements IUserDAO{
	
	private static DBUserDAO instance;
	private DBManager manager;
	
	private DBUserDAO(){
		manager = DBManager.getInstance();
	}
	
	public static DBUserDAO getInstance(){
		if(instance == null){
			instance = new DBUserDAO();
		}
		return instance;
	}

	@Override
	public boolean addUser(User newUser) {
		boolean success = true;
		String query = "INSERT INTO USERS (user_id, user_pass, email, secret_question, secret_answer) VALUES (?, ?, ?, ?, ?);";
		try(PreparedStatement st = manager.getConnection().prepareStatement(query);) {
			st.setString(1, newUser.getUsername());
			st.setString(2, newUser.getPassword());
			st.setString(3, newUser.getEmail());
			st.setString(4, newUser.getSecretQuestion());
			st.setString(5, newUser.getSecretAnswer());
			st.execute();
			} catch (SQLException e) {
			success = false;
		}
		return success;
	}

	@Override
	public void updateUser(User loggedUser, String newPassword, String email) {
		String passQuery = "UPDATE fd_db.USERS SET user_pass = ? WHERE user_id = ?";
		try(PreparedStatement st = manager.getConnection().prepareStatement(passQuery);){
			st.setString(1, newPassword);
			st.setString(2, loggedUser.getUsername());
			st.executeUpdate();
			System.out.println("user updated");
		} catch (SQLException e) {
			System.out.println("Wrong update query!");
		}
		String emailQuery = "UPDATE fd_db.USERS SET email = ? WHERE user_id = ?";
		try(PreparedStatement st = manager.getConnection().prepareStatement(emailQuery);){
			st.setString(1, email);
			st.setString(2, loggedUser.getUsername());
			st.executeUpdate();
			System.out.println("user updated");
		} catch (SQLException e) {
			System.out.println("Wrong update query!");
		}
		
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		System.out.println("geta all users invoked");
		String query = "SELECT user_id, user_pass, email, secret_question, secret_answer FROM fd_db.USERS;";
		List<User> users = new ArrayList<>();
		Statement st = manager.getConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		System.out.println("result = " + result);
		if(result == null){
			st.close();
			return users;
		}
		while(result.next()){
			User u = new User(result.getString(1),
							  result.getString(2),
					          result.getString(3),
					          result.getString(4),
					          result.getString(5));
			System.out.println("user added");
			users.add(u);
		}
		st.close();
		return users;
	}
}
