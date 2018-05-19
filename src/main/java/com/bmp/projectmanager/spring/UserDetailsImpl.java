package com.bmp.projectmanager.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.domain.entity.UserRole;

public class UserDetailsImpl implements UserDetails {

    private User user;
    private List<GrantedAuthority> roles = new ArrayList<>();

    public UserDetailsImpl(User user) {
        this.user = user;

        for (UserRole role : user.getUserRoles()) {
            GrantedAuthority auth = new SimpleGrantedAuthority(role.getRole());
            roles.add(auth);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

}
