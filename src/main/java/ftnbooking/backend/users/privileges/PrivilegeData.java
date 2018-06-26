package ftnbooking.backend.users.privileges;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.users.ApplicationUserType;

@Component
public class PrivilegeData {

	@Autowired
	private PrivilegeService privilegeService;

	@PostConstruct
	private void init() {
		privilegeService.add(new Privilege("LIST_LODGINGS", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("GET_USER_BY_MAIL", ApplicationUserType.ADMIN));
		privilegeService.add(new Privilege("GET_COMMENTS", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("ADD_COMMENT", ApplicationUserType.VISITOR));
	}

}
