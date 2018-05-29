package com.bmp.projectmanager.application.web.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bmp.projectmanager.application.web.form.UserRegistrationForm;

@Component
public class UserRegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserRegistrationForm userRegistrationForm = (UserRegistrationForm) target;

		if(userRegistrationForm.getEmail() == null || userRegistrationForm.getEmail().length() == 0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email is empty");
		}

		if (!userRegistrationForm.getPassword().equals(userRegistrationForm.getPasswordConfirm())) {
		    errors.rejectValue("password", "password.error", "Password and Password confirm fields should be equal");
		}


	}

}
