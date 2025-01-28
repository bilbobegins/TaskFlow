package com.bilbobegins1997.TaskFlow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private   String title;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private   User user;

    private Long userTodoId;
    private  String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getUserTodoId() {
        return userTodoId;
    }

    public void setUserTodoId(Long userTodoId) {
        this.userTodoId = userTodoId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
