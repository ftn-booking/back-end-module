package ftnbooking.backend.users;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.EmailUtils;
import ftnbooking.logging.LoggerManager;


@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

	@Autowired
	private LoggerManager logger;
	@Autowired
	private ApplicationUserService userService;
	@Autowired
	private EmailUtils emailUtils;

	@PostMapping
	public ResponseEntity<ApplicationUser> register(@RequestBody @Valid ApplicationUser user) {
		ApplicationUser registered = userService.add(user);
		if(registered == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		logger.logAdmin("REGISTERED USER: " + registered.getId(), "VISITOR");
		return new ResponseEntity<>(registered, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity<ApplicationUser> getCurrentUser(Principal principal) {
		ApplicationUser user = userService.findOne(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping
	public ResponseEntity<?> changePassword(Principal principal,
			@RequestBody @Valid ChangePasswordDTO passwordDto) {
		boolean result = userService.changePassword(principal.getName(), passwordDto);
		if(result)
			return new ResponseEntity<>(HttpStatus.OK);
		logger.logAdmin("PASSWORD CHANGED: ", principal.getName());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/reset/{email}")
	public ResponseEntity<?> resetPassword(@PathVariable String email)
			throws MailException, InterruptedException {
		ApplicationUser user = userService.findOne(email);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		logger.logAdmin("PASSWORD RESETED: ", email);

		emailUtils.sendEmail(email,
				"Password recovery",
				"localhost:8080/api/authentication/reset/confirm/" + user.getResetToken());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/reset/confirm/{token}")
	public ResponseEntity<?> resetPasswordConfirmed(@PathVariable String token)
			throws MailException, InterruptedException {
		ApplicationUser user = userService.findByResetToken(token);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		logger.logAdmin("PASSWORD RESETED: ", user.getEmail());
		String newPassword = userService.resetPassword(user);
		emailUtils.sendEmail(user.getEmail(),
				"Password recovery",
				"Your new password is: " + newPassword + "\nPlease change it as soon as possible.");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('GET_USER_BY_MAIL')")
	@GetMapping("/{mail}")
	public ResponseEntity<?> getUserByMail(@PathVariable String mail){
		ApplicationUser user = userService.findOne(mail);
		if (user != null)
			return new ResponseEntity<>(user,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


}
