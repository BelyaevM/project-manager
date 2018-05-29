package com.bmp.projectmanager.application.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.application.dao.UserRepository;
import com.bmp.projectmanager.application.domain.entity.Role;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.services.UserDomainServices;
import com.bmp.projectmanager.application.web.form.UserRegistrationForm;
import com.bmp.projectmanager.application.web.form.UserSettingsForm;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserDomainServices userService;


//	@Autowired
//	private UserRegistrationValidator userRegistrationValidator;
//
//	@InitBinder("user")
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(userRegistrationValidator);
//	}

	@GetMapping("/list")
    public String list(Model model) {

		List<User> userList = userDao.findAll();

		model.addAttribute("users", userList);
        return "user/list";
    }

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(ModelAndView modelAndView, UserRegistrationForm userRegistrationForm) {
		modelAndView.addObject("user", userRegistrationForm);
		modelAndView.setViewName("user/registration");

		return modelAndView;
	}

	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public @ResponseBody ModelAndView processConfirmationForm(ModelAndView modelAndView,
			@Valid @ModelAttribute("user") UserRegistrationForm userRegistrationForm, BindingResult bindingResult, HttpServletRequest request) {

		modelAndView.setViewName("user/registration");

		if (bindingResult.hasErrors()) {
            return modelAndView;
        }

		userService.createNewUser(userRegistrationForm, request.getLocalName());

		modelAndView.setViewName("redirect:/user/list");
		return modelAndView;
	}

	@RequestMapping(value="/activate", method=RequestMethod.GET)
	public ModelAndView activate(ModelAndView modelAndView,
			@RequestParam("email") String email, @RequestParam("token") String token) {

	    try {
	        modelAndView.addObject("result", userService.userActivate(email, token));
	    } catch (Exception e) {
	        e.printStackTrace();
	        modelAndView.addObject("result", false);
	    }

		modelAndView.setViewName("user/activate");
		return modelAndView;
	}

    @GetMapping("/settings/{userId}")
    public ModelAndView settings(ModelAndView modelAndView, UserSettingsForm userSettingsForm, @PathVariable Long userId) {

        User user = userService.getById(userId);

        userSettingsForm.setEnabled(user.isEnabled());
        userSettingsForm.setUserId(userId);
        userSettingsForm.setRoles(user.getRolesList().toArray(new String[user.getRolesList().size()]));

        modelAndView.addObject("allRoles", new String[] {Role.ROLE_ADMIN, Role.ROLE_MANAGER, Role.ROLE_USER});
        modelAndView.addObject("userSettings", userSettingsForm);
        modelAndView.setViewName("user/settings");

        return modelAndView;
    }

    @PostMapping("/settings")
    public ModelAndView processSettings(ModelAndView modelAndView,
            @Valid @ModelAttribute("userSettings") UserSettingsForm userSettingsForm, BindingResult bindingResult) {

        modelAndView.setViewName("user/settings");

        if (bindingResult.hasErrors()) {
            return modelAndView;
        }

        userService.updateUserSettings(userSettingsForm);

        modelAndView.setViewName("redirect:/user/list");
        return modelAndView;
    }

}
