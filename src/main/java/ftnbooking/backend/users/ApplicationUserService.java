package ftnbooking.backend.users;

import java.util.Date;
import java.util.List;

public interface ApplicationUserService {

	ApplicationUser findOne(Long id);

	ApplicationUser findOne(String email);

	ApplicationUser findByResetToken(String token);

	List<ApplicationUser> findAll();

	ApplicationUser add(ApplicationUser user);

	boolean changePassword(String email, ChangePasswordDTO passwordDto);

	String resetPassword(ApplicationUser forUser);

	void failedLogin(ApplicationUser user);

	void successfulLogin(ApplicationUser user);

}
