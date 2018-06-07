package com.bmp.projectmanager.application.web.form;

import org.springframework.lang.NonNull;

public class ProjectUsersForm {

	@NonNull
	Long[] usersId;

	@NonNull
	Long projectId;

	public Long[] getUsersId() {
		return usersId;
	}

	public void setUsersId(Long[] usersId) {
		this.usersId = usersId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
