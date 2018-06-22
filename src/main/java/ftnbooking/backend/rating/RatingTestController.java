package ftnbooking.backend.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationService;

@RestController
@RequestMapping("/test/rate")
public class RatingTestController {

	
	@GetMapping
	public ResponseEntity<?> rate() {
		RatingData newRatingData = new RatingData(3.2, 11, 4);
		double newGrade = RatingService.getGrade(newRatingData);
		
		return new ResponseEntity<>( HttpStatus.OK);
	}

}
