package ftnbooking.backend.reservations;

public class ReservationDTO {

	private long lodgingId;
	private long fromDate;
	private long toDate;

	public ReservationDTO() {}

	public ReservationDTO(long lodgingId, long fromDate, long toDate) {
		this.lodgingId = lodgingId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public long getLodgingId() {
		return lodgingId;
	}

	public void setLodgingId(long lodgingId) {
		this.lodgingId = lodgingId;
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
