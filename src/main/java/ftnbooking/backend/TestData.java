package ftnbooking.backend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.lodgings.FoodServiceType;
import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.lodgings.LodgingService;
import ftnbooking.backend.lodgings.LodgingType;
import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationService;
import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;
import ftnbooking.backend.users.ApplicationUserType;

@Component
public class TestData {

	@Autowired
	private ApplicationUserService userService;
	@Autowired
	private LodgingService lodgingService;
	@Autowired
	private ReservationService reservationService;

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

		Lodging lodging1 = new Lodging("Lodging",
				"Hawaii",
				"Test lodging 1",
				4,
				LodgingType.APARTMENT,
				2,
				true,
				true,
				false,
				false,
				true,
				FoodServiceType.FULL_BOARD,
				user3);
		lodging1.addImagePath("img/placeholder.png");
		lodging1.addImagePath("img/placeholder.png");
		lodging1.addImagePath("img/placeholder.png");
		lodgingService.add(lodging1);

		Lodging lodging2 = new Lodging("Tourist Inn",
				"Sri Lanka",
				"Calm place for family",
				3,
				LodgingType.B_AND_B,
				3,
				true,
				false,
				false,
				true,
				true,
				FoodServiceType.BREAKFAST,
				user4);
		lodging2.addImagePath("img/placeholder.png");
		lodging2.addImagePath("img/placeholder.png");
		lodgingService.add(lodging2);

		Reservation reservation1 = new Reservation(user1, lodging2, 1534291200000l, 1535155200000l);
		reservationService.add(reservation1);

		Reservation reservation2 = new Reservation(user1, lodging1, 1539907200000l, 1540252800000l);
		reservationService.add(reservation2);

	}
}
