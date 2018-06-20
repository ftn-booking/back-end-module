package ftnbooking.backend.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationService;
import ftnbooking.backend.users.ApplicationUser;

@Component
public class CommentConverter {

	@Autowired
	private ReservationService reservationService;

	Comment fromDTO(ApplicationUser forUser, CommentDTO dto) {
		Reservation reservation = reservationService.findOne(dto.getReservationId());
		if(reservation == null)
			return null;
		if(forUser.getId() != reservation.getUser().getId())
			return null;
		if(!reservation.isApproved())
			return null;

		return new Comment(forUser, reservation, dto.getContent());
	}

}
