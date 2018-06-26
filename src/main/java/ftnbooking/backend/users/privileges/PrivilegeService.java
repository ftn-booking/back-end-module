package ftnbooking.backend.users.privileges;

import java.util.List;

import ftnbooking.backend.users.ApplicationUserType;

public interface PrivilegeService {

	List<Privilege> findAllForRole(ApplicationUserType role);

	Privilege add(Privilege privilege);

}
