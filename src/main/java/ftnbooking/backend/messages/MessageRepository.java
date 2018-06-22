package ftnbooking.backend.messages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.users.ApplicationUser;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByUser(ApplicationUser user);
	
	List<Message> findByReservation_Lodging_Agent(ApplicationUser user);
	
	List<Message> findByReservation(Reservation reservation);
	
}
