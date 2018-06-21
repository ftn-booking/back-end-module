package ftnbooking.backend.lodgings;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnbooking.backend.messages.Message;
import ftnbooking.backend.messages.MessageService;
import ftnbooking.backend.prices.Price;
import ftnbooking.backend.prices.PriceService;
import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationRepository;
import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.FeatureTypeRepository;
import ftnbooking.backend.types.FoodServiceType;
import ftnbooking.backend.types.FoodServiceTypeRepository;
import ftnbooking.backend.types.LodgingType;
import ftnbooking.backend.types.LodgingTypeRepository;
import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserRepository;

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
	
	@Autowired
	private LodgingTypeRepository lodgingTypeRepository;
	
	@Autowired
	private FeatureTypeRepository featureTypeRepository;
	
	@Autowired
	private FoodServiceTypeRepository foodServiceTypeRepository;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private LodgingService lodgingService;
	
	@Override
	public Long addLodging(Lodging lodging) {
		System.out.println(lodging.getAgent().getId());
		ApplicationUser agent = applicationUserRepository.findByEmail(lodging.getAgent().getEmail());
		System.out.println(agent.getId());
		lodging.setAgent(agent);
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

	@Override
	public List<FeatureType> synchronizeFeatureType() {
		return featureTypeRepository.findAll();
	}

	@Override
	public List<LodgingType> synchronizeLodgingType() {
		return lodgingTypeRepository.findAll();
	}

	@Override
	public List<FoodServiceType> synchronizeFoodServiceType() {
		return foodServiceTypeRepository.findAll();
	}

	@Override
	public List<Price> synchronizePrice(ApplicationUser agent) {
		return priceService.findByAgent(agent);
	}

	@Override
	public Long addPrice(Price price) {
		return priceService.add(price).getId();
	}

	@Override
	public List<ApplicationUser> synchronizeApplicationUser() {
		return applicationUserRepository.findAll();
	}

	@Override
	public List<Message> synchronizeMessage(ApplicationUser user) {
		return messageService.findByAgent(user);
	}

	@Override
	public Long sendMessage(Message message) {
		return messageService.add(message).getId();
	}

	@Override
	public boolean changePassword(ApplicationUser user) {
		applicationUserRepository.save(user);
		return true;
	}

	
}
