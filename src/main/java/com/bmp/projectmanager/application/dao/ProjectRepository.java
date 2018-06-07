package com.bmp.projectmanager.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bmp.projectmanager.application.domain.entity.Project;
import com.bmp.projectmanager.application.domain.entity.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findAllByStatus(int status);

    @Query("SELECT u.projects FROM User u WHERE u = :user and status = :status")
    public List<Project> getProjectsByUser(@Param("user") User user, @Param("status") int status);

}