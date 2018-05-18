package com.bmp.projectmanager.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bmp.projectmanager.application.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIdNot(Long ID);

    @Query("Select u from User u where u.firstName like %?1")
    List<User> findByFirstnameEndsWith(String firstName);

}
