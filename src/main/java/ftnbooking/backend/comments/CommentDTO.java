package ftnbooking.backend.comments;

public class CommentDTO {

	private Long reservationId;
	private String content;

	public CommentDTO() {}

	public CommentDTO(Long reservationId, String content) {
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
