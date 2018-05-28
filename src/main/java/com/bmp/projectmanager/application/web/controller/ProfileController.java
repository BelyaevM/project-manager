package com.bmp.projectmanager.application.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.application.domain.services.UserDomainServices;
import com.bmp.projectmanager.spring.UserDetailsImpl;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UserDomainServices userService;

	@RequestMapping(value="/activate", method=RequestMethod.GET)
	public ModelAndView activate(ModelAndView modelAndView,
			@RequestParam("email") String email, @RequestParam("token") String token) {

		modelAndView.addObject("result", userService.userActivate(email, token));

		modelAndView.setViewName("profile/activate");
		return modelAndView;
	}

    @GetMapping("/view")
    public ModelAndView profile(ModelAndView modelAndView, Authentication auth) {

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        modelAndView.addObject("user", userDetails.getUser());
        modelAndView.setViewName("profile/profile");

        return modelAndView;
    }

}
