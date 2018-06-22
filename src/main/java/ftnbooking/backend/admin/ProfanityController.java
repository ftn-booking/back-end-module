package ftnbooking.backend.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.rating.RemoteCommentService;

@RestController
@RequestMapping("/admin/profanity")
public class ProfanityController {

	@RequestMapping(method = RequestMethod.POST  )
	public ResponseEntity<?> addProfanity(@RequestBody String profanity) 
	{
		RemoteCommentService.addProfanity(profanity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT  )
	public ResponseEntity<?> removeProfanity(@RequestBody String profanity) 
	{
		RemoteCommentService.removeProfanity(profanity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET  )
	public ResponseEntity<List<String>> getProfanities(@RequestBody String profanity) 
	{
		RemoteCommentService.removeProfanity(profanity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
