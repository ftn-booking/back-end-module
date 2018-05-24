package ftnbooking.backend.users;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

	@Autowired
	private ApplicationUserService userService;

	@PostMapping
	public ResponseEntity<ApplicationUser> register(@RequestBody @Valid ApplicationUser user) {
		ApplicationUser registered = userService.add(user);
		if(registered == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		return new ResponseEntity<>(registered, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ApplicationUser> getCurrentUser(Principal principal) {
		ApplicationUser user = userService.findOne(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
