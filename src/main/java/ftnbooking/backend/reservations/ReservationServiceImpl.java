package ftnbooking.backend.reservations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftnbooking.backend.lodgings.LodgingService;
import ftnbooking.backend.users.ApplicationUser;

@Transactional(readOnly = true)
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private LodgingService lodgingService;

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

	@Override
	@Transactional(readOnly = false)
	public Reservation add(Reservation input) {
		boolean available = lodgingService.isAvailable(input.getLodging(), input.getFromDate(), input.getToDate());
		if(!available)
			return null;

		return reservationRepository.save(input);
	}

}
