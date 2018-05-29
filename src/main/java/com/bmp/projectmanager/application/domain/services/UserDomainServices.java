package com.bmp.projectmanager.application.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.bmp.projectmanager.application.dao.UserRepository;
import com.bmp.projectmanager.application.domain.entity.Role;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.entity.UserRole;
import com.bmp.projectmanager.application.domain.mail.MailClient;
import com.bmp.projectmanager.application.web.form.UserRegistrationForm;
import com.bmp.projectmanager.application.web.form.UserSettingsForm;

@Component
public class UserDomainServices {

	@Autowired
	public UserRepository userDao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	MailClient mailClient;

	public List<User> getList() {
		return userDao.findAll();
	}

	public User createNewUser(UserRegistrationForm userRegistrationForm, String hostUrl) {
		User user = new User();

		BeanUtils.copyProperties(userRegistrationForm, user, "password", "passwordConfirm");
		user.setPassword(bCryptPasswordEncoder.encode(userRegistrationForm.getPassword()));

		UserRole role = UserRole.createUserRole(Role.ROLE_USER);
		role.setUser(user);

		user.getUserRoles().add(role);
		user.setEnabled(false);

		String token = user.getEmail() + user.getPassword() + new Date().toString();
		token = DigestUtils.md5DigestAsHex(token.getBytes());
		user.setToken(token);

		mailClient.sendRegistrationEmail(user, hostUrl);

		userDao.save(user);

		return user;
	}

	public boolean userActivate(String email, String token) {

		User user = userDao.getByEmail(email);

		if (user != null) {
			if (!token.isEmpty() && user.getToken().equals(token)) {
				user.setToken(null);
				user.setEnabled(true);
				userDao.save(user);
				return true;
			}
		}

		return false;
	}

	public User getById(Long id) {
		Optional<User> userOptional = userDao.findById(id);

		if (userOptional.isPresent()) {
			return userOptional.get();
		}

		return null;
	}

	public boolean hasRole(User u, String role) {

		if (u.getUserRoles() != null && u.getUserRoles().size() > 0) {
			for (UserRole userRole : u.getUserRoles()) {
				if (userRole.getRole().equals(role)) {
					return true;
				}
			}
		}

		return false;
	}

	public List<User> getListExceptSelf(Long id) {
		return userDao.findAllExcept(id);
	}

    public void updateUserSettings(@Valid UserSettingsForm userSettingsForm) {
        User user = this.getById(userSettingsForm.getUserId());

        user.setEnabled(userSettingsForm.getEnabled());

        user.getUserRoles().clear();

        for (String role : userSettingsForm.getRoles()) {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            user.getUserRoles().add(userRole);
        }

        userDao.saveAndFlush(user);
    }

}
