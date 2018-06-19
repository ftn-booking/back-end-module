package ftnbooking.backend.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

@Transactional(readOnly = true)
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public List<Comment> findByUser(ApplicationUser user) {
		return commentRepository.findByUser(user);
	}

	@Override
	public List<Comment> findByLodging(Lodging lodging) {
		return commentRepository.findByReservation_Lodging(lodging);
	}

	@Override
	public List<Comment> findByUserAndApproved(ApplicationUser user, Approval approved) {
		return commentRepository.findByUserAndApproved(user, approved);
	}

	@Override
	public List<Comment> findByLodgingAndApproved(Lodging lodging, Approval approved) {
		return commentRepository.findByReservation_LodgingAndApproved(lodging, approved);
	}

	@Override
	@Transactional(readOnly = false)
	public Comment add(Comment input) {
		return commentRepository.save(input);
	}

	@Override
	public Comment findById(Long id) {
		// TODO Auto-generated method stub
		return commentRepository.findById(id).orElse(null);
	}

}
