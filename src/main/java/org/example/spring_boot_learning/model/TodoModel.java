package org.example.spring_boot_learning.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "todos")
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private boolean completed;

    public TodoModel() {
    }
    public TodoModel(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
    public Long getId() {return id;}
    public String getTitle() {return title;}
    public boolean isCompleted() {return completed;}

    public void setTitle(String title) {
        this.title = title;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
