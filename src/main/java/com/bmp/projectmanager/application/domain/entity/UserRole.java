package com.bmp.projectmanager.application.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 6216344084865363418L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne
    @JoinColumn(name="userId", nullable=false)
 	private User user;

	@Column(name="role", length=64, nullable=false)
	private String role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static UserRole createUserRole(String role) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);

		return userRole;
	}

}
