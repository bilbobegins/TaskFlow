package com.bilbobegins1997.TaskFlow.controller;

import com.bilbobegins1997.TaskFlow.entity.Todo;
import com.bilbobegins1997.TaskFlow.entity.TodoUser;
import com.bilbobegins1997.TaskFlow.repository.TodoRepository;
import com.bilbobegins1997.TaskFlow.repository.UserRepository;
import com.bilbobegins1997.TaskFlow.service.TodoService;
import com.bilbobegins1997.TaskFlow.service.TodoUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final TodoService todoService;
    private final TodoUserService userService;


    TodoController(TodoRepository todoRepository, UserRepository userService, TodoService todoService, TodoUserService userService1){
        this.todoRepository = todoRepository;
        this.userRepository = userService;
        this.todoService = todoService;
        this.userService = userService1;
    }


    @GetMapping("/todo")
    public List<Todo> getTodos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String username = authentication.getName();

        TodoUser user = userRepository.findByLogin(username).orElseThrow(() -> new NoSuchElementException("no such user with login: " + username  ) );

        return todoService.getTasksByUserId(user.getId());
    }

    @GetMapping("/todo/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        String username = authentication.getName();
        TodoUser user = userRepository.findByLogin(username)
                .orElseThrow(() -> new NoSuchElementException("No user found with login: " + username));

        // Find the specific Todo by its ID
        return user.getTodos().stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No todo found with ID: " + id));
    }

    @PostMapping("/{userId}/todo")
    public ResponseEntity<Todo> saveTodo(@PathVariable Long userId,@RequestBody Todo todo){
        TodoUser user  = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(" no such ID,"+ userId));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        todo.setTodoUser(user);
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @DeleteMapping("todo/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }

    @PatchMapping("todo/{id}")
    public void patchbody(@RequestBody Todo todo,@PathVariable Long id){
        Todo temp =  todoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no "+id));
        temp.setTitle(todo.getTitle());
        todoRepository.save(temp);
    }



}
