package show.tracker.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import show.tracker.api.model.Genre;
import show.tracker.api.repository.GenreDAO;

@RestController
@RequestMapping("/genres")
public class GenreController {

	private GenreDAO genreDAO;

	public GenreController() {
		this.genreDAO = new GenreDAO();
	}

	@GetMapping
	public ResponseEntity<?> getGenre(@RequestBody GenreCredentials credentials) {
		Genre genre = genreDAO.getOne(credentials.getId());
		if (genre != null) {
			return ResponseEntity.ok(genre);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerGenre(@RequestBody Genre genre) {
		boolean isSuccess = genreDAO.insert(genre);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body("Genre created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register genre");
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteGenre(@RequestBody GenreDeleteRequest genreDeleteRequest) {
		int id = genreDeleteRequest.getId();
		boolean deleted = genreDAO.delete(id);
		if (deleted) {
			return new ResponseEntity<>("Genre deleted successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to delete genre.", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateGenre(@RequestBody Genre genre) {
		boolean updated = genreDAO.update(genre);
		if (updated) {
			return new ResponseEntity<>("Genre updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to update genre.", HttpStatus.NOT_FOUND);
		}
	}

	public static class GenreCredentials {
		private int id;
		private String name;

		public GenreCredentials() {
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class GenreDeleteRequest {
		private int id;

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
