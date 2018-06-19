package ftnbooking.backend.admin;

import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.FoodServiceType;
import ftnbooking.backend.types.LodgingType;

public class RegistryItemDTO {

	private Long id;
	private String name;
	private boolean active;
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public RegistryItemDTO(long id, String name, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
	}
	public RegistryItemDTO() {
		super();
	}
	public RegistryItemDTO(LodgingType lodgingType) 
	{
		// TODO Auto-generated constructor stub
		id = lodgingType.getId();
		name = lodgingType.getName();
		active = lodgingType.isActive();
		
	}
	public RegistryItemDTO(FeatureType featureType) 
	{
		// TODO Auto-generated constructor stub
		id = featureType.getId();
		name = featureType.getName();
		active = featureType.isActive();
	}
	public RegistryItemDTO(FoodServiceType foodServiceType) {
		// TODO Auto-generated constructor stub
		id = foodServiceType.getId();
		name = foodServiceType.getName();
		active = foodServiceType.isActive();
	}
	
	
	
}
