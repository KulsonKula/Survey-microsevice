package com.example.survey.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
public class Survey {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String title;

    @Column
    private String status;

    @Column
    private String created_at;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    public Survey() {
    }
}
