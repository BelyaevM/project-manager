package com.bmp.projectmanager.application.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 6216344084865363418L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="email", length=320, nullable=false)
	private String email;

	@Column(name="firstName", length=64, nullable=true)
	private String firstName;

	@Column(name="lastName", length=64, nullable=true)
	private String lastName;

	@Column(name="password", length=64, nullable=true)
	private String password;

	@Column(name="enabled")
	private boolean enabled;

	@Column(name="token", length=32, nullable=true)
	private String token;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user", orphanRemoval = true)
	private Set<UserRole> userRoles;

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="users")
    private Set<Project> projects = new HashSet<>();

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

	public Set<UserRole> getUserRoles() {
		if (userRoles == null) {
			userRoles = new HashSet<>();
		}
		return userRoles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + "]";
    }

	public String getHighLevelRole() {

	    List<String> allRoles = new ArrayList<>();

	    for (UserRole role : this.getUserRoles()) {
            allRoles.add(role.getRole());
        }

	    if (allRoles.contains(Role.ROLE_ADMIN)) {
	        return Role.ROLE_ADMIN;
	    } else if(allRoles.contains(Role.ROLE_MANAGER)) {
	        return Role.ROLE_MANAGER;
	    } else {
	        return Role.ROLE_USER;
	    }

	}

	public List<String> getRolesList() {
	    List<String> list = new ArrayList<>();

	    for (UserRole role : this.getUserRoles()) {
            list.add(role.getRole());
        }

	    return list;
	}

}
