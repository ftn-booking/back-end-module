package ftnbooking.backend.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.rating.RemoteCommentService;
import ftnbooking.logging.LoggerManager;

@RestController
@RequestMapping("/api/admin/profanity")
public class ProfanityController {

	@Autowired
	private LoggerManager logger;
	
	@PreAuthorize("hasAuthority('UPDATE_REGISTRIES')")
	@RequestMapping(method = RequestMethod.POST  )
	public ResponseEntity<?> addProfanity(@RequestBody String profanity, Principal principal) 
	{
		RemoteCommentService.addProfanity(profanity);
		
		logger.logAdmin("ADDED PROFANITY:" + profanity, principal.getName());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('UPDATE_REGISTRIES')")
	@RequestMapping(method = RequestMethod.PUT  )
	public ResponseEntity<?> removeProfanity(@RequestBody String profanity, Principal principal) 
	{
		RemoteCommentService.removeProfanity(profanity);
		logger.logAdmin("REMOVED PROFANITY:" + profanity, principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('GET_PROFANITIES')")
	@RequestMapping(method = RequestMethod.GET  )
	public ResponseEntity<List<String>> getProfanities(Principal principal) 
	{
		ArrayList<String> retVal = new ArrayList<>();
		
		retVal = RemoteCommentService.getProfanities();
		return new ResponseEntity<List<String>>(retVal, HttpStatus.OK);
	}
	
}
