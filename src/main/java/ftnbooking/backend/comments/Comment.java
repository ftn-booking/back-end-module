package ftnbooking.backend.comments;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Comment")
@Entity
public class Comment {
	
	@XmlElement(required = true)
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@XmlElement(required = true)
	@ManyToOne
	@NotNull
	private ApplicationUser user;

	@XmlElement(required = true)
	@ManyToOne
	@NotNull
	private Lodging lodging;
	
	@XmlElement(required = true)
	@NotNull
	private String content;
	
	@XmlElement(required = true)
	private Approval approved;
	
	public Comment() {
		
	}

	public Comment(@NotNull ApplicationUser user, 
			@NotNull Lodging lodging, 
			@NotNull String content) {
		super();
		this.user = user;
		this.lodging = lodging;
		this.content = content;
		this.approved = Approval.PENDING;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public Lodging getLodging() {
		return lodging;
	}

	public void setLodging(Lodging lodging) {
		this.lodging = lodging;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Approval getApproved() {
		return approved;
	}

	public void setApproved(Approval approved) {
		this.approved = approved;
	}
	
	
	
}
