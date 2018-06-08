package com.bmp.projectmanager.application.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.application.domain.services.ActivityDomainServices;
import com.bmp.projectmanager.application.domain.services.IssueDomainServices;
import com.bmp.projectmanager.spring.UserDetailsImpl;

@Controller
public class IndexController {

    @Autowired
    IssueDomainServices issueDomainServices;

    @Autowired
    ActivityDomainServices activityDomainServices;

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        modelAndView.addObject("issues", issueDomainServices.getUserActiveIssues(userDetails.getUser(), 5));
        modelAndView.addObject("activities", activityDomainServices.getUserActivity(userDetails.getUser(), 10));
        modelAndView.setViewName("index");

        return modelAndView;
    }

}
