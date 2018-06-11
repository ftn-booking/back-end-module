package ftnbooking.backend.agent;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class PonudjenaUsluga {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private PotentialReservation potentialReservation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usluga usluga;
	
	public PonudjenaUsluga() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PotentialReservation getPotentialReservation() {
		return potentialReservation;
	}

	public void setPotentialReservation(PotentialReservation potentialReservation) {
		this.potentialReservation = potentialReservation;
	}

	public Usluga getUsluga() {
		return usluga;
	}

	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}
	
	
}
