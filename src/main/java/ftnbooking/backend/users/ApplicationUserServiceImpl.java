package ftnbooking.backend.users;

import java.util.List;
import java.util.UUID;

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
	public ApplicationUser findByResetToken(String token) {
		return userRepository.findByResetToken(token);
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

	@Override
	@Transactional(readOnly = false)
	public boolean changePassword(String email, ChangePasswordDTO passwordDto) {
		ApplicationUser user = findOne(email);
		if(!bCryptPasswordEncoder.matches(passwordDto.getOldPassword(), user.getPassword()))
			return false;

		user.setPassword(bCryptPasswordEncoder.encode(passwordDto.getNewPassword()));
		user = userRepository.save(user);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public String resetPassword(ApplicationUser forUser) {
		String newPassword = UUID.randomUUID().toString();
		forUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
		userRepository.save(forUser);
		return newPassword;
	}

}
