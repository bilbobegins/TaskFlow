package com.bilbobegins1997.TaskFlow.service;

import com.bilbobegins1997.TaskFlow.dto.RegisterRequest;
import com.bilbobegins1997.TaskFlow.entity.TodoUser;
import com.bilbobegins1997.TaskFlow.exception.UserAlreadyExistsException;
import com.bilbobegins1997.TaskFlow.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TodoUserService implements UserDetailsService  {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public TodoUserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public String registerUser(RegisterRequest request) throws UsernameNotFoundException {
        if (userRepository.findByLogin(request.username()).isPresent()) {

            throw new UserAlreadyExistsException("User already exists!");
        }

        TodoUser newUser = new TodoUser();
        newUser.setLogin(request.username());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(newUser);

        return "User registered successfully!";


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TodoUser user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new User(
                 user.getLogin(),
                 user.getPassword(),
                Collections.emptyList());
    }


}
