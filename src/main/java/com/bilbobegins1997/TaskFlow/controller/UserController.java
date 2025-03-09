package com.bilbobegins1997.TaskFlow.controller;
import com.bilbobegins1997.TaskFlow.entity.TodoUser;
import com.bilbobegins1997.TaskFlow.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/user")
public class UserController {

   final UserRepository userRepository;

     UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public TodoUser getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        String username =  authentication.getName();

        return userRepository.findByLogin(username).orElseThrow(() -> new NoSuchElementException("No User by this id " + username));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
         userRepository.deleteById(id);
    }


}
