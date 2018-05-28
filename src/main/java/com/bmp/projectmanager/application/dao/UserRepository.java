package com.bmp.projectmanager.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bmp.projectmanager.application.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndEnabledTrue(String email);

    @Query("Select u from User u where email = :email")
    public User getByEmail(@Param("email") String email);

    @Query("Select u from User u where id <> :userId")
    public List<User> findAllExcept(@Param("userId") Long userId);

}