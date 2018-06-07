package com.bmp.projectmanager.application.web.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.bmp.projectmanager.application.domain.entity.Project;

public class ProjectSettingsForm {

    @NotEmpty
	private String name;

	@NotEmpty
	private String description;

	@Min(0)
	private int prjStatus;

	private Long id;

	public ProjectSettingsForm() {};

	public ProjectSettingsForm(Project prj) {
	    this.id = prj.getId();
	    this.name = prj.getName();
	    this.description = prj.getDescription();
	    this.prjStatus = prj.getStatus();
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

    public int getPrjStatus() {
        return prjStatus;
    }

    public void setPrjStatus(int status) {
        this.prjStatus = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
