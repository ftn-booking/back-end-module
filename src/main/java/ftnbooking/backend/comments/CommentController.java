package ftnbooking.backend.comments;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.lodgings.LodgingService;
import ftnbooking.backend.rating.RemoteCommentService;
import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private LodgingService lodgingService;
	@Autowired
	private CommentConverter commentConverter;
	@Autowired
	private ApplicationUserService userService;

	@GetMapping("/lodging/{id:\\d+}")
	public ResponseEntity<List<Comment>> getComments(@PathVariable Long id) {
		Lodging lodging = lodgingService.findOne(id);
		if(lodging == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<Comment> comments = commentService.findByLodgingAndApproved(lodging, Approval.APPROVED);
		if(comments == null || comments.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Comment> addComment(Principal principal,
			@RequestBody CommentDTO dto) {
		ApplicationUser user = userService.findOne(principal.getName());

		Comment comment = commentConverter.fromDTO(user, dto);
		if(comment == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		comment.setApproved(RemoteCommentService.checkComment(comment.getContent()));
		Comment ret = commentService.add(comment);
		if(ret == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

}
