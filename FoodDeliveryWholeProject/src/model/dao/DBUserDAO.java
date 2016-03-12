package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ingredient;
import model.Meal;
import model.Order;
import model.User;
import model.db.DBManager;

public class DBUserDAO implements IUserDAO {

	private static DBUserDAO instance;
	private DBManager manager;

	private DBUserDAO() {
		manager = DBManager.getInstance();
	}

	public static DBUserDAO getInstance() {
		if (instance == null) {
			instance = new DBUserDAO();
		}
		return instance;
	}

	@Override
	public boolean addUser(User newUser) {
		boolean success = true;
		String query = "INSERT INTO USERS (user_id, user_pass, email, secret_question, secret_answer) VALUES (?, ?, ?, ?, ?);";
		try (PreparedStatement st = manager.getConnection().prepareStatement(query);) {
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
		try (PreparedStatement st = manager.getConnection().prepareStatement(passQuery);) {
			st.setString(1, newPassword);
			st.setString(2, loggedUser.getUsername());
			st.executeUpdate();
			System.out.println("user updated");
		} catch (SQLException e) {
			System.out.println("Wrong update query!");
		}
		String emailQuery = "UPDATE fd_db.USERS SET email = ? WHERE user_id = ?";
		try (PreparedStatement st = manager.getConnection().prepareStatement(emailQuery);) {
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
		if (result == null) {
			st.close();
			return users;
		}
		while (result.next()) {
			User u = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
					result.getString(5));
			System.out.println("user added");
			users.add(u);
		}
		st.close();
		return users;
	}

	@Override
	public ArrayList<Order> getOrdersArchiveDB(String username) {
		String query = String.join("\n",
				"SELECT o.order_id,m.meal_id,i.ingredients_id,order_time,o.total_price,m.meal_name,m.price,m.photo,i.ingredients_name from orders o",
				"join ordered_meals om on(o.order_id=om.order_id)", "join meal m on(m.meal_id=om.meal_id) ",
				"join meal_ingredients mi on (m.meal_id=mi.meal_id) ",
				"join ingredients i on (i.ingredients_id=mi.ingredients_id) ",
				"where user_id=? order by o.order_id,m.meal_id,i.ingredients_id;");

		ArrayList<Order> rv = new ArrayList<>();

		try (PreparedStatement st = manager.getConnection().prepareStatement(query)) {
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			if (result != null) {
				int lastOrder = -1;
				int lastMeal = -1;
				int lastIngredient = -1;
				Order o = null;
				Meal m = null;
				while (result.next()) {
					if (lastOrder != result.getInt(1)) {
						o = new Order().setPrice(result.getDouble(5)).setDate(result.getDate(4));
						rv.add(o);
						lastOrder = result.getInt(1);
					}
					if(lastMeal!=result.getInt(2)){
						m = new Meal().setName(result.getString(6)).setPrice(result.getDouble(7));
						o.addMeal(m);
						lastMeal=result.getInt(2);
						//TODO add photo to meal
					}
					m.addIngredients(new Ingredient(result.getString(9)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rv;
	}

	@Override
	public boolean addAddress(User newUser, String neighbourhood, String fullAddress) {
		boolean success = true;
		String neighbourhoodQuery = "SELECT neighbourhood_id FROM fd_db.neighbourhood WHERE neighbourhood_name = ?";
		int neighbourhood_id = 0;
		try(PreparedStatement preparedStatement = manager.getConnection().prepareStatement(neighbourhoodQuery);){
			preparedStatement.setString(1, neighbourhood);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				neighbourhood_id = rs.getInt(1); 
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = "INSERT INTO fd_db.ADDRESS (full_address, neighbourhood_id, user_id) VALUES (?, ?, ?);";
		try (PreparedStatement st = manager.getConnection().prepareStatement(query);) {
			st.setString(1, fullAddress);
			st.setInt(2, neighbourhood_id);
			st.setString(3, newUser.getUsername());
			st.execute();
		} catch (SQLException e) {
			success = false;
		}
		return success;
	}

}
