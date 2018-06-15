package ftnbooking.backend.reservations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByUser(ApplicationUser user);
	List<Reservation> findByLodgingAndToDateGreaterThanAndFromDateLessThan(Lodging lodging,
			long currentFromDate,
			long currentToDate);

}
