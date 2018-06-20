package ftnbooking.backend.reservations;

import java.util.List;

import ftnbooking.backend.users.ApplicationUser;

public interface ReservationService {

	Reservation findOne(Long id);

	List<Reservation> findByUser(ApplicationUser user);

	List<Reservation> findAll();

	Reservation add(Reservation input);

	Double addRating(ApplicationUser user, Reservation reservation, int newRating);

}
