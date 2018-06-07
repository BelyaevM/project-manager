package com.bmp.projectmanager.application.domain.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bmp.projectmanager.application.dao.ProjectRepository;
import com.bmp.projectmanager.application.domain.entity.Project;
import com.bmp.projectmanager.application.domain.entity.Role;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.web.form.ProjectAddForm;
import com.bmp.projectmanager.application.web.form.ProjectSettingsForm;

@Component
public class ProjectDomainServices {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserDomainServices userDomainServices;

	public List<Project> getProjectList(User u) {

		List<Project> projects = null;

		if (userDomainServices.hasRole(u, Role.ROLE_ADMIN)) {
			projects = projectRepository.findAllByStatus(Project.STATUS_ACTIVE);
		} else {
			projects = projectRepository.getProjectsByUser(u, Project.STATUS_ACTIVE);
		}

		return projects;
	}

	public void addProject(@Valid ProjectAddForm projectAddForm, User user) {
		User manager = userDomainServices.getById(user.getId());

		Project prj = new Project();
		prj.setName(projectAddForm.getName());
		prj.setDescription(projectAddForm.getDescription());
		prj.setManager(manager);
		prj.getUsers().add(manager);
		prj.setCreated(new Date());
		prj.setUpdated(new Date());
		prj.setStatus(Project.STATUS_ACTIVE);

		manager.getProjects().add(prj);

		projectRepository.save(prj);
	}

	public Project getProjectById(Long id) {

		Optional<Project> projectOptional = projectRepository.findById(id);

		if (projectOptional.isPresent()) {
			return projectOptional.get();
		}

		return null;
	}

	public void saveOrUpdate(Project project) {
		project.setUpdated(new Date());
		projectRepository.save(project);
	}

	public Map<Long, String> getProjectPerformers(Project project) {
	    Map<Long, String> performersMap = null;

		if (project != null) {
		    Set<User> performers = project.getUsers();
			performersMap = new HashMap<>();

			for (User performer : performers) {
				performersMap.put(performer.getId(), performer.getFirstName() + " " + performer.getLastName());
			}
		}

		return performersMap;
	}

    public Map<Integer, String> getProjectStatuses() {
        Map<Integer, String> issuesMap = new TreeMap<>();

        issuesMap.put(Project.STATUS_ACTIVE, "Active");
        issuesMap.put(Project.STATUS_ARCHIVED, "Archived");

        return issuesMap;
    }

    public void updateProject(Project project, ProjectSettingsForm projectSettingsForm) {
        BeanUtils.copyProperties(projectSettingsForm, project, "id");
        project.setStatus(projectSettingsForm.getPrjStatus());
        projectRepository.saveAndFlush(project);
    }

}
