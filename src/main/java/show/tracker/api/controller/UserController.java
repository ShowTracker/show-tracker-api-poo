package show.tracker.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import show.tracker.api.model.User;
import show.tracker.api.repository.UserDAO;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserDAO userDAO; // Instance of UserDAO

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*
	 * @GetMapping public ResponseEntity<?> getUser(@RequestParam("email") String
	 * email, @RequestParam("password") String password) { User user =
	 * userDAO.getOne(email, password);
	 * 
	 * if (user != null) { return ResponseEntity.ok(user); } else { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"); } }
	 */
	
	@GetMapping
	public ResponseEntity<?> getUser(@RequestBody UserCredentials credentials ) {
		User user = userDAO.getOne(credentials.getEmail(), credentials.getPassword());

		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	public static class UserCredentials {
		private String email;
		private String password;

		public UserCredentials() {
			// TODO Auto-generated constructor stub
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}