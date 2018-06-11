package ftnbooking.backend.agent;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import ftnbooking.backend.users.ApplicationUser;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Accomodation")
@Entity
public class Accomodation {

	@XmlElement(required = true)
    private String name;
	
    @Autowired
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
	private AccomodationType accomodationType;
    
    @XmlElement(required = true)
    private String location;
    
    @XmlElement(required = true)
    private String description;
    
    @Autowired
	@XmlElement(name = "PotentialReservation", required = true)
    @OneToMany(mappedBy = "Accomodation", cascade = CascadeType.REMOVE)
    private List<PotentialReservation> potentialReservation;
    
    @Autowired
	@XmlElement(name = "Slika", required = true)
    @OneToMany(mappedBy = "Accomodation", cascade = CascadeType.REMOVE)
    private List<Slika> slika;
    
    @ManyToOne(fetch = FetchType.EAGER)
	private AccomodationCategory accomodationCategory;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private List<ApplicationUser> agent;
    
    public Accomodation() {
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccomodationType getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationType accomodationType) {
		this.accomodationType = accomodationType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PotentialReservation> getPotentialReservation() {
		return potentialReservation;
	}

	public void setPotentialReservation(List<PotentialReservation> potentialReservation) {
		this.potentialReservation = potentialReservation;
	}

	public List<Slika> getSlika() {
		return slika;
	}

	public void setSlika(List<Slika> slika) {
		this.slika = slika;
	}

	public AccomodationCategory getAccomodationCategory() {
		return accomodationCategory;
	}

	public void setAccomodationCategory(AccomodationCategory accomodationCategory) {
		this.accomodationCategory = accomodationCategory;
	}

	public List<ApplicationUser> getAgent() {
		return agent;
	}

	public void setAgent(List<ApplicationUser> agent) {
		this.agent = agent;
	}
    
    
}
