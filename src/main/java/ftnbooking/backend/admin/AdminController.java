package ftnbooking.backend.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserRepository;
import ftnbooking.backend.users.ApplicationUserService;
import ftnbooking.backend.users.ApplicationUserType;
import ftnbooking.logging.LoggerManager;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	
	@Autowired
	private LoggerManager logger;
	
	@Autowired
	private ApplicationUserRepository userRepository;
	@Autowired
	private ApplicationUserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/*@RequestMapping(method = RequestMethod.POST,value="/login")
	public ResponseEntity<Long> login(@RequestBody ApplicationUser user) 
	{
		ApplicationUser existing = userRepository.findByEmail(user.getEmail());
		String encodedPassword =bCryptPasswordEncoder.encode(user.getPassword());
		
		if(bCryptPasswordEncoder.matches(user.getPassword(), existing.getPassword()))
		{
			
			
			
			
			
			return new ResponseEntity<>(existing.getId(), HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}*/
	@RequestMapping(method = RequestMethod.GET,value="/login")
	public ResponseEntity<String> checkIfAdmin(Principal principal) 
	{
		ApplicationUser user = userService.findOne(principal.getName());
		if(user == null || user.getUserType()!=ApplicationUserType.ADMIN)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	
		
		
	}
	
	
	
	@PreAuthorize("hasAuthority('GET_USERS')")
	@RequestMapping(method = RequestMethod.GET,value="/users")
	public ResponseEntity<List<UserDTO>> getUsers(Principal principal) 
	{
		
		ArrayList<UserDTO> retVal = new ArrayList<UserDTO>(); 
		for (ApplicationUser user : userRepository.findAll()) 
		{
			if(!user.getUserType().equals(ApplicationUserType.ADMIN))
			{
				retVal.add(new UserDTO(user));
			}
		}		 
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('ADD_USERS')")
	@RequestMapping(method = RequestMethod.POST,value="/users")
	public ResponseEntity<?> addUser(@RequestBody NewAccountDTO user, Principal principal) 
	{
		
		ApplicationUser user1 = new ApplicationUser(user.getEmail(), user.getPassword(),user.getName(), user.getLastname(), user.getCity(), user.getPhone());
		user1.setUserType(ApplicationUserType.AGENT);
		user1.setPid(user.getPid());
		userRepository.save(user1);		 
		
		logger.logAdmin("CREATED USER: " + user1.getEmail(), principal.getName());
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('MODIFY_USERS')")
	@RequestMapping(method = RequestMethod.POST,value="/users/{id:\\d+}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserDTO user, Principal principal) 
	{
		ApplicationUser existing = userRepository.findById(id).orElse(null);
		
		existing.setActive(user.isActive());
		existing.setBanned(user.isBanned());
		userRepository.save(existing);
		
		logger.logAdmin("CHANGED USER STATE TO: " + user.toLog(), principal.getName());
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	


}
