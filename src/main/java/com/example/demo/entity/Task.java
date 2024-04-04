package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("userstory_id")
    @ManyToOne
    @JoinColumn(name="userstory_id")
    private UserStory userStory;


    @Column
    private String title;
    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    public enum Status {
        TO_DO,
        IN_PROGRESS,
        DONE
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserStory getUserstory_id() {
        return userStory;
    }

    public void setUserstory_id(UserStory userStory) {
        this.userStory = userStory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
