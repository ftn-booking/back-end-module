package ftnbooking.backend.lodgings;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationRepository;
import ftnbooking.backend.users.ApplicationUser;

@Service
@WebService(endpointInterface = "ftnbooking.backend.lodgings.LodgingServiceSoap",
			serviceName = "LodgingService",
			portName = "LodgingServicePort",
			targetNamespace = "https://backend.ftnbooking/lodgingServiceSoap")
public class LodgingServiceSoapImpl implements LodgingServiceSoap{

	@Autowired
	private LodgingRepository lodgingRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Long addLodging(Lodging lodging) {
		lodgingRepository.save(lodging);
		return lodging.getId();
	}

	@Override
	public Long reserveLodging(Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation.getId();
	}

	@Override
	public Long freeLodging(Reservation reservation) {
		if(reservation.getUser().equals(null)) {
			reservationRepository.delete(reservation);
			return (long) -1;
		}
		return (long) -10;
	}

	@Override
	public Long realizeReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation.getId();
	}

	@Override
	public List<Lodging> synchronizeLodging(ApplicationUser agent) {
		
		return lodgingRepository.findByAgent(agent);
	}

	@Override
	public List<Reservation> synchronizeReservation(ApplicationUser agent) {
		List<Lodging> lodgings = lodgingRepository.findByAgent(agent);
		List<Reservation> reservations = new ArrayList<>();
		for(int i = 0; i<lodgings.size(); i++) {
			List<Reservation> reservationsByLodging = reservationRepository.findByLodging(lodgings.get(i));
			reservations.addAll(reservationsByLodging);
		}
		return reservations;
	}

	
}
