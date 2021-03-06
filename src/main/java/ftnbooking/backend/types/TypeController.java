package ftnbooking.backend.types;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/types")
public class TypeController {

	@Autowired
	private LodgingTypeService lodgingTypeService;
	@Autowired
	private FoodServiceTypeService foodTypeService;
	@Autowired
	private FeatureTypeService featureTypeService;

	@PreAuthorize("hasAuthority('GET_LODGING_TYPES')")
	@GetMapping("/lodging")
	public ResponseEntity<List<LodgingType>> getLodgingTypes() {
		List<LodgingType> lodgingTypes = lodgingTypeService.findAll();
		if(lodgingTypes == null || lodgingTypes.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(lodgingTypes, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('GET_FOOD_TYPES')")
	@GetMapping("/food")
	public ResponseEntity<List<FoodServiceType>> getFoodTypes() {
		List<FoodServiceType> foodTypes = foodTypeService.findAll();
		if(foodTypes == null || foodTypes.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foodTypes, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('GET_FEATURE_TYPES')")
	@GetMapping("/feature")
	public ResponseEntity<List<FeatureType>> getFeatureTypes() {
		List<FeatureType> featureTypes = featureTypeService.findAll();
		if(featureTypes == null || featureTypes.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(featureTypes, HttpStatus.OK);
	}

}
