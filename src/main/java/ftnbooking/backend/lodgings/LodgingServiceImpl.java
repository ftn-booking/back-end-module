package ftnbooking.backend.lodgings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftnbooking.backend.rating.RatingData;
import ftnbooking.backend.rating.RatingService;
import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationRepository;

@Transactional(readOnly = true)
@Service
public class LodgingServiceImpl implements LodgingService {

	@Autowired
	private LodgingRepository lodgingRepository;
	@Autowired
	private ReservationRepository reservationRepository;


	@Override
	public Lodging findOne(Long id) {
		return lodgingRepository.findById(id).orElse(null);
	}

	@Override
	public List<Lodging> findAll() {
		return lodgingRepository.findAll();
	}

	@Override
	public boolean isAvailable(Lodging lodging, long fromDate, long toDate) {
		List<Reservation> reservations = reservationRepository
				.findByLodgingAndToDateGreaterThanAndFromDateLessThan(lodging, fromDate, toDate);
		if(reservations == null || reservations.isEmpty())
			// There are no other reservations for this lodging at specified time
			return true;
		return false;
	}

	@Override
	public List<Lodging> findAvailable(long fromDate, long toDate) {
		List<Lodging> ret = new ArrayList<>();

		List<Lodging> lodgings = lodgingRepository.findAll();
		for(Lodging lodging : lodgings) {
			if(isAvailable(lodging, fromDate, toDate)) {
				ret.add(lodging);
			}
		}
		return ret;
	}

	@Override
	@Transactional(readOnly = false)
	public Lodging add(Lodging input) {
		return lodgingRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Double recalculateRating(Lodging lodging, Integer newRating) {
		if(lodging.getRating() == null) {
			lodging.setRating(newRating.doubleValue());
			lodging.setNumberOfRatings(1);
			return lodging.getRating();
		}

		/*Double oldRating = lodging.getRating();
		Integer oldCount = lodging.getNumberOfRatings();
		lodging.setRating((oldRating * oldCount + newRating) / (oldCount + 1));
		lodging.setNumberOfRatings(oldCount + 1);*/
		
		// Prebaceno na koriscenje RatingService modula, jedina izmena je sto se kalkulacija vrsi pomocu RatingService klase.
		Double oldRating = lodging.getRating();
		Integer oldCount = lodging.getNumberOfRatings();
		lodging.setRating(RatingService.getGrade(new RatingData(oldRating,oldCount ,newRating)));
		lodging.setNumberOfRatings(oldCount + 1);
		
		
		
		
		lodgingRepository.save(lodging);
		return lodging.getRating();
	}

}
