package ftnbooking.backend.users.privileges;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnbooking.backend.users.ApplicationUserType;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	List<Privilege> findByForRole(ApplicationUserType role);

}
