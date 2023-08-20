package com.example.survey.model;

import jakarta.persistence.*;

@Entity
@Table
public class Users {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String username;
    @Column
    private String password;

    public Users() {
    }
}
