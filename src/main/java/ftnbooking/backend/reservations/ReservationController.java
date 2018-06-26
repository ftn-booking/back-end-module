package ftnbooking.backend.reservations;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PreAuthorize("hasAuthority('GET_USERS_RESERVATIONS')")
	@GetMapping("/me")
	public ResponseEntity<List<Reservation>> getUsersReservations(Principal principal) {
		ApplicationUser user = userService.findOne(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<Reservation> reservations = reservationService.findByUser(user);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADD_RESERVATION')")
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

	@PreAuthorize("hasAuthority('RATE_RESERVATION')")
	@PutMapping("/{id:\\d+}/rate/{rating:[1-5]}")
	public ResponseEntity<?> rate(Principal principal,
			@PathVariable Long id,
			@PathVariable Integer rating) {
		ApplicationUser user = userService.findOne(principal.getName());

		Reservation reservation = reservationService.findOne(id);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Double ret = reservationService.addRating(user, reservation, rating);
		if(ret == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		HashMap<String, Double> res = new HashMap<>();
		res.put("newRating", ret);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('CANCEL_RESERVATION')")
	@DeleteMapping("/{id:\\d+}")
	public ResponseEntity<?> cancel(Principal principal,
			@PathVariable Long id) {
		ApplicationUser user = userService.findOne(principal.getName());

		Reservation reservation = reservationService.findOne(id);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		boolean ret = reservationService.delete(user, reservation);
		if(!ret)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
