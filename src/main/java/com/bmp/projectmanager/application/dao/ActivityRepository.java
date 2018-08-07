package com.bmp.projectmanager.application.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bmp.projectmanager.application.domain.entity.Activity;
import com.bmp.projectmanager.application.domain.entity.Issue;
import com.bmp.projectmanager.application.domain.entity.User;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    public List<Activity> findAllTop10ByIssueAndStatusOrderByUpdatedDesc(Issue issue, int status);
    public List<Activity> findAllByUserAndStatusOrderByUpdatedDesc(User user, int status, Pageable pageable);

    @Query("Select sum(a.spentTime) from Activity a where issueId = :issue and status = :statusCode")
    public Integer findTotalSpentTimeForIssue(@Param("issue") Issue issue, @Param("statusCode") int statusCode);
}