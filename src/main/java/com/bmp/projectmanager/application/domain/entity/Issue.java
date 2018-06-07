package com.bmp.projectmanager.application.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "issue")
public class Issue {

    public static int STATUS_NEW = 0;
    public static int STATUS_UNDERWAY = 10;
    public static int STATUS_DONE = 20;
    public static int STATUS_CLOSED = 30;

    public static int PRIORITY_LOW = 10;
    public static int PRIORITY_NORMAL = 20;
    public static int PRIORITY_HIGH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject", length = 256, nullable = false)
    private String subject;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @OneToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private User owner;

    @OneToOne
    @JoinColumn(name = "performerId", nullable = false)
    private User performer;

    @Column(name = "due", nullable = true)
    private Date due;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "updated", nullable = false)
    private Date updated;

    @Column(name = "status", nullable = false)
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getPerformer() {
        return performer;
    }

    public void setPerformer(User performer) {
        this.performer = performer;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPriorityStr() {
        if (priority == PRIORITY_LOW) {
            return "Low";
        } else if (priority == PRIORITY_NORMAL) {
            return "Normal";
        } else if (priority == PRIORITY_HIGH) {
            return "High";
        }

        return "Low";
    }

    public String getStatusStr() {
        if (status == STATUS_NEW) {
            return "New";
        } else if (status == STATUS_UNDERWAY) {
            return "In progress";
        } else if (status == STATUS_DONE) {
            return "Done";
        } else if (status == STATUS_CLOSED) {
            return "Closed";
        }

        return "New";
    }

}
