package ftnbooking.backend.reservations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.users.ApplicationUser;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@Version
	private Long version;

	@ManyToOne
	@NotNull
	private ApplicationUser user;

	@ManyToOne
	@NotNull
	private Lodging lodging;

	private long fromDate;

	private long toDate;

	public Reservation() {}

	public Reservation(ApplicationUser user,
			Lodging lodging,
			long fromDate,
			long toDate) {
		this.user = user;
		this.lodging = lodging;
		this.fromDate = fromDate;
		this.toDate = toDate;
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

	public long getFromDate() {
		return fromDate;
	}

	public void setFromDate(long fromDate) {
		this.fromDate = fromDate;
	}

	public long getToDate() {
		return toDate;
	}

	public void setToDate(long toDate) {
		this.toDate = toDate;
	}

}
