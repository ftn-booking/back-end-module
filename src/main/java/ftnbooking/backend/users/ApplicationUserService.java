package ftnbooking.backend.users;

import java.util.List;

public interface ApplicationUserService {

	ApplicationUser findOne(Long id);

	ApplicationUser findOne(String email);

	List<ApplicationUser> findAll();

	ApplicationUser add(ApplicationUser user);

}
