package ftnbooking.backend.messages;

import java.util.List;

import ftnbooking.backend.users.ApplicationUser;

public interface MessageService {
	
	public List<Message> findAll();
	
	public List<Message> findByUser(ApplicationUser user);
	
	public List<Message> findByAgent(ApplicationUser user);
	
	public Message add(Message input);
}
