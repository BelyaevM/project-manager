package com.bmp.projectmanager.application.web.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.bmp.projectmanager.application.domain.entity.Issue;

public class IssueCreateForm {

    @NotNull
    @NotBlank
	private String subject;

    @NotNull
    @NotBlank
	private String description;

    @NotNull
	private Long performerId;

    @NotNull
    private Long issueStatus;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date due;

	@NotNull
	private Long priority = (long) Issue.PRIORITY_NORMAL;


	@NotNull
	private Long projectId;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPerformerId() {
		return performerId;
	}

	public void setPerformerId(Long performerId) {
		this.performerId = performerId;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

    public Long getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(Long issueStatus) {
        this.issueStatus = issueStatus;
    }

}
