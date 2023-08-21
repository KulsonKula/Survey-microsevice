package com.example.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
public class Question {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String text;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "survey_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Survey survey;


    public Question() {
    }

    public Question(long id, String text, String type, Survey survey) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.survey = survey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
