package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ingredient;
import model.Meal;
import model.MealType;
import model.RestaurantType;
import model.db.DBManager;

public class DBMealDAO implements IMealDAO {

	private static DBMealDAO instance;
	private DBManager manager;

	private DBMealDAO() {
		manager = DBManager.getInstance();
	}

	public static DBMealDAO getInstance() {
		if (instance == null) {
			instance = new DBMealDAO();
		}
		return instance;
	}

	@Override
	public Meal getMeal(long id) {
		String query = String.join("\n",
				"select m.meal_name,m.price,m.meal_type_id,photo,mt.meal_type_name,i.ingredients_id,i.ingredients_name from meal m",
				"join meal_type mt on(m.meal_type_id=mt.meal_type_id)",
				"join meal_ingredients mi on (m.meal_id=mi.meal_id)",
				"join ingredients i on (mi.ingredients_id=i.ingredients_id)", "where m.meal_id = ?;");
		Meal rv = null;
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)) {
			pst.setLong(1, id);
			ResultSet result = pst.executeQuery();
			if (result != null) {
				while (result.next()) {
					if (rv == null) {
						rv = new Meal().setMealId(id).setName(result.getString(1)).setPrice(result.getDouble(2))
								.setCategory(result.getString(5));

					}
					rv.addIngredients(new Ingredient(result.getString(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;

	}

	@Override
	public ArrayList<MealType> getMealTypes() {

		String query = "SELECT mt.meal_type_name,mt.meal_type_id from meal_type mt";
		ArrayList<MealType> rv = new ArrayList<>();
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)) {
			ResultSet result = pst.executeQuery();
			if (result != null) {
				while (result.next()) {
					rv.add(new MealType(result.getLong(2), result.getString(1)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;

	}

	@Override
	public ArrayList<Ingredient> getAllIngredients() {
		String query = "select ingredients_id,ingredients_name from ingredients;";
		ArrayList<Ingredient> rv = new ArrayList<>();
		try (PreparedStatement pst = manager.getConnection().prepareStatement(query)) {
			ResultSet result = pst.executeQuery();
			if (result != null) {
				while (result.next()) {
					rv.add(new Ingredient(result.getLong(1), result.getString(2)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

	@Override
	public ArrayList<Meal> getSearchResult(long restID, long mealTypeID, double price, ArrayList<Long> ingredients) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select m.meal_name,m.price,m.meal_type_id,photo,mt.meal_type_name,i.ingredients_id,i.ingredients_name,m.meal_id from meal m ");
		sb.append("join meal_type mt on(m.meal_type_id=mt.meal_type_id ) ");
		sb.append("join meal_ingredients mi on (m.meal_id=mi.meal_id) ");
		sb.append("join ingredients i on (mi.ingredients_id=i.ingredients_id) ");

		sb.append("where 1 = 1 ");
		if (price > 0) {
			sb.append("and m.price<" + price + " ");
		}
		if (mealTypeID >= 0) {
			sb.append("and mt.meal_type_id=" + mealTypeID + " ");
		}
		if (restID >= 0) {
			sb.append("and EXISTS(select 1 from restaurant_menu where restaurant_id=" + restID
					+ " and meal_id=m.meal_id) ");
		}
		if (ingredients.size() > 0) {
			sb.append("and exists(select 1 from meal_ingredients where meal_id = m.meal_id and ingredients_id in(");
			String delimiter = "";
			for (long l : ingredients) {
				sb.append(delimiter + l);
				delimiter = ",";
			}
			sb.append(")) ");
		}

		ArrayList<Meal> rv = new ArrayList<>();
		try (Statement st = manager.getConnection().createStatement()) {
			ResultSet result = st.executeQuery(sb.toString());
			Meal temp = null;
			long mealId = -1;
			if (result != null) {
				while (result.next()) {
					if (mealId != result.getLong(8)) {
						temp = new Meal().setMealId(result.getLong(8)).setName(result.getString(1))
								.setPrice(result.getDouble(2)).setCategory(result.getString(5));
						rv.add(temp);
						mealId=result.getLong(8);
					}
					temp.addIngredients(new Ingredient(result.getString(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rv;
	}

}
