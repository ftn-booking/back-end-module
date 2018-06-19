package ftnbooking.backend.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.comments.Approval;
import ftnbooking.backend.comments.Comment;
import ftnbooking.backend.comments.CommentService;

@RestController
@RequestMapping("/api/admin/comments")
public class AdminCommentController {

	@Autowired
	private CommentService commentService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getComments() 
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
	
	@RequestMapping(method = RequestMethod.POST,value="/{id:\\d+}")
	public ResponseEntity<?> updateComment(@PathVariable Long id,@RequestBody String approved ) 
	{
		Comment existing = commentService.findById(id);
		if(approved.equals("approved"))
		{
			existing.setApproved(Approval.APPROVED);
		}else
		{
			existing.setApproved(Approval.NOTAPPROVED);
		}
		
		commentService.add(existing);
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
}
