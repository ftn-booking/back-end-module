package ftnbooking.backend.messages;

public class MessageDTO {

	private Long reservationId;
	private String content;

	public MessageDTO() {}

	public MessageDTO(Long reservationId, String content) {
		this.reservationId = reservationId;
		this.content = content;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
