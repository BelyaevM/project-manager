package com.bmp.projectmanager.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmp.projectmanager.application.domain.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}