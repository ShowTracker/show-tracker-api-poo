package show.tracker.api.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import show.tracker.api.model.media.Film;
import show.tracker.api.model.media.Media;
import show.tracker.api.model.media.TvShow;

public class MediaDAO extends DAO<Media> {

    @Override
    public boolean insert(Media obj) {
        // CANNOT USE METHOD
        return false;
    }

    @Override
    public Media getOne(int id) {

        Media result = null;
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = c.prepareStatement("SELECT id, title_type, year, title, is_adult, duration, end_year FROM media WHERE id = ?");
            stmt.setInt(id, id);
            boolean found = false;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("title_type") == "movie") {
                    int codigo = rs.getInt("id");
                    String title = rs.getString("title");
                    String year = rs.getString("year");
                    boolean isAdult = rs.getBoolean("is_adult");
                    String duration = rs.getString("duration");
                    result = new Film(duration, codigo, year, title, isAdult);
                } else {
                    int codigo = rs.getInt("id");
                    String title = rs.getString("title");
                    String year = rs.getString("year");
                    boolean isAdult = rs.getBoolean("is_adult");
                    String endYear = rs.getString("end_year");
                    result = new TvShow(endYear, codigo, year, title, isAdult);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        // TODO implement method
        return result;
    }

    @Override
    public List<Media> getAll() {
        List<Media> result = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = c.prepareStatement("SELECT id, title_type, year, title, is_adult, duration, end_year FROM media");
            boolean found = false;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String title_type = rs.getString("title_type");
                if ("movie".equals(title_type)) {
                    int codigo = rs.getInt("id");
                    String title = rs.getString("title");
                    String year = rs.getString("year");
                    boolean isAdult = rs.getBoolean("is_adult");
                    String duration = rs.getString("duration");
                    Media f = new Film(duration, codigo, year, title, isAdult);
                    result.add(f);
                    // } else if (rs.getString("title_type") == "tvSeries"){
                } else {
                    int codigo = rs.getInt("id");
                    String title = rs.getString("title");
                    String year = rs.getString("year");
                    boolean isAdult = rs.getBoolean("is_adult");
                    String endYear = rs.getString("end_year");
                    Media tv = new TvShow(endYear, codigo, year, title, isAdult);
                    result.add(tv);
                }
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

    public List<Media> searchMedia(String entry) {

        List<Media> result = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = c.prepareStatement("SELECT id, title_type, year, title, is_adult, duration, end_year FROM media WHERE title LIKE '%" + entry + "%'");
            // stmt.setString(1, entry);
            boolean found = false;
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String title_type = rs.getString("title_type");
                if ("movie".equals(title_type)) {
                    int codigo = rs.getInt("id");
                    String title = rs.getString("title");
                    String year = rs.getString("year");
                    boolean isAdult = rs.getBoolean("is_adult");
                    String duration = rs.getString("duration");
                    found = true;
                    Media f = new Film(duration, codigo, year, title, isAdult);
                    result.add(f);
                    // } else if (rs.getString("title_type") == "tvSeries"){
                } else {
                    int codigo = rs.getInt("id");
                    String title = rs.getString("title");
                    String year = rs.getString("year");
                    boolean isAdult = rs.getBoolean("is_adult");
                    String endYear = rs.getString("end_year");
                    
                    found = true;
                    Media tv = new TvShow(endYear, codigo, year, title, isAdult);
                    result.add(tv);
                }
            }
            rs.close();
            stmt.close();
            c.close();
            if (!found) {
                System.out.println("Nenhum registro encontrado");
            }
        } catch (SQLException ex) {
            System.out.println("Erro SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro Normal: " + ex.getMessage());
        }
        return result;
    }

    @Override
    public boolean update(Media obj) {
        // CANNOT USE METHOD
    	return false;
    }

    @Override
    public boolean delete(int id) {
        // CANNOT USE METHOD
        return false;
    }

}
