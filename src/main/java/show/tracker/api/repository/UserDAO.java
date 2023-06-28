package show.tracker.api.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import show.tracker.api.model.User;

@Repository
public class UserDAO extends DAO<User> {

	@Override
	public boolean insert(User obj) {
		boolean result = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("INSERT INTO user_movie "
					+ "(email, password, first_name, last_name, birth_date) " + "VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getEmail());
			stmt.setString(2, obj.getPassword());
			stmt.setString(3, obj.getFisrtName());
			stmt.setString(4, obj.getLastName());
			stmt.setString(5, obj.getBirthDate());
			result = (stmt.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return result;
	}

	public User getOne(String email, String password) {
		User result = null;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement(
					"SELECT email, password, first_name, last_name, birth_date FROM user_movie WHERE email = ? AND password = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			boolean found = false;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String u_email = rs.getString("email");
				String u_password = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String birth_date = rs.getString("birth_date");
				found = true;
				User user = new User(first_name, last_name, birth_date, u_email, u_password);
				result = user;
			}
			rs.close();
			stmt.close();
			c.close();
			if (!found) {
				System.out.println("Nenhum registro encontrado");
			}
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return result;
	}

	public boolean deleteAll() {
		boolean result = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("DELETE FROM user_movie");
			result = (stmt.executeUpdate() == 1);
			stmt.close();
			c.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return result;
	}

	public boolean deleteUser(String email) {
		boolean result = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("DELETE FROM user_movie WHERE email = ?");
			stmt.setString(1, email);
			result = (stmt.executeUpdate() == 1);
			stmt.close();
			c.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public User getOne(int id) {
		return null;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public List<User> getAll() {
		return null;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public boolean update(User obj) {
		return false;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public boolean delete(int id) {
		return false;
	}

}
