package ftn.booking.agent;

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
    protected String name;
	
    @Autowired
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
	private AccomodationType accomodationType;
    
    @XmlElement(required = true)
    protected String location;
    
    @XmlElement(required = true)
    protected String description;
    
    @Autowired
	@XmlElement(name = "PotentialReservation", required = true)
    @OneToMany(mappedBy = "Accomodation", cascade = CascadeType.REMOVE)
    protected List<PotentialReservation> potentialReservation;
    
    @Autowired
	@XmlElement(name = "Slika", required = true)
    @OneToMany(mappedBy = "Accomodation", cascade = CascadeType.REMOVE)
    protected List<Slika> slika;
    
    @ManyToOne(fetch = FetchType.EAGER)
	private AccomodationCategory accomodationCategory;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @XmlElement(name = "Agent", required = true)
    private List<ApplicationUser> agent;
}
