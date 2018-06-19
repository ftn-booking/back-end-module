package ftnbooking.backend.comments;

import java.util.List;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

public interface CommentService {
	
	public List<Comment> findAll();
	
	public List<Comment> findByUser(ApplicationUser user);
	
	public List<Comment> findByLodging(Lodging lodging);
	
	public Comment findById(Long id);
	
	public List<Comment> findByUserAndApproved(ApplicationUser user, Approval approved);
	
	public List<Comment> findByLodgingAndApproved(Lodging lodging, Approval approved);
	
	public Comment add(Comment input);
	
}
