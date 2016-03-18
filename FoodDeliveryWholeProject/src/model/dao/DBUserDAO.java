package model.dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import model.Address;
import model.Ingredient;
import model.Meal;
import model.Neighbourhood;
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

	public List<Address> selectAddresses(User user) {
		List<Address> addresses = new ArrayList<Address>();
		String query = String.join("\n",
				"SELECT neighbourhood_name,full_address,A.address_id,N.neighbourhood_id FROM fd_db.Neighbourhood N",
				"INNER JOIN fd_db.Address A ON N.neighbourhood_id = A.neighbourhood_id",
				"INNER JOIN fd_db.Users U ON A.user_id = U.user_id", "WHERE U.user_id = ?");
		try (PreparedStatement stmt = manager.getConnection().prepareStatement(query)) {
			stmt.setString(1, user.getUsername());
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				addresses.add(new Address(result.getString(1), result.getString(2)).setAddressId(result.getLong(3))
						.setNeighbourhoodId(result.getLong(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addresses;
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
		// return Collections.unmodifiableList(users);
	}

	@Override
	public ArrayList<Order> getOrdersArchiveDB(String username) {
		String query = String.join("\n",
				"SELECT o.order_id,om.meal_id,order_time,o.total_price,o.restaurant_id from orders o" ,
				"join ordered_meals om on(o.order_id=om.order_id) ",
				"where user_id=? order by o.order_id,om.meal_id");

		ArrayList<Order> rv = new ArrayList<>();

		try (PreparedStatement st = manager.getConnection().prepareStatement(query)) {
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			if (result != null) {
				int lastOrder = -1;
				Order o = null;
				while (result.next()) {
					if (lastOrder != result.getInt(1)) {
						o = new Order().setPrice(result.getDouble(4)).setDate(result.getDate(3))
								.setRestaurant(DBRestaurantDAO.getInstance().getRestaurantsById(result.getLong(5)));
						rv.add(o);
						lastOrder = result.getInt(1);
					}
					o.addMeal(Meal.getMealByID(result.getLong(2)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rv;
	}

	@Override
	public boolean addAddress(User newUser, long nId, String fullAddress) {
		boolean success = true;

		String query = "INSERT INTO fd_db.ADDRESS (full_address, neighbourhood_id, user_id) VALUES (?, ?, ?);";
		try (PreparedStatement st = manager.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);) {
			st.setString(1, fullAddress);
			st.setLong(2, nId);
			st.setString(3, newUser.getUsername());
			st.executeUpdate();
			ResultSet tableKeys = st.getGeneratedKeys();
			tableKeys.next();
			int addressId = tableKeys.getInt(1);
			
			newUser.addAddress(Address.getAddressByID(addressId));
			
		} catch (SQLException e) {
			success = false;
		}
		return success;
	}

	@Override
	public ArrayList<Address> getAddresses(String username) {
		ArrayList<Address> rv = new ArrayList<>();
		String query = String.join("\n",
				"select a.address_id ,a.full_address,a.neighbourhood_id,n.neighbourhood_name from address a",
				"join neighbourhood n on (n.neighbourhood_id= a.neighbourhood_id)", "where user_id = ?;");
		try (PreparedStatement st = manager.getConnection().prepareStatement(query)) {
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			if (result != null) {
				while (result.next()) {
					rv.add(new Address().setAddressId(result.getLong(1)).setNeighbourhoodId(result.getLong(3))
							.setNeighbourhood(result.getString(4)).setFullAddress(result.getString(2)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rv;
	}

	@Override
	public ArrayList<Order> getAllOrdersArchive() {
		String query = String.join("\n",
				"SELECT o.order_id,om.meal_id,order_time,o.total_price,o.restaurant_id from orders o" ,
				"join ordered_meals om on(o.order_id=om.order_id) ",
				"order by o.order_id,om.meal_id");
		ArrayList<Order> rv = new ArrayList<>();
		try (PreparedStatement st = manager.getConnection().prepareStatement(query)) {
			ResultSet result = st.executeQuery();
			if (result != null) {
				int lastOrder = -1;
			
				Order o = null;
			
				while (result.next()) {
					if (lastOrder != result.getInt(1)) {
						o = new Order().setPrice(result.getDouble(4)).setDate(result.getDate(3))
								.setRestaurant(DBRestaurantDAO.getInstance().getRestaurantsById(result.getLong(5)));
						rv.add(o);
						lastOrder = result.getInt(1);
					}
					o.addMeal(Meal.getMealByID(result.getLong(2)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rv;

	}

	@Override
	public Address getAddressByID(long id) {
		String query = "select address_id,full_address,a.neighbourhood_id,n.neighbourhood_name from address a join neighbourhood n on(a.neighbourhood_id=n.neighbourhood_id) where address_id=?;";
		Address rv = null;
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)) {
			pst.setLong(1, id);
			ResultSet result = pst.executeQuery();
			if (result != null) {
				while (result.next()) {
					rv = new Address().setAddressId(result.getLong(1)).setFullAddress(result.getString(2))
							.setNeighbourhoodId(result.getLong(3)).setNeighbourhood(result.getString(4));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;

	}

	@Override
	public void saveCart(String username, ArrayList<Meal> meals, long restId, double totalPrice, long addressId) {
		
		int orderId=0;
		try (PreparedStatement pst = manager.getConnection()
				.prepareStatement("INSERT into orders (order_time,order_finished,total_price,address_id,restaurant_id,user_id) VALUES (?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS)) {
			pst.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pst.setDouble(3, totalPrice);
			pst.setLong(4, addressId);
			pst.setLong(5, restId);
			pst.setString(6,username);

			pst.executeUpdate();

			ResultSet tableKeys = pst.getGeneratedKeys();
			tableKeys.next();
			orderId = tableKeys.getInt(1);
			
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try(PreparedStatement pst = manager.getConnection().prepareStatement("INSERT into ordered_meals values (?,?)")) {
			pst.setLong(1, orderId);
			for(Meal m : meals) {
				pst.setLong(2,m.getMealId());
				pst.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
