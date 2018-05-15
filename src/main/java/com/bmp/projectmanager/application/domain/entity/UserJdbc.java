package com.bmp.projectmanager.application.domain.entity;

import java.io.Serializable;

public class UserJdbc implements Serializable {

	private static final long serialVersionUID = 6216344084865363418L;

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + "]";
    }

}
