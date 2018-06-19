package ftnbooking.backend.prices;

import java.util.List;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

public interface PriceService {

	public List<Price> findAll();

	List<Price> findActive(Lodging lodging, long fromDate);

	public List<Price> findByLodging(Lodging lodging);

	public List<Price> findByAgent(ApplicationUser user);

	public Price add(Price input);

}
