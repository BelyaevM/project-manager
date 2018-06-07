package com.bmp.projectmanager.application.domain.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.bmp.projectmanager.application.dao.IssueRepository;
import com.bmp.projectmanager.application.domain.entity.Issue;
import com.bmp.projectmanager.application.domain.entity.Project;
import com.bmp.projectmanager.application.domain.entity.User;
import com.bmp.projectmanager.application.web.form.IssueCreateForm;

@Component
public class IssueDomainServices {

    @Autowired
    private ProjectDomainServices projectDomainServices;

    @Autowired
    private UserDomainServices userDomainServices;

    @Autowired
    private IssueRepository issueRepository;

    public Map<Long, String> getIssueStatuses() {
        Map<Long, String> issuesMap = new HashMap<>();

        issuesMap.put((long)Issue.STATUS_NEW, "New");
        issuesMap.put((long)Issue.STATUS_UNDERWAY, "Underway");
        issuesMap.put((long)Issue.STATUS_DONE, "Done");
        issuesMap.put((long)Issue.STATUS_CLOSED, "Closed");

        return issuesMap;
    }

    public Map<Long, String> getIssuePriorities() {
        Map<Long, String> issuesMap = new TreeMap<>();

        issuesMap.put((long)Issue.PRIORITY_LOW, "Low");
        issuesMap.put((long)Issue.PRIORITY_NORMAL, "Normal");
        issuesMap.put((long)Issue.PRIORITY_HIGH, "High");

        return issuesMap;
    }

    public void createIssue(@Valid IssueCreateForm issueCreateForm, User user) {
        Project project = projectDomainServices.getProjectById(issueCreateForm.getProjectId());
        User performer = userDomainServices.getById(issueCreateForm.getPerformerId());
        Issue issue = new Issue();
        BeanUtils.copyProperties(issueCreateForm, issue);
        issue.setProject(project);
        issue.setOwner(user);
        issue.setPriority(issueCreateForm.getPriority().intValue());
        issue.setStatus(Issue.STATUS_NEW);
        issue.setPerformer(performer);
        issue.setCreated(new Date());
        issue.setUpdated(new Date());

        issueRepository.saveAndFlush(issue);
    }

    public List<Issue> getUserActiveIssues(User user, int limit) {
        PageRequest page = null;
        if (limit > 0) {
            page = PageRequest.of(0, limit);
        }
        return issueRepository.findAllActiveForUser(user, Issue.STATUS_CLOSED, page);
    }

    public Issue getById(Long id) {
        Optional<Issue> issueOptional = issueRepository.findById(id);

        if (issueOptional.isPresent()) {
            return issueOptional.get();
        }

        return null;
    }

    public boolean isUserHaveAccess(Issue issue, User user) {
        return issue.getProject().getUsers().contains(user);
    }

}
