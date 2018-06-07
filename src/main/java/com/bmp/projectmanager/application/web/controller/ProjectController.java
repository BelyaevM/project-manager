package com.bmp.projectmanager.application.web.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.application.domain.entity.Project;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.services.ProjectDomainServices;
import com.bmp.projectmanager.application.domain.services.UserDomainServices;
import com.bmp.projectmanager.application.web.form.ProjectAddForm;
import com.bmp.projectmanager.application.web.form.ProjectSettingsForm;
import com.bmp.projectmanager.application.web.form.ProjectUsersForm;
import com.bmp.projectmanager.spring.UserDetailsImpl;


@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectDomainServices projectDomainServices;

	@Autowired
	private UserDomainServices userDomainServices;

	@GetMapping("/list")
    public ModelAndView list(ModelAndView model, Authentication auth) {

		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

		List<Project> projectList = projectDomainServices.getProjectList(userDetails.getUser());

		model.addObject("projectList", projectList);
		model.setViewName("project/list");
        return model;
    }

	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/add")
	public ModelAndView registration(ModelAndView modelAndView, ProjectAddForm projectAddForm) {

		modelAndView.addObject("projectForm", projectAddForm);
		modelAndView.setViewName("project/add");
		return modelAndView;
	}

	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/add")
	public @ResponseBody ModelAndView processConfirmationForm(ModelAndView modelAndView, Authentication auth,
			@Valid @ModelAttribute("projectForm") ProjectAddForm projectAddForm, BindingResult bindingResult) {

		modelAndView.setViewName("project/add");

		if (bindingResult.hasErrors()) {
            return modelAndView;
        }

		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

		projectDomainServices.addProject(projectAddForm, userDetails.getUser());

		modelAndView.setViewName("redirect:/project/list");
		return modelAndView;
	}


	@GetMapping("/overview/{id}")
    public ModelAndView view(ModelAndView model, Authentication auth, @PathVariable Long id) {
		Project project = projectDomainServices.getProjectById(id);

		if (project == null) {
			model.addObject("projectError", 1);
		}

		model.addObject("project", project);
		model.setViewName("project/overview");
        return model;
    }

	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/managementDevelopers/{id}")
    public ModelAndView managementDevelopers(ModelAndView model, Authentication auth,
    		@PathVariable Long id) {

		Project project = projectDomainServices.getProjectById(id);

		if (project == null) {
			model.addObject("projectError", 1);
		}

		ProjectUsersForm projectUsersForm = new ProjectUsersForm();

		List<Long> userIds = new LinkedList<>();

		for (User dev: project.getUsers()) {
			userIds.add(dev.getId());
		}

		projectUsersForm.setUsersId(userIds.toArray(new Long[userIds.size()]));
		projectUsersForm.setProjectId(project.getId());

		model.addObject("project", project);
		model.addObject("projectUsersForm", projectUsersForm);
		model.addObject("users", userDomainServices.getList());
		model.setViewName("project/managementdev");
        return model;
    }

	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/managementDevelopers")
	public @ResponseBody ModelAndView managementDevelopers(ModelAndView modelAndView, Authentication auth,
			@Valid @ModelAttribute("projectUsersForm") ProjectUsersForm projectUsersForm, BindingResult bindingResult) {

		modelAndView.setViewName("project/managementdev");

		if (bindingResult.hasErrors()) {
            return modelAndView;
        }

		Project project = projectDomainServices.getProjectById(projectUsersForm.getProjectId());
		project.getUsers().clear();
		project.getUsers().add(project.getManager());

		for (Long userId : projectUsersForm.getUsersId()) {
			User user = userDomainServices.getById(userId);

			project.getUsers().add(user);
			user.getProjects().add(project);
		}

		projectDomainServices.saveOrUpdate(project);

		modelAndView.setViewName("redirect:/project/overview/" + project.getId());
		return modelAndView;
	}

	@GetMapping("/issues/{projectId}")
	public ModelAndView issues(ModelAndView model, @PathVariable Long projectId) {

		Project project = projectDomainServices.getProjectById(projectId);

		if (project == null) {
			model.addObject("projectError", 1);
		}

		model.addObject("project", project);
		model.setViewName("project/issues");
		return model;
	}

    @GetMapping("/settings/{projectId}")
    public ModelAndView settings(ModelAndView model, @PathVariable Long projectId, Authentication auth) {

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Project prj = projectDomainServices.getProjectById(projectId);
        ProjectSettingsForm projectSettingsForm = new ProjectSettingsForm(prj);

        if (prj != null && prj.getManager().equals(userDetails.getUser())) {
            model.addObject("projectStatuses", projectDomainServices.getProjectStatuses());
            model.addObject(projectSettingsForm);
            model.setViewName("project/settings");
        } else {
            model.setViewName("redirect:/access-denied");
        }

        return model;
    }

    @PostMapping("/settings")
    public ModelAndView settingsPost(ModelAndView modelAndView, Authentication auth,
            @Valid @ModelAttribute("projectSettingsForm") ProjectSettingsForm projectSettingsForm, BindingResult bindingResult) {

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        modelAndView.setViewName("project/settings/");

        if (bindingResult.hasErrors()) {
            return modelAndView;
        }

        Project project = projectDomainServices.getProjectById(projectSettingsForm.getId());

        if (project.getManager().equals(userDetails.getUser())) {
            projectDomainServices.updateProject(project, projectSettingsForm);
        }

        modelAndView.setViewName("redirect:/project/overview/" + project.getId());
        return modelAndView;
    }

}
