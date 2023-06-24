package show.tracker.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import show.tracker.api.model.User;
import show.tracker.api.repository.UserDAO;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserDAO userDAO;

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping
	public ResponseEntity<?> getUser(@RequestBody UserCredentials credentials) {
		User user = userDAO.getOne(credentials.getEmail(), credentials.getPassword());

		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		boolean isSuccess = userDAO.insert(user);

		if (isSuccess) {
			User newUser = userDAO.getOne(user.getEmail(), user.getPassword());
			return ResponseEntity.ok(newUser);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
		}
	}

	@DeleteMapping("/deleteAccount")
	public ResponseEntity<String> deleteUser(@RequestBody UserDeleteRequest userDeleteRequest) {
		String email = userDeleteRequest.getEmail();
		boolean deleted = userDAO.deleteUser(email);
		if (deleted) {
			return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to delete user.", HttpStatus.NOT_FOUND);
		}
	}

	public static class UserCredentials {
		private String email;
		private String password;

		public UserCredentials() {
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

	public static class UserDeleteRequest {
		private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
