package com.bmp.projectmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.services.UserDomainServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectManagerApplicationTests {

    @Autowired
    private UserDomainServices userDomainService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void FindByEmailTest() {
	    String email = "admin@projm.com";
	    User found = userDomainService.getByEmail(email);

	    assertThat(found.getEmail()).isEqualTo(email);
	}

}
