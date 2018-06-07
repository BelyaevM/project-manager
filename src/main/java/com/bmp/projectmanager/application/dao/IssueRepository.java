package com.bmp.projectmanager.application.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bmp.projectmanager.application.domain.entity.Issue;
import com.bmp.projectmanager.application.domain.entity.Project;
import com.bmp.projectmanager.application.domain.entity.User;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("Select i from Issue i where project = :project order by updated desc")
    public List<Issue> getIssuesForProject(@Param("project") Project project);

    @Query("Select i from Issue i where performerId = :user and status != :statusClose order by updated desc")
    public List<Issue> findAllActiveForUser(@Param("user") User user, @Param("statusClose") int statusClose, Pageable pageRequest);

}