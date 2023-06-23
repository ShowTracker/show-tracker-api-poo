package show.tracker.api;

import java.util.List;

import show.tracker.api.model.Genre;
import show.tracker.api.model.User;
import show.tracker.api.model.media.Media;
import show.tracker.api.repository.GenreDAO;
import show.tracker.api.repository.MediaDAO;
import show.tracker.api.repository.UserDAO;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenreDAO genreDAO = new GenreDAO();
		List<Genre> g = genreDAO.getAll();

		for (int i = 0; i < g.size(); i++) {
			Genre genre = g.get(i);
			System.out.println(genre.getName());
		}

	}

}
