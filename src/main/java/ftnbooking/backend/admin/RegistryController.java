package ftnbooking.backend.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.FeatureTypeService;
import ftnbooking.backend.types.FoodServiceType;
import ftnbooking.backend.types.FoodServiceTypeService;
import ftnbooking.backend.types.LodgingType;
import ftnbooking.backend.types.LodgingTypeService;
import ftnbooking.logging.LoggerManager;

@RestController
@RequestMapping("/api/registry")
public class RegistryController {
	
	@Autowired
	private LoggerManager logger;
	
	@Autowired
	private LodgingTypeService lodgingTypeService;
	
	@Autowired
	private FeatureTypeService featureTypeService;
	@Autowired
	private FoodServiceTypeService foodServiceTypeService;

	@PreAuthorize("hasAuthority('GET_REGISTRIES')")
	@RequestMapping(method = RequestMethod.GET, value="/lodging")
	public ResponseEntity<List<RegistryItemDTO>> getLodgings(Principal principal) 
	{
		List<RegistryItemDTO> retVal = new ArrayList<RegistryItemDTO>();
		
		for (LodgingType lodgingType : lodgingTypeService.findAll()) 
		{
			retVal.add(new RegistryItemDTO(lodgingType));
		}
		
		return new ResponseEntity<List<RegistryItemDTO>>(retVal, HttpStatus.OK);
	
	}
	@PreAuthorize("hasAuthority('GET_REGISTRIES')")
	@RequestMapping(method = RequestMethod.GET, value="/feature")
	public ResponseEntity<List<RegistryItemDTO>> getFeatures(Principal principal) 
	{
		List<RegistryItemDTO> retVal = new ArrayList<RegistryItemDTO>();
		
		for (FeatureType featureType : featureTypeService.findAll()) 
		{
			retVal.add(new RegistryItemDTO(featureType));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	
	}
	@PreAuthorize("hasAuthority('GET_REGISTRIES')")
	@RequestMapping(method = RequestMethod.GET, value="/food")
	public ResponseEntity<List<RegistryItemDTO>> getFoods(Principal principal) 
	{
		List<RegistryItemDTO> retVal = new ArrayList<RegistryItemDTO>();
		
		for (FoodServiceType foodServiceType : foodServiceTypeService.findAll()) 
		{
			retVal.add(new RegistryItemDTO(foodServiceType));
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	
	}
	@PreAuthorize("hasAuthority('UPDATE_REGISTRIES')")
	@RequestMapping(method = RequestMethod.POST, value="/lodgingRegistry")
	public ResponseEntity<?> addLodging(@RequestBody RegistryItemDTO registryItemDTO, Principal principal) 
	{
		String registry = "LODGING";
		String action;
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
			action = "ADDED";
		}else
		{
			
			LodgingType lodgingType = lodgingTypeService.findOne(registryItemDTO.getId());
			
			lodgingType.setActive(registryItemDTO.isActive());	
			lodgingTypeService.add(lodgingType);
			action="DEACTIVATED";
		}
		
		logger.logAdmin(action+" "+ registryItemDTO.getName().toUpperCase() + " TO " + registry + "REGISTRY ", principal.getName());
		
		return new ResponseEntity<>( HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('UPDATE_REGISTRIES')")
	@RequestMapping(method = RequestMethod.POST, value="/featureRegistry")
	public ResponseEntity<?> addFeature(@RequestBody RegistryItemDTO registryItemDTO, Principal principal) 
	{
		String registry = "FEATURE";
		String action;
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
			action = "ADDED";
		}else
		{
			
			FeatureType featureType = featureTypeService.findOne(registryItemDTO.getId());
			
			featureType.setActive(registryItemDTO.isActive());	
			featureTypeService.add(featureType);
			action="DEACTIVATED";
			
		}
		logger.logAdmin(action+" "+ registryItemDTO.getName().toUpperCase() + " TO " + registry + "REGISTRY ", principal.getName());
		return new ResponseEntity<>( HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('UPDATE_REGISTRIES')")
	@RequestMapping(method = RequestMethod.POST, value="/foodRegistry")
	public ResponseEntity<?> addFood(@RequestBody RegistryItemDTO registryItemDTO, Principal principal) 
	{
		String registry = "FOOD";
		String action;
		long id =0;
		try
		{
			id =registryItemDTO.getId();
		}catch(Exception ex)
		{
			
		}
		if(id==0)
		{
			FoodServiceType foodServiceType = new FoodServiceType();
		
			foodServiceType.setName(registryItemDTO.getName());
			foodServiceType.setActive(true);	
			foodServiceTypeService.add(foodServiceType);
			action = "ADDED";
		}else
		{
			
			FoodServiceType foodServiceType = foodServiceTypeService.findOne(registryItemDTO.getId());
			
			foodServiceType.setActive(registryItemDTO.isActive());	
			foodServiceTypeService.add(foodServiceType);
			action="DEACTIVATED";
			
		}
		logger.logAdmin(action+" "+ registryItemDTO.getName().toUpperCase() + " TO " + registry + "REGISTRY ", principal.getName());
		return new ResponseEntity<>( HttpStatus.OK);
	}
	

}
