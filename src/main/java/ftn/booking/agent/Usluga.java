package ftn.booking.agent;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Usluga")
@Entity
public class Usluga {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @XmlElement(required = true)
    private String name;
    
    @OneToMany(mappedBy = "Usluga", cascade = CascadeType.REMOVE)
    public List<PonudjenaUsluga> ponudjenaUsluga;


    public Usluga() {
    	
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<PonudjenaUsluga> getPonudjenaUsluga() {
		return ponudjenaUsluga;
	}


	public void setPonudjenaUsluga(List<PonudjenaUsluga> ponudjenaUsluga) {
		this.ponudjenaUsluga = ponudjenaUsluga;
	}
    
    
}
