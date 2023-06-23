
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
        boolean result = false;
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = c.prepareStatement("INSERT INTO genre (name) VALUES (?)");
            stmt.setString(1, obj.getName());
            result = (stmt.executeUpdate() == 1);
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public Genre getOne(int id) {
        Genre resultado = null;
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = c.prepareStatement("SELECT id, name FROM genre WHERE id = ?");
            stmt.setInt(1, id);
            boolean found = false;
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("id");
                String nome = rs.getString("name");
                found = true;
                Genre g = new Genre(codigo, nome);
                resultado = g;
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
        return resultado;
    }

    @Override
    public List<Genre> getAll() {
        // TODO test
        List<Genre> resultado = new ArrayList<Genre>();
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = c.prepareStatement("SELECT id, name FROM genre");
            boolean encontrou = false;
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("id");
                String nome = rs.getString("name");
                encontrou = true;
                Genre g = new Genre(codigo, nome);
                resultado.add(g);
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

    @Override
    public void update(Genre obj) {
        // CANNOT USE METHOD
    }

    @Override
    public boolean delete(int id) {
        // CANNOT USE METHOD
        return false;
    }
    
}
