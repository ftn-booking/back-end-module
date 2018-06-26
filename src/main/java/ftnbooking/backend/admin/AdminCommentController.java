package ftnbooking.backend.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.comments.Approval;
import ftnbooking.backend.comments.Comment;
import ftnbooking.backend.comments.CommentService;
import ftnbooking.logging.LoggerManager;

@RestController
@RequestMapping("/api/admin/comments")
public class AdminCommentController {

	@Autowired
	private LoggerManager logger;
	
	@Autowired
	private CommentService commentService;
	
	@PreAuthorize("hasAuthority('GET_COMMENTS')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getComments(Principal principal) 
	{
		List<CommentDTO> retVal = new ArrayList<CommentDTO>();		
		for (Comment comment : commentService.findAll()) 
		{
			if(comment.getApproved().equals(Approval.PENDING))
			{
				retVal.add(new CommentDTO(comment));
			}
		}		
		return new ResponseEntity<List<CommentDTO>>(retVal, HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('APPROVE_COMMENTS')")
	@RequestMapping(method = RequestMethod.POST,value="/{id:\\d+}")
	public ResponseEntity<?> updateComment(@PathVariable Long id,@RequestBody String approved, Principal principal) 
	{
		
		Comment existing = commentService.findById(id);
		String log;
		if(approved.equals("approved"))
		{
			existing.setApproved(Approval.APPROVED);
			log="APPROVED";
		}else
		{
			existing.setApproved(Approval.NOTAPPROVED);
			log="DISAPPROVED";
		}
		
		
		commentService.add(existing);
		logger.logAdmin(log + "COMMENT WITH ID: " + existing.getId() + " ON RESERVATION: " + existing.getReservation().getId() + " OFF LODGING:" +existing.getReservation().getLodging().getName() , principal.getName());
		
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
}
