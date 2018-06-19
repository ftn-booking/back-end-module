package ftnbooking.backend.prices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.lodgings.LodgingService;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

	@Autowired
	private PriceService priceService;
	@Autowired
	private LodgingService lodgingService;

	@GetMapping("/{id:\\d+}")
	public ResponseEntity<List<Price>> getPricesForLodging(@PathVariable Long id) {
		Lodging lodging = lodgingService.findOne(id);
		if(lodging == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<Price> prices = priceService.findByLodging(lodging);
		if(prices == null || prices.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(prices, HttpStatus.OK);
	}

	@GetMapping("/{id:\\d+}/from/{fromDate:\\d+}")
	public ResponseEntity<List<Price>> getActivePrice(@PathVariable Long id, @PathVariable Long fromDate) {
		Lodging lodging = lodgingService.findOne(id);
		if(lodging == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<Price> prices = priceService.findActive(lodging, fromDate);
		if(prices == null || prices.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(prices, HttpStatus.OK);
	}

}
