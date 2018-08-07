package com.bmp.projectmanager.application.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.application.domain.entity.Issue;
import com.bmp.projectmanager.application.domain.entity.Project;
import com.bmp.projectmanager.application.domain.services.ActivityDomainServices;
import com.bmp.projectmanager.application.domain.services.IssueDomainServices;
import com.bmp.projectmanager.application.domain.services.ProjectDomainServices;
import com.bmp.projectmanager.application.web.form.ActivityCreateForm;
import com.bmp.projectmanager.application.web.form.IssueCreateForm;
import com.bmp.projectmanager.spring.UserDetailsImpl;


@Controller
@RequestMapping("/issue")
public class IssueController {

	@Autowired
	private ProjectDomainServices projectDomainServices;

    @Autowired
    private IssueDomainServices issueDomainServices;

    @Autowired
    private ActivityDomainServices activityDomainServices;

    @InitBinder
    public void initBinder(WebDataBinder binder){
         binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }

	@GetMapping("/list")
    public ModelAndView list(ModelAndView model, Authentication auth) {

		model.setViewName("issue/list");
        return model;
    }

	@GetMapping("/create/{projectId}")
	public ModelAndView registration(ModelAndView modelAndView, @PathVariable Long projectId, IssueCreateForm issueCreateForm, Authentication auth) {

		Project project = projectDomainServices.getProjectById(projectId);

		issueCreateForm.setProjectId(projectId);

		modelAndView.addObject("issueCreateForm", issueCreateForm);
		modelAndView.addObject("performers", projectDomainServices.getProjectPerformers(project));
		modelAndView.addObject("issueStatuses", issueDomainServices.getIssueStatuses());
		modelAndView.addObject("issuePriorities", issueDomainServices.getIssuePriorities());
		modelAndView.addObject("project", project);
		modelAndView.setViewName("issue/create");
		return modelAndView;
	}

	@PostMapping("/create")
	public @ResponseBody ModelAndView processConfirmationForm(ModelAndView modelAndView, Authentication auth,
			@Valid @ModelAttribute("issueCreateForm") IssueCreateForm issueCreateForm, BindingResult bindingResult) {

		modelAndView.setViewName("issue/create");

		if (bindingResult.hasErrors()) {
            return modelAndView;
        }

		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

		issueDomainServices.createIssue(issueCreateForm, userDetails.getUser());

		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@GetMapping("/overview/{issueId}")
	public ModelAndView issueOverview(ModelAndView modelAndView, @PathVariable Long issueId, Authentication auth) {

	    Issue issue = issueDomainServices.getById(issueId);
	    Project project = issue.getProject();
	    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

	    if (!project.getUsers().contains(userDetails.getUser())) {
	        modelAndView.setViewName("redirect:/issue/accessDenied");
	    } else {
	        modelAndView.addObject("activityList", activityDomainServices.getLastTenForIssue(issue));
	        modelAndView.addObject("issue", issue);
	        modelAndView.addObject("totalSpent", activityDomainServices.getTotalSpentTime(issue));
	        modelAndView.setViewName("issue/issueOverview");
	    }

	    return modelAndView;
	}

	@GetMapping("/{issueId}/activity/new")
	public ModelAndView addActivity(ModelAndView modelAndView, @PathVariable Long issueId, Authentication auth, ActivityCreateForm activityCreateForm) {

	    Issue issue = issueDomainServices.getById(issueId);
	    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        if (!issueDomainServices.isUserHaveAccess(issue, userDetails.getUser())) {
            modelAndView.setViewName("redirect:/issue/accessDenied");
        } else {
            modelAndView.addObject("issueId", issueId);
            modelAndView.addObject(activityCreateForm);
            modelAndView.addObject("issue", issue);
            modelAndView.setViewName("issue/activityAdd");
        }


	    return modelAndView;
	}

    @PostMapping("/{issueId}/activity/new")
    public @ResponseBody ModelAndView addActivityPost(ModelAndView modelAndView, @PathVariable Long issueId, Authentication auth,
            @Valid @ModelAttribute("activityCreateForm") ActivityCreateForm activityCreateForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("issueId", issueId);
            modelAndView.setViewName("issue/activityAdd");
            return modelAndView;
        }

        Issue issue = issueDomainServices.getById(issueId);
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        if (!issueDomainServices.isUserHaveAccess(issue, userDetails.getUser())) {
            modelAndView.setViewName("redirect:/issue/accessDenied");
        } else {
            activityDomainServices.createActivity(issue, activityCreateForm, userDetails.getUser());
            modelAndView.setViewName("redirect:/issue/overview/" + issueId);
        }

        return modelAndView;
    }


}
