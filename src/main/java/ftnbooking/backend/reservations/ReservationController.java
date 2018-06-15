package ftnbooking.backend.reservations;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ApplicationUserService userService;
	@Autowired
	private ReservationConverter reservationConverter;

	@GetMapping
	public ResponseEntity<List<Reservation>> getReservations() {
		List<Reservation> reservations = reservationService.findAll();
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.findOne(id);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}

	@GetMapping("/me")
	public ResponseEntity<List<Reservation>> getUsersReservations(Principal principal) {
		ApplicationUser user = userService.findOne(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<Reservation> reservations = reservationService.findByUser(user);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Reservation> add(Principal principal,
			@RequestBody ReservationDTO dto) {
		ApplicationUser user = userService.findOne(principal.getName());

		Reservation reservation = reservationConverter.fromDTO(user, dto);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Reservation reserved = reservationService.add(reservation);
		if(reserved == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(reserved, HttpStatus.OK);
	}

}
