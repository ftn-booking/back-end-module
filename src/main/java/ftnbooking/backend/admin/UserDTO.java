package ftnbooking.backend.admin;

import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserType;

public class UserDTO {
	
	private Long id;
	private String email;
	private ApplicationUserType userType;	
	//private int accountState;
	private Boolean isActive;
	private Boolean isBanned;
	public UserDTO() {
		super();
	}
	public UserDTO(ApplicationUser user)
	{
		this.id=user.getId();
		this.email = user.getEmail();
		this.userType = user.getUserType();
		/*if(user.isBanned() && user.isActive() )
		{
			accountState=2;
		}else if(user.isBanned() &&!user.isActive())
		{
			accountState=3;
		}else if(!user.isBanned() &&!user.isActive())
			accountState=1;
		else
		{
			accountState=0;
		}*/
		this.isActive = user.isActive();
		this.isBanned = user.isBanned();
	}
	
	
	public UserDTO(Long id, String email, ApplicationUserType userType, Boolean isActive, Boolean isBanned) {
		super();
		this.id = id;
		this.email = email;
		this.userType = userType;
		this.isActive = isActive;
		this.isBanned = isBanned;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ApplicationUserType getUserType() {
		return userType;
	}
	public void setUserType(ApplicationUserType userType) {
		this.userType = userType;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean isBanned() {
		return isBanned;
	}
	public void setBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	

	
	
}
