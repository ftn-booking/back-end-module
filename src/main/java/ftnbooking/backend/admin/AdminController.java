package ftnbooking.backend.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserRepository;
import ftnbooking.backend.users.ApplicationUserType;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private ApplicationUserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/login")
	public ResponseEntity<Long> login(@RequestBody ApplicationUser user) 
	{
		ApplicationUser existing = userRepository.findByEmail(user.getEmail());
		String encodedPassword =bCryptPasswordEncoder.encode(user.getPassword());
		
		if(bCryptPasswordEncoder.matches(user.getPassword(), existing.getPassword()))
		{
			
			
			
			
			
			return new ResponseEntity<>(existing.getId(), HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.GET,value="/users")
	public ResponseEntity<List<UserDTO>> getUsers() 
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
	
	@RequestMapping(method = RequestMethod.POST,value="/users/{id:\\d+}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserDTO user ) 
	{
		ApplicationUser existing = userRepository.findById(id).orElse(null);
		
		existing.setActive(user.isActive());
		existing.setBanned(user.isBanned());
		userRepository.save(existing);
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	


}
