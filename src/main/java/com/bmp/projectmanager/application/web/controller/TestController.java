package com.bmp.projectmanager.application.web.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bmp.projectmanager.application.dao.UserRepository;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.entity.UserJdbc;

@Controller
@RequestMapping("/test")
public class TestController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/jdbc")
    public ModelAndView index(ModelAndView modelAndView) {

        List<UserJdbc> users = this.jdbcTemplate.query(
                "select id, email, firstName, lastName, password from user",
                new RowMapper<UserJdbc>() {
                    @Override
                    public UserJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserJdbc user = new UserJdbc();
                        user.setId(rs.getLong("id"));
                        user.setEmail(rs.getString("email"));
                        user.setFirstName(rs.getString("firstName"));
                        user.setLastName(rs.getString("lastName"));
                        return user;
                    }
                });

        modelAndView.addObject("users", users);
        modelAndView.setViewName("test/jdbc");

        return modelAndView;
    }

    @GetMapping("/jpa")
    public ModelAndView jpa(ModelAndView model) {

        List<User> users = userRepository.findByFirstnameEndsWith("eg");

        model.addObject("users", users);
        model.setViewName("test/jpa");

        return model;
    }

}
