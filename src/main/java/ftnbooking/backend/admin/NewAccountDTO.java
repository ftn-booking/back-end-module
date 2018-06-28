package ftnbooking.backend.admin;

public class NewAccountDTO {
	private String email;
    private String name;
    private String password;
    private String lastname;
    private String city;
    private String phone;
    private String pid;

    public NewAccountDTO()
    {
    }

    public NewAccountDTO(String email, String name, String lastname, String city, String phone, String pid)
    {
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.phone = phone;
        this.pid =pid;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    

    
}
