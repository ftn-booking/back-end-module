package ftnbooking.backend.lodgings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lodgings")
public class LodgingController {

	@Autowired
	private LodgingService lodgingService;

	@PreAuthorize("hasAuthority('GET_LODGINGS')")
	@GetMapping
	public ResponseEntity<List<Lodging>> getLodgings() {
		List<Lodging> lodgings = lodgingService.findAll();
		if(lodgings == null || lodgings.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(lodgings, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('GET_LODGING')")
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Lodging> getLodging(@PathVariable Long id) {
		Lodging lodging = lodgingService.findOne(id);
		if(lodging == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(lodging, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('GET_AVAILABLE_LODGINGS')")
	@GetMapping("/from/{fromDate:\\d+}/to/{toDate:\\d+}")
	public ResponseEntity<List<Lodging>> getAvailable(@PathVariable long fromDate, @PathVariable long toDate) {
		List<Lodging> lodgings = lodgingService.findAvailable(fromDate, toDate);
		if(lodgings == null || lodgings.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(lodgings, HttpStatus.OK);
	}

}
