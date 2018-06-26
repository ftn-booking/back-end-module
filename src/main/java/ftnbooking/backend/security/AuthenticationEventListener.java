package ftnbooking.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;

@Component
public class AuthenticationEventListener {

	@Autowired
	private ApplicationUserService applicationUserService;

	@EventListener
	public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
		String email = (String) event.getAuthentication().getPrincipal();
		ApplicationUser user = applicationUserService.findOne(email);
		applicationUserService.failedLogin(user);
	}

	@EventListener
	public void authenticationSucceeded(AuthenticationSuccessEvent event) {
		User principal = (User) event.getAuthentication().getPrincipal();
		String email = principal.getUsername();
		ApplicationUser user = applicationUserService.findOne(email);
		applicationUserService.successfulLogin(user);
	}

}
