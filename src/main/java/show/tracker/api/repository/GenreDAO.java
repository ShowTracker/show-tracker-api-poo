
package show.tracker.api.repository;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import show.tracker.api.model.Genre;

public class GenreDAO extends DAO<Genre> {

	@Override
	public Genre getOne(int id) {
		Genre genre = null;
		try (Connection connection = DriverManager.getConnection(URL)) {
			PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM genre WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int codigo = resultSet.getInt("id");
				String nome = resultSet.getString("name");
				genre = new Genre(codigo, nome);
			} else {
				System.out.println("Nenhum registro encontrado");
			}
			resultSet.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return genre;
	}

	@Override
	public List<Genre> getAll() {
		List<Genre> genres = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL)) {
			PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM genre");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int codigo = resultSet.getInt("id");
				String nome = resultSet.getString("name");
				Genre g = new Genre(codigo, nome);
				genres.add(g);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		if (genres.isEmpty()) {
			System.out.println("Nenhum registro encontrado");
		}
		return genres;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This table in database is read only
	 */
	@Override
	public boolean insert(Genre genre) {
		return false;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This table in database is read only
	 */
	@Override
	public boolean update(Genre genre) {
		return false;
	}

	/**
	 * <strong>CANNOT USE METHOD</strong> <br>
	 * This table in database is read only
	 */
	@Override
	public boolean delete(int id) {
		return false;
	}
}
