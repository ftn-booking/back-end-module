package ftnbooking.backend.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.lodgings.LodgingService;
import ftnbooking.backend.users.ApplicationUser;

@Component
public class ReservationConverter {

	@Autowired
	private LodgingService lodgingService;

	Reservation fromDTO(ApplicationUser forUser, ReservationDTO dto) {
		if(dto.getFromDate() >= dto.getToDate())
			return null;
		Lodging lodging = lodgingService.findOne(dto.getLodgingId());
		return new Reservation(forUser, lodging, dto.getFromDate(), dto.getToDate());
	}

}
