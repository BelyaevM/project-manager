package com.bmp.projectmanager.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmp.projectmanager.application.domain.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}