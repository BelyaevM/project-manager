package com.bmp.projectmanager.application.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bmp.projectmanager.application.dao.ActivityRepository;
import com.bmp.projectmanager.application.domain.entity.Activity;
import com.bmp.projectmanager.application.domain.entity.Issue;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.web.form.ActivityCreateForm;

@Component
public class ActivityDomainServices {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity getById(Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);

        if (activityOptional.isPresent()) {
            return activityOptional.get();
        }

        return null;
    }

    public void createActivity(Issue issue, @Valid ActivityCreateForm activityCreateForm, User user) {
        Activity activity = new Activity();
        activity.setUser(user);
        activity.setProject(issue.getProject());
        activity.setIssue(issue);
        activity.setStatus(Activity.STATUS_DONE);

        BeanUtils.copyProperties(activityCreateForm, activity);

        issue.setUpdated(new Date());
        activityRepository.saveAndFlush(activity);
    }

    public List<Activity> getLastTenForIssue(Issue issue) {
        return activityRepository.findAllTop10ByIssueAndStatusOrderByUpdatedDesc(issue, Activity.STATUS_DONE);
    }

    public List<Activity> getUserActivity(User user, int limit) {
        Pageable page = null;
        if (limit > 0) {
            page = PageRequest.of(0, limit);
        }

        return activityRepository.findAllByUserAndStatusOrderByUpdatedDesc(user, Activity.STATUS_DONE, page);
    }

    public int getTotalSpentTime(Issue issue) {
        return activityRepository.findTotalSpentTimeForIssue(issue, Activity.STATUS_DONE);
    }

}
