package ftnbooking.backend.prices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.lodgings.LodgingRepository;
import ftnbooking.backend.users.ApplicationUser;

@Service
public class PriceServiceImpl implements PriceService{

	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private LodgingRepository lodgingRepository;
	
	@Override
	public List<Price> findAll() {
		return priceRepository.findAll();
	}

	@Override
	public List<Price> findByLodging(Lodging lodging) {
		return priceRepository.findByLodging(lodging);
	}

	@Override
	public List<Price> findByAgent(ApplicationUser user) {
		List<Lodging> lodgings = lodgingRepository.findByAgent(user);
		List<Price> prices = new ArrayList<>();
		for(int i = 0; i<lodgings.size(); i++) {
			prices.addAll(priceRepository.findByLodging(lodgings.get(i)));
		}
		return prices;
	}

	@Override
	public Price add(Price input) {
		return priceRepository.save(input);
	}

}
