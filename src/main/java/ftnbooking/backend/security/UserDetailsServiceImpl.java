package ftnbooking.backend.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserRepository;
import ftnbooking.backend.users.privileges.Privilege;
import ftnbooking.backend.users.privileges.PrivilegeService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserRepository applicationUserRepository;
	private PrivilegeService privilegeService;

	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository, PrivilegeService privilegeService) {
		this.applicationUserRepository = applicationUserRepository;
		this.privilegeService = privilegeService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByEmail(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(applicationUser.getUserType().toString()));
		List<Privilege> privileges = privilegeService.findAllForRole(applicationUser.getUserType());
		for(Privilege privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege.getName()));
		}

		return new User(applicationUser.getEmail(),
				applicationUser.getPassword(),
				!applicationUser.isBanned(),
				true, true,
				applicationUser.isActive(),
				authorities);
	}

}