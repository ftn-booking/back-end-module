package ftnbooking.backend.users;

import java.util.List;

public interface ApplicationUserService {

	ApplicationUser findOne(Long id);

	ApplicationUser findOne(String email);

	ApplicationUser findByResetToken(String token);

	List<ApplicationUser> findAll();

	ApplicationUser add(ApplicationUser user);

	boolean changePassword(String email, ChangePasswordDTO passwordDto);

	String resetPassword(ApplicationUser forUser);

}
