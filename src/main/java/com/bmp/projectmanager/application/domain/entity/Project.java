package com.bmp.projectmanager.application.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {

	public static int STATUS_ACTIVE 	= 1;
	public static int STATUS_ARCHIVED 	= 0;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="name", length=64, nullable=false)
	private String name;

	@Column(name="description", length=256, nullable=true)
	private String description;

	@OneToOne
	@JoinColumn(name="userId", nullable=false)
	private User manager;

	@Column(name="created", nullable=false)
	private Date created;

	@Column(name="updated", nullable=false)
	private Date updated;

	@Column(name="status", nullable=false)
	private int status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_projects",
	    joinColumns = { @JoinColumn(name = "userId") },
	    inverseJoinColumns = { @JoinColumn(name = "projectId") })
    private Set<User> users = new HashSet<>();

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="projectId")
	private Set<Issue> issues = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date modified) {
		this.updated = modified;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<User> getUsers() {
		return users;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

}
