package ftnbooking.backend.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserRepository applicationUserRepository;

	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByEmail(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getEmail(),
				applicationUser.getPassword(),
				!applicationUser.isBanned(),
				true, true,
				applicationUser.isActive(),
				AuthorityUtils.createAuthorityList(applicationUser.getUserType().toString()));
	}

}