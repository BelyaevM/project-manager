package com.bmp.projectmanager.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmp.projectmanager.application.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}