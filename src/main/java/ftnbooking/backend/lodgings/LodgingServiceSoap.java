package ftnbooking.backend.lodgings;

import java.util.List;

import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;

import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.FoodServiceType;
import ftnbooking.backend.types.LodgingType;
import ftnbooking.backend.users.ApplicationUser;


@WebService(name = "LodgingServiceSoap", targetNamespace = "https://backend.ftnbooking/lodgingServiceSoap")
@SchemaValidation(type = SchemaValidationType.REQUEST)
public interface LodgingServiceSoap {
	Long addLodging(Lodging lodging);
	Long reserveLodging(Reservation reservation);
	Long freeLodging(Reservation reservation);
	//List<Lodging> getMyLodgings(ApplicationUser agent);
	Long realizeReservation(Reservation reservation);
	List<Lodging> synchronizeLodging(ApplicationUser agent);
	List<Reservation> synchronizeReservation(ApplicationUser agent);
	List<FeatureType> synchronizeFeatureType();
	List<LodgingType> synchronizeLodgingType();
	List<FoodServiceType> synchronizeFoodServiceType();
	//ostale su jos poruke i cenovnik
}
