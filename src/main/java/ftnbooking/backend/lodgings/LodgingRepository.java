package ftnbooking.backend.lodgings;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnbooking.backend.users.ApplicationUser;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {
	List<Lodging> findByAgent(ApplicationUser agent);
}
