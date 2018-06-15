package ftnbooking.backend.users;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class ApplicationUser {

	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@Version
	private Long version;

	@Email
	@NotBlank
	private String email;

	@Size(min = 6)
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Pattern(regexp = "(?U)\\p{Alpha}*")
	private String name;

	@Pattern(regexp = "(?U)\\p{Alpha}*")
	private String lastName;

	@Pattern(regexp = "(?U)[\\p{Alpha}\\h]*")
	private String city;

	@Pattern(regexp = "(\\d{9,10})|(^$)")
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private ApplicationUserType userType = ApplicationUserType.VISITOR;

	@JsonIgnore
	private String resetToken = UUID.randomUUID().toString();

	public ApplicationUser() {}

	public ApplicationUser(String email,
			String password,
			String name,
			String lastName,
			String city,
			String phoneNumber) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.phoneNumber = phoneNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ApplicationUserType getUserType() {
		return userType;
	}

	public void setUserType(ApplicationUserType userType) {
		this.userType = userType;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

}

