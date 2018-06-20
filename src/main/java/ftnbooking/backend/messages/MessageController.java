package ftnbooking.backend.messages;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private MessageConverter messageConverter;
	@Autowired
	private ApplicationUserService userService;

	@GetMapping
	public ResponseEntity<List<Message>> getMessages(Principal principal) {
		ApplicationUser user = userService.findOne(principal.getName());

		List<Message> messages = messageService.findByUser(user);
		if(messages == null || messages.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Message> addMessage(Principal principal,
			@RequestBody MessageDTO dto) {
		ApplicationUser user = userService.findOne(principal.getName());

		Message message = messageConverter.fromDTO(user, dto);
		if(message == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Message ret = messageService.add(message);
		if(ret == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
