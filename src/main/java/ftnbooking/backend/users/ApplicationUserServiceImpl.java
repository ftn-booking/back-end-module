package ftnbooking.backend.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

	@Autowired
	private ApplicationUserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ApplicationUser findOne(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public ApplicationUser findOne(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<ApplicationUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public ApplicationUser add(ApplicationUser user) {
		ApplicationUser existing = userRepository.findByEmail(user.getEmail());
		if(existing != null) // User already exists
			return null;

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
