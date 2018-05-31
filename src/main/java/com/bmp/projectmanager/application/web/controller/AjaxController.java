package com.bmp.projectmanager.application.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.bmp.projectmanager.application.domain.services.UserDomainServices;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private UserDomainServices userService;

    @RequestMapping(value="/user/filter", produces=MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String userFilter(@RequestParam("q") String pattern, ModelAndView modelAndView) {
        return userService.searchUsersByPatternAsJson(HtmlUtils.htmlEscape(pattern));
    }

}
