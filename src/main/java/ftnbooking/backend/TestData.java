package ftnbooking.backend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;
import ftnbooking.backend.users.ApplicationUserType;

@Component
public class TestData {

	@Autowired
	private ApplicationUserService userService;

	@PostConstruct
	private void init() {
		ApplicationUser user1 = new ApplicationUser("ch@me", "qweqwe", "Chewbacca", "Chewbacca", "Kashyyyk", null);
		user1.setUserType(ApplicationUserType.VISITOR);
		userService.add(user1);

		ApplicationUser user2 = new ApplicationUser("rms@me", "qweqwe", "Richard", "Stallman", "New York", null);
		user2.setUserType(ApplicationUserType.VISITOR);
		userService.add(user2);

		ApplicationUser user3 = new ApplicationUser("han@me", "qweqwe", "Han", "Solo", "Corellia", null);
		user3.setUserType(ApplicationUserType.AGENT);
		userService.add(user3);

		ApplicationUser user4 = new ApplicationUser("pot@me", "qweqwe", "Lennart", "Poettering", "Guatemala City", null);
		user4.setUserType(ApplicationUserType.AGENT);
		userService.add(user4);

		ApplicationUser user5 = new ApplicationUser("ben@me", "qweqwe", "Ben", "Solo", "Chandrila", null);
		user5.setUserType(ApplicationUserType.ADMIN);
		userService.add(user5);

		ApplicationUser user6 = new ApplicationUser("an@me", "qweqwe", "Anakin", "Skywalker", "Tatooine", null);
		user6.setUserType(ApplicationUserType.ADMIN);
		userService.add(user6);

		ApplicationUser user7 = new ApplicationUser("sys@me", "qweqwe", "Sheev", "Palpatine", "Naboo", null);
		user7.setUserType(ApplicationUserType.ADMIN);
		userService.add(user7);
	}
}
