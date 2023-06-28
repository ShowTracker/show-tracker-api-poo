package show.tracker.api.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import show.tracker.api.model.WatchedList;
import show.tracker.api.model.media.Film;
import show.tracker.api.model.media.Media;
import show.tracker.api.model.media.TvShow;

public class WatchedListDAO extends DAO<WatchedList> {

	public boolean insert(String email) {
		boolean result = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("INSERT INTO whatched_list (user_email) VALUES (?)");
			stmt.setString(1, email);
			result = (stmt.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return result;
	}

	public WatchedList getOne(String email) {
		WatchedList result = null;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c
					.prepareStatement("SELECT id, user_email FROM whatched_list WHERE user_email = ?");
			stmt.setString(1, email);
			boolean found = false;
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String u_email = rs.getString("user_email");
				found = true;
				WatchedList wl = new WatchedList(id, u_email);
				result = wl;
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

	public List<Media> getAllUserMedia(int id) {
		List<Media> resultado = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement(
					"SELECT me.id, me.title, title_type, duration, is_adult, year, end_year FROM media AS me, wl_media AS wm WHERE me.id = wm.media_id AND wm.wl_id = ?");
			stmt.setInt(1, id);
			boolean encontrou = false;
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String title_type = rs.getString("title_type");
				if ("movie".equals(title_type)) {
					int codigo = rs.getInt("id");
					String title = rs.getString("title");
					String year = rs.getString("year");
					boolean isAdult = rs.getBoolean("is_adult");
					String duration = rs.getString("duration");

					encontrou = true;

					Media f = new Film(duration, codigo, year, title, isAdult);
					resultado.add(f);
				} else {
					int codigo = rs.getInt("id");
					String title = rs.getString("title");
					String year = rs.getString("year");
					boolean isAdult = rs.getBoolean("is_adult");
					String endYear = rs.getString("end_year");

					encontrou = true;

					Media ts = new TvShow(endYear, codigo, year, title, isAdult);
					resultado.add(ts);
				}

			}
			rs.close();
			stmt.close();
			c.close();
			if (!encontrou) {
				System.out.println("Nenhum registro encontrado");
			}

		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return resultado;
	}

	public boolean addUserMedia(int media_id, int wl_id) {
		boolean resultado = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("INSERT INTO wl_media (wl_id, media_id) VALUES (?, ?)");
			stmt.setInt(1, wl_id);
			stmt.setInt(2, media_id);
			resultado = (stmt.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return resultado;
	}

	public boolean delete(String email) {
		boolean result = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("DELETE FROM whatched_list WHERE user_email = ?");
			stmt.setString(1, email);
			result = (stmt.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return result;
	}

	public boolean deleteAllUserMedia(String email) {
		boolean resultado = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement(
					"DELETE FROM wl_media WHERE wl_id = (SELECT id FROM whatched_list WHERE user_email = ?)");
			stmt.setString(1, email);
			resultado = (stmt.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}

		return resultado;
	}

	public boolean deleteUserMedia(int media_id, int wl_id) {
		boolean resultado = false;
		try {
			Class.forName(DRIVER);
			Connection c = DriverManager.getConnection(URL);
			PreparedStatement stmt = c.prepareStatement("DELETE FROM wl_media WHERE wl_id = ? AND media_id = ?");
			stmt.setInt(1, wl_id);
			stmt.setInt(2, media_id);
			resultado = (stmt.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return resultado;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public boolean update(WatchedList obj) {
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

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public List<WatchedList> getAll() {
		return null;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public WatchedList getOne(int id) {
		return null;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This method don't need to be implemented
	 */
	@Override
	public boolean insert(WatchedList obj) {
		// CANNOT USE METHOD
		return false;
	}

}
