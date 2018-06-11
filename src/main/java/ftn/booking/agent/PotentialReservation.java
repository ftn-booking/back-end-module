package ftn.booking.agent;

import java.math.BigDecimal;
import java.sql.Date;
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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "dateFrom",
    "dateTo",
    "price",
    "brojLezaja",
    "brojSlobodnihRezervacija",
    "reservations",
    "usluga"
})
@Entity
public class PotentialReservation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private Date dateFrom;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private Date dateTo;
    
    @XmlElement(required = true)
    private BigDecimal price;
    
    private int brojLezaja;
    
    private int brojSlobodnihRezervacija;
    
    @XmlElement(name = "Reservation", required = true)
    @OneToMany(mappedBy = "PotentialReservation", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Accomodation accomodation;
    
    @OneToMany(mappedBy = "ponuda", cascade = CascadeType.REMOVE)
    private List<PonudjenaUsluga> ponudjenaUsluga;
    
    public PotentialReservation() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getBrojLezaja() {
		return brojLezaja;
	}

	public void setBrojLezaja(int brojLezaja) {
		this.brojLezaja = brojLezaja;
	}

	public int getBrojSlobodnihRezervacija() {
		return brojSlobodnihRezervacija;
	}

	public void setBrojSlobodnihRezervacija(int brojSlobodnihRezervacija) {
		this.brojSlobodnihRezervacija = brojSlobodnihRezervacija;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public List<PonudjenaUsluga> getPonudjenaUsluga() {
		return ponudjenaUsluga;
	}

	public void setPonudjenaUsluga(List<PonudjenaUsluga> ponudjenaUsluga) {
		this.ponudjenaUsluga = ponudjenaUsluga;
	}
    
    

}
