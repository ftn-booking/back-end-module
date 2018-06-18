package ftnbooking.backend.lodgings;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.LodgingType;
import ftnbooking.backend.users.ApplicationUser;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Lodging")
@Entity
public class Lodging {
	
	@XmlElement(required = true)
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@Version
	private Long version;

	@XmlElement(required = true)
	@Pattern(regexp = "(?U)[\\p{Alpha}\\h]*")
	@NotBlank
	private String name;

	@XmlElement(required = true)
	@Pattern(regexp = "(?U)[\\p{Alpha}\\h]*")
	@NotBlank
	private String address;

	@XmlElement(required = true)
	@NotBlank
	private String description;

	@XmlElement(required = true)
	@Max(5)
	@Min(0)
	private int category;

	/*@XmlElement(required = true)
	@Enumerated(EnumType.STRING)
	private LodgingType type;*/

	@XmlElement(required = true)
	@Max(5)
	@Min(1)
	private Integer rating;

	@XmlElement(required = true)
	@Min(1)
	private int numberOfBeds;

	/*
	@XmlElement(required = true)
	private boolean hasParking;

	@XmlElement(required = true)
	private boolean hasWifi;

	@XmlElement(required = true)
	private boolean hasTv;

	@XmlElement(required = true)
	private boolean hasKitchen;

	@XmlElement(required = true)
	private boolean hasBathroom;

	@XmlElement(required = true)
	@Enumerated(EnumType.STRING)
	private FoodServiceType foodServiceType;

*/
	@XmlElement(required = true)
	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<FeatureType> featureType;
	
	@XmlElement(required = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private LodgingType lodgingType;
	
	@XmlElement(required = true)
	@ElementCollection
	private List<String> imagePaths = new ArrayList<String>();

	@XmlElement(required = true)
	@ManyToOne
	@NotNull
	private ApplicationUser agent;

	public Lodging() {}

	public Lodging(String name,
			String address,
			String description,
			int category,
			LodgingType lodgingType,
			int numberOfBeds,
			List<FeatureType> featureType,
			ApplicationUser agent) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.category = category;
		this.lodgingType = lodgingType;
		this.numberOfBeds = numberOfBeds;
		this.featureType = featureType;
		this.agent = agent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public LodgingType getLodgingType() {
		return lodgingType;
	}

	public void setLodgingType(LodgingType lodgingType) {
		this.lodgingType = lodgingType;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public List<FeatureType> getFeatureType() {
		return featureType;
	}

	public void setFeatureType(List<FeatureType> featureType) {
		this.featureType = featureType;
	}

	public List<String> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(List<String> imagePaths) {
		this.imagePaths = imagePaths;
	}

	public void addImagePath(String imagePath) {
		this.imagePaths.add(imagePath);
	}

	public ApplicationUser getAgent() {
		return agent;
	}

	public void setAgent(ApplicationUser agent) {
		this.agent = agent;
	}

}
