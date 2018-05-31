package com.bmp.projectmanager.application.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import org.springframework.web.util.HtmlUtils;

public class UserRegistrationForm {

	@NotNull
	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotBlank
	private String firstName;

	@NotNull
	@NotBlank
	private String lastName;

	@NotNull
	@Size(min=3, max=30)
	@Pattern(flags=Flag.UNICODE_CASE, regexp="^(?=.*\\d).{4,8}$", message="Password should have at least 4 symbol and one digital")
	private String password;

	@NotNull(message="Password should be equals")
	private String passwordConfirm;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = HtmlUtils.htmlEscape(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = HtmlUtils.htmlEscape(lastName);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		if (!this.password.equals(this.passwordConfirm)) {
			this.passwordConfirm = null;
		}
	}

}
