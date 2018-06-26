package ftnbooking.backend.users.privileges;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnbooking.backend.users.ApplicationUserType;

@Entity
public class Privilege {

	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private ApplicationUserType forRole;

	public Privilege() {}

	public Privilege(String name, ApplicationUserType forRole) {
		this.name = name;
		this.forRole = forRole;
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

	public ApplicationUserType getForRole() {
		return forRole;
	}

	public void setForRole(ApplicationUserType forRole) {
		this.forRole = forRole;
	}

}
