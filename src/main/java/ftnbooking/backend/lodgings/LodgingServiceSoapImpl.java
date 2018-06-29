package ftnbooking.backend.lodgings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnbooking.backend.messages.Message;
import ftnbooking.backend.messages.MessageRepository;
import ftnbooking.backend.messages.MessageService;
import ftnbooking.backend.prices.Price;
import ftnbooking.backend.prices.PriceRepository;
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
import ftnbooking.backend.users.ApplicationUserService;
import ftnbooking.backend.users.ChangePasswordDTO;
import ftnbooking.logging.LoggerManager;

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
	private PriceRepository priceRepository;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private LodgingService lodgingService;
	
	@Autowired
	private ApplicationUserService applicationUserService;
	
	@Autowired
	private LoggerManager logger;
	
	@Override
	public Long addLodging(Lodging lodging) {
		System.out.println(lodging.getAgent().getId());
		System.out.println(lodging);
		ApplicationUser agent = applicationUserRepository.findByEmail(lodging.getAgent().getEmail());
		if(lodging.getId()== null) {
					System.out.println(agent.getId());
			lodging.setAgent(agent);
			lodgingRepository.save(lodging);
			logger.logAgent("ADDED NEW LODGING WITH ID: " + lodging.getId(), agent.getEmail());
			return lodging.getId();
		}
		Optional<Lodging> opt = lodgingRepository.findById(lodging.getId());
		Lodging lo = opt.get();
		System.out.println(lo);
		lo.setAddress(lodging.getAddress());
		lo.setAgent(agent);
		lo.setCategory(lodging.getCategory());
		lo.setDescription(lodging.getDescription());
		lo.setFeatureType(lodging.getFeatureType());
		lo.setFoodServiceType(lodging.getFoodServiceType());
		lo.setImagePaths(lodging.getImagePaths());
		lo.setName(lodging.getName());
		lo.setNumberOfBeds(lodging.getNumberOfBeds());
		lo.setNumberOfRatings(0);
		lo.setRating(null);
		lodgingService.add(lo);
		logger.logAgent("UPDATED LODGING WITH ID: " + lo.getId(), agent.getEmail());
		return lo.getId();
	}

	@Override
	public Long reserveLodging(Reservation reservation) {
		reservationRepository.save(reservation);
		logger.logAgent("LODGING WITH ID: " + reservation.getId() + " RESERVED", reservation.getLodging().getAgent().getEmail());
		return reservation.getId();
	}

	@Override
	public Long freeLodging(Reservation reservation) {
		if(reservation.getUser().getEmail().equals(reservation.getLodging().getAgent().getEmail())) {
			reservationRepository.delete(reservation);
			logger.logAgent("DELETED RESERVATION WITH ID: " + reservation.getId(), reservation.getLodging().getAgent().getEmail());
			return (long) -1;
		}
		logger.logAgent("ERROR WHILE ATTTEMPTING TO DELETE RESERVATION WITH ID: " + reservation.getId(), reservation.getLodging().getAgent().getEmail());
		return (long) -10;
	}

	@Override
	public Long realizeReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		logger.logAgent("REALIZED RESERVATION WITH ID: " + reservation.getId(), reservation.getLodging().getAgent().getEmail());
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
		Long id = priceRepository.save(price).getId();
		logger.logAgent("ADDED NEW PRICE WITH ID: " + id, price.getLodging().getAgent().getEmail());
		return id;
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
		logger.logAgent("Messaged USER: " + message.getUser().getId(), message.getReservation().getLodging().getAgent().getEmail());
		return messageService.add(message).getId();
	}

	@Override
	public boolean changePassword(String email, ChangePasswordDTO cpDto) {
		applicationUserService.changePassword(email, cpDto);
		logger.logAgent("CHANGED PASSWORD", email);
		return true;
	}

	@Override
	public boolean deleteLodging(Lodging lodging) {
		if(reservationRepository.findByLodgingAndToDateLessThan(lodging, System.currentTimeMillis()).isEmpty()) {
			List<Reservation> reservations = reservationRepository.findByLodging(lodging);
			for(int i = 0; i < reservations.size(); i++) {
				List<Message> messages = messageRepository.findByReservation(reservations.get(i));
				messageRepository.deleteAll(messages);
			}
			reservationRepository.deleteAll(reservations);
			List<Price> prices = priceService.findByLodging(lodging);
			priceRepository.deleteAll(prices);
			lodgingRepository.delete(lodging);
			logger.logAgent("LODGING " + lodging.getId() + " DELETED", lodging.getAgent().getEmail());
			return true;
		}
		logger.logAgent("FAILED TO DELETE LODGING WITH ID: " + lodging.getId(), lodging.getAgent().getEmail());
		return false;
	}

	@Override
	public boolean deleteReservation(Reservation reservation) {
		reservationRepository.delete(reservation);
		logger.logAgent("RESERVATION " + reservation.getId() + " DELETED", reservation.getLodging().getAgent().getEmail());
		return true;
	}
	
	@Override
	public boolean deletePrice(Price price) {
		priceRepository.delete(price);
		logger.logAgent("PRICE " + price.getId() + " DELETED", price.getLodging().getAgent().getEmail());
		return true;
	}

	
}
