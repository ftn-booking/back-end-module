package ftnbooking.backend.comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByUser(ApplicationUser user);
	
	List<Comment> findByReservation_Lodging(Lodging lodging);
	
	List<Comment> findByUserAndApproved(ApplicationUser user, Approval approved);
	
	List<Comment> findByReservation_LodgingAndApproved(Lodging lodging, Approval approved);
}
