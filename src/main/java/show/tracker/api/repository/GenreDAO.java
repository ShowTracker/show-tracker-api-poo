
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
	public boolean insert(Genre obj) {
		try (Connection connection = DriverManager.getConnection(URL)) {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO genre (name) VALUES (?)");
			statement.setString(1, obj.getName());
			return (statement.executeUpdate() == 1);
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	@Override
	public Genre getOne(int id) {
		Genre resultado = null;
		try (Connection connection = DriverManager.getConnection(URL)) {
			PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM genre WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int codigo = resultSet.getInt("id");
				String nome = resultSet.getString("name");
				resultado = new Genre(codigo, nome);
			} else {
				System.out.println("Nenhum registro encontrado");
			}

			resultSet.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return resultado;
	}

	@Override
	public List<Genre> getAll() {
		List<Genre> resultado = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL)) {
			PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM genre");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int codigo = resultSet.getInt("id");
				String nome = resultSet.getString("name");
				Genre g = new Genre(codigo, nome);
				resultado.add(g);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}

		if (resultado.isEmpty()) {
			System.out.println("Nenhum registro encontrado");
		}

		return resultado;
	}

	@Override
	public boolean update(Genre obj) {
		try (Connection connection = DriverManager.getConnection(URL)) {
			PreparedStatement statement = connection.prepareStatement("UPDATE genre SET name = (?) WHERE id = (?)");
			statement.setString(1, obj.getName());
			statement.setInt(2, obj.getId());
			return statement.executeUpdate() > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
	    try (Connection connection = DriverManager.getConnection(URL);
	         PreparedStatement statement = connection.prepareStatement("DELETE FROM genre WHERE id = (?)")) {
	        statement.setInt(1, id);
	        return statement.executeUpdate() > 0;
	    } catch (SQLException ex) {
	        System.out.println("Error: " + ex.getMessage());
	        return false;
	    }
	}


}
