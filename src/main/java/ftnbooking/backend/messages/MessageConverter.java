package ftnbooking.backend.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationService;
import ftnbooking.backend.users.ApplicationUser;

@Component
public class MessageConverter {

	@Autowired
	private ReservationService reservationService;

	Message fromDTO(ApplicationUser forUser, MessageDTO dto) {
		Reservation reservation = reservationService.findOne(dto.getReservationId());
		if(reservation == null)
			return null;
		if(forUser.getId() != reservation.getUser().getId())
			return null;

		return new Message(forUser, reservation, dto.getContent(), true);
	}

}
