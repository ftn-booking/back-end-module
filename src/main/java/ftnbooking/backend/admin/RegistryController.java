package ftnbooking.backend.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.FeatureTypeService;
import ftnbooking.backend.types.LodgingType;
import ftnbooking.backend.types.LodgingTypeService;
import ftnbooking.backend.users.ApplicationUser;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {
	
	@Autowired
	private LodgingTypeService lodgingTypeService;
	
	@Autowired
	private FeatureTypeService featureTypeService;

	@RequestMapping(method = RequestMethod.GET, value="/lodging")
	public ResponseEntity<List<RegistryItemDTO>> getLodgings() 
	{
		List<RegistryItemDTO> retVal = new ArrayList<RegistryItemDTO>();
		
		for (LodgingType lodgingType : lodgingTypeService.findAll()) 
		{
			retVal.add(new RegistryItemDTO(lodgingType));
		}
		
		return new ResponseEntity<List<RegistryItemDTO>>(retVal, HttpStatus.OK);
	
	}
	@RequestMapping(method = RequestMethod.GET, value="/feature")
	public ResponseEntity<List<RegistryItemDTO>> getFeatures() 
	{
		List<RegistryItemDTO> retVal = new ArrayList<RegistryItemDTO>();
		
		for (FeatureType featureType : featureTypeService.findAll()) 
		{
			retVal.add(new RegistryItemDTO(featureType));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	
	}
	@RequestMapping(method = RequestMethod.POST, value="/lodgingRegistry")
	public ResponseEntity<?> addLodging(@RequestBody RegistryItemDTO registryItemDTO) 
	{
		long id =0;
		try
		{
			id =registryItemDTO.getId();
		}catch(Exception ex)
		{
			
		}
		if(id==0)
		{
			LodgingType lodgingType = new LodgingType();
		
			lodgingType.setName(registryItemDTO.getName());
			lodgingType.setActive(true);	
			lodgingTypeService.add(lodgingType);
		}else
		{
			
			LodgingType lodgingType = lodgingTypeService.findOne(registryItemDTO.getId());
			
			lodgingType.setActive(registryItemDTO.isActive());	
			lodgingTypeService.add(lodgingType);
			
		}
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/featureRegistry")
	public ResponseEntity<?> addFeature(@RequestBody RegistryItemDTO registryItemDTO) 
	{
		long id =0;
		try
		{
			id =registryItemDTO.getId();
		}catch(Exception ex)
		{
			
		}
		if(id==0)
		{
			FeatureType featureType = new FeatureType();
		
			featureType.setName(registryItemDTO.getName());
			featureType.setActive(true);	
			featureTypeService.add(featureType);
		}else
		{
			
			FeatureType featureType = featureTypeService.findOne(registryItemDTO.getId());
			
			featureType.setActive(registryItemDTO.isActive());	
			featureTypeService.add(featureType);
			
		}
		return new ResponseEntity<>( HttpStatus.OK);
	}
	/*@RequestMapping(method = RequestMethod.PUT, value="/lodgingRegistry")
	public ResponseEntity<?> updateLodging(@RequestBody RegistryItemDTO registryItemDTO) 
	{
		LodgingType lodgingType = new LodgingType();
		lodgingType.setId(registryItemDTO.getId());
		lodgingType.setActive(true);	
		lodgingTypeService.add(lodgingType);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/featureRegistry")
	public ResponseEntity<?> updateFeature(@RequestBody RegistryItemDTO registryItemDTO) 
	{
		FeatureType featureType = new FeatureType();
		featureType.setId(registryItemDTO.getId());
		featureType.setActive(true);	
		featureTypeService.add(featureType);
		return new ResponseEntity<>( HttpStatus.OK);
	}*/

}
