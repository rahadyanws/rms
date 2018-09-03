package com.mitrais.rms.model;

public class User
{
    private Long id;
    private String userName;
    private String password;
    private String name;  
    private String email;
    private String gender;
    private String type;
    private String status;
	private String address;
	
	public User() {
		super();
	}
	
	public User(Long id, String userName, String password, String name, String email, String gender, String type,
			String status, String address) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.type = type;
		this.status = status;
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long i) {
		this.id = i;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
