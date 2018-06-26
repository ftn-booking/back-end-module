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
		privilegeService.add(new Privilege("GET_USER_BY_MAIL", ApplicationUserType.ADMIN));

		privilegeService.add(new Privilege("GET_COMMENTS", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("ADD_COMMENT", ApplicationUserType.VISITOR));

		privilegeService.add(new Privilege("GET_LODGINGS", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("GET_LODGING", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("GET_AVAILABLE_LODGINGS", ApplicationUserType.VISITOR));

		privilegeService.add(new Privilege("GET_MESSAGES", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("ADD_MESSAGE", ApplicationUserType.VISITOR));

		privilegeService.add(new Privilege("GET_PRICES_FOR_LODGING", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("GET_ACTIVE_PRICE", ApplicationUserType.VISITOR));

		privilegeService.add(new Privilege("GET_USERS_RESERVATIONS", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("ADD_RESERVATION", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("RATE_RESERVATION", ApplicationUserType.VISITOR));
		privilegeService.add(new Privilege("CANCEL_RESERVATION", ApplicationUserType.VISITOR));
	}

}
