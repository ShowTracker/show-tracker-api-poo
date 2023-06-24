package show.tracker.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import show.tracker.api.model.WatchedList;
import show.tracker.api.model.media.Media;
import show.tracker.api.repository.MediaDAO;
import show.tracker.api.repository.WatchedListDAO;

@RestController
@RequestMapping("/media")
public class MediaController {

	private MediaDAO mediaDAO;
	private WatchedListDAO wlDAO;

	public MediaController() {
		this.mediaDAO = new MediaDAO();
		this.wlDAO = new WatchedListDAO();
	}

	@GetMapping
	public ResponseEntity<?> searchMedia(@RequestParam String entry) {
		List<Media> searchResult = mediaDAO.searchMedia(entry);
		if (searchResult != null) {
			return ResponseEntity.ok(searchResult);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media not found");
		}
	}

	@PostMapping("/user/add")
	public ResponseEntity<?> addUserMedia(@RequestBody MediaCredentials credentials) {
		WatchedList wl = wlDAO.getOne(credentials.getEmail());

		boolean insertMedia = wlDAO.addUserMedia(credentials.getMedia_id(), wl.getId());
		if (insertMedia) {
			List<Media> media = wlDAO.getAllUserMedia(wl.getId());
			return ResponseEntity.ok(media);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("It wasn't possible to add the media to the user watched list.");
		}
	}

	@GetMapping("/user")
	public ResponseEntity<?> getUserMedia(@RequestBody UserCredentials credentials) {
		WatchedList wl = wlDAO.getOne(credentials.getEmail());
		List<Media> medias = wlDAO.getAllUserMedia(wl.getId());
		if (medias != null) {
			return ResponseEntity.ok(medias);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media not found");
		}
	}

	@DeleteMapping("/user/remove")
	public ResponseEntity<String> removeUserMedia(@RequestBody MediaCredentials credentials) {
		WatchedList wl = wlDAO.getOne(credentials.getEmail());
		boolean deleteMedia = wlDAO.deleteUserMedia(credentials.getMedia_id(), wl.getId());

		if (deleteMedia) {
			return ResponseEntity.status(HttpStatus.OK).body("Media removed successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("It wasn't possible to remove the media.");
		}
	}

	public static class MediaCredentials {
		private String email;
		private int media_id;

		public MediaCredentials() {
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String user_email) {
			this.email = user_email;
		}

		public int getMedia_id() {
			return media_id;
		}

		public void setMedia_id(int media_id) {
			this.media_id = media_id;
		}

	}

	public static class UserCredentials {
		private String email;

		public UserCredentials() {
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
