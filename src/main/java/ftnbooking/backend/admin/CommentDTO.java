package ftnbooking.backend.admin;

import ftnbooking.backend.comments.Comment;

public class CommentDTO {

	private Long id;
	private String content;
	public CommentDTO(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public CommentDTO() {
		super();
	}
	public CommentDTO(Comment comment) {
		// TODO Auto-generated constructor stub
		this.id = comment.getId();
		this.content = comment.getContent();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
