package com.bmp.projectmanager.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmp.projectmanager.application.domain.entity.Activity;
import com.bmp.projectmanager.application.domain.entity.Issue;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    public List<Activity> findAllTop10ByIssueAndStatusOrderByUpdatedDesc(Issue issue, int status);

}