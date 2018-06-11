package ftnbooking.backend.agent;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import ftnbooking.backend.users.ApplicationUser;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@XmlElement(required = true)
    private boolean realized;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private Date dateOfReservation;
    
    @ManyToOne(fetch = FetchType.EAGER)
	private PotentialReservation potentialReservation;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private ApplicationUser reservee;
    
    public Reservation() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRealized() {
		return realized;
	}

	public void setRealized(boolean realized) {
		this.realized = realized;
	}

	public Date getDateOfReservation() {
		return dateOfReservation;
	}

	public void setDateOfReservation(Date dateOfReservation) {
		this.dateOfReservation = dateOfReservation;
	}

	public PotentialReservation getPotentialReservation() {
		return potentialReservation;
	}

	public void setPotentialReservation(PotentialReservation potentialReservation) {
		this.potentialReservation = potentialReservation;
	}

	public ApplicationUser getReservee() {
		return reservee;
	}

	public void setReservee(ApplicationUser reservee) {
		this.reservee = reservee;
	}
    
    
}
