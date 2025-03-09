package com.bilbobegins1997.TaskFlow.service;
import com.bilbobegins1997.TaskFlow.entity.Todo;
import com.bilbobegins1997.TaskFlow.entity.TodoUser;
import com.bilbobegins1997.TaskFlow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final UserRepository userRepository;

    public TodoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Todo> getTasksByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(TodoUser::getTodos) // If user exists, return their todos
                .orElse(Collections.emptyList()); // If user not found, return an empty list
    }



}