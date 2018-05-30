package com.bmp.projectmanager.application.web.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.services.UserDomainServices;
import com.bmp.projectmanager.application.web.form.UserRegistrationForm;

@Component
public class UserRegistrationValidator implements Validator {

    @Autowired
    UserDomainServices userDomainService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserRegistrationForm userRegistrationForm = (UserRegistrationForm) target;

		User user = userDomainService.getByEmail(userRegistrationForm.getEmail());

		if (user != null) {
		    errors.rejectValue("email", "email.error.duplicate.exists", "User with this email already exists.");
		}


	}

}
