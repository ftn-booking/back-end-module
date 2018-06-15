package ftnbooking.backend.reservations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

@Transactional(readOnly = true)
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reservation> findByUser(ApplicationUser user) {
		return reservationRepository.findByUser(user);
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	private boolean isAvailable(Lodging lodging, long fromDate, long toDate) {
		List<Reservation> reservations = reservationRepository
				.findByLodgingAndToDateGreaterThanAndFromDateLessThan(lodging, fromDate, toDate);
		if(reservations == null || reservations.isEmpty())
			// There are no other reservations for this lodging at specified time
			return true;
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public Reservation add(Reservation input) {
		boolean available = isAvailable(input.getLodging(), input.getFromDate(), input.getToDate());
		if(!available)
			return null;

		return reservationRepository.save(input);
	}

}
