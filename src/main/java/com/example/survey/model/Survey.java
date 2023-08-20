package com.example.survey.model;

import jakarta.persistence.*;

@Entity
@Table
public class Survey {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String title;
    @Column
    private int user_id;

    @Column
    private String status;

    @Column
    private String created_at;

    public Survey(int id, String title, int user_id, String status, String created_at) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.status = status;
        this.created_at = created_at;
    }

    public Survey() {

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
