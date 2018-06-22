package ftnbooking.backend.rating;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/comment")
public class CommentTestController {

	int i=0;
	
	@GetMapping
	public ResponseEntity<?> comment() {
		
		if(i<1)
		{
			RemoteCommentService.addProfanity("derpp");
		}else if (i<2)
		{
			RemoteCommentService.addProfanity("derp");
		}else
		{
			RemoteCommentService.getProfanities();
		}
		i++;
		return new ResponseEntity<>( HttpStatus.OK);
	}
}
