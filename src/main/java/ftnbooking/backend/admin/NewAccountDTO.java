package ftnbooking.backend.admin;

public class NewAccountDTO {
	private String email;
    private String name;
    private String lastname;
    private String city;
    private String phone;

    public NewAccountDTO()
    {
    }

    public NewAccountDTO(String email, String name, String lastname, String city, String phone)
    {
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.phone = phone;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    

    
}
