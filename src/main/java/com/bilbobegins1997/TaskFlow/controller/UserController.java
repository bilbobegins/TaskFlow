package com.bilbobegins1997.TaskFlow.controller;

import com.bilbobegins1997.TaskFlow.entity.Todo;
import com.bilbobegins1997.TaskFlow.entity.User;
import com.bilbobegins1997.TaskFlow.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("todo")
public class UserController {

   final UserRepository userRepository;

     UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
         return userRepository.findById(id).orElseThrow(() ->new NoSuchElementException("No id with "+id));
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        userRepository.save(user);
    }



    @PatchMapping("/{id}/todos")
    public User addTodoToUser(@PathVariable Long id, @RequestBody Todo todo) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        List<Todo> todos = user.getTodos();
        Long todosNextId = todos.stream()
                .mapToLong(Todo::getUserTodoId)
                .max()
                .orElse(0L)+1;

        todo.setUserTodoId(todosNextId);
        todo.setUser(user);
        todos.add(todo);

        userRepository.save(user);

        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
         userRepository.deleteById(id);
    }


}
