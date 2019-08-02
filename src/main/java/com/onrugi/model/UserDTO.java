package com.onrugi.model;

public class UserDTO {
	private Integer id;
	private String name;
	private String password;
	private Integer role;

	public UserDTO() {

	}

	public UserDTO(Integer id, String name, String password, Integer role) {
		super();
		this.name = name;
		this.password = password;
		this.id = id;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

}
