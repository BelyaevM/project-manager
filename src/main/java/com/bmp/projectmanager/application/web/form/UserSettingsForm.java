package com.bmp.projectmanager.application.web.form;

import org.springframework.lang.NonNull;

public class UserSettingsForm {

    @NonNull
    String[] roles;

    @NonNull
    Long userId;

    Boolean enabled;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
