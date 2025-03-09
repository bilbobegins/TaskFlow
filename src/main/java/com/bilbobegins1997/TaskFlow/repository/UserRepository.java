package com.bilbobegins1997.TaskFlow.repository;

import com.bilbobegins1997.TaskFlow.entity.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TodoUser,Long> {
    Optional<TodoUser> findByLogin(String login);
}
