package ftnbooking.backend.users.privileges;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftnbooking.backend.users.ApplicationUserType;

@Transactional(readOnly = true)
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public List<Privilege> findAllForRole(ApplicationUserType role) {
		return privilegeRepository.findByForRole(role);
	}

	@Override
	@Transactional(readOnly = false)
	public Privilege add(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

}
