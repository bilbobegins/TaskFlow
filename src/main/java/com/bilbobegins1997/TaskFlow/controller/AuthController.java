package com.bilbobegins1997.TaskFlow.controller;
import com.bilbobegins1997.TaskFlow.dto.JwtResponse;
import com.bilbobegins1997.TaskFlow.dto.LoginRequest;
import com.bilbobegins1997.TaskFlow.dto.RegisterRequest;
import com.bilbobegins1997.TaskFlow.service.TokenService;
import com.bilbobegins1997.TaskFlow.service.TodoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TodoUserService todoUserService;
    private final TokenService tokenService;
    private final AuthenticationManager manager;


    public AuthController(TodoUserService todoUserService,
                          TokenService tokenService,
                          AuthenticationManager manager) {
        this.todoUserService = todoUserService;
        this.tokenService = tokenService;
        this.manager = manager;
    }

//    @PostMapping("/token")
//    public String token(Authentication authentication){
//        LOG.debug("Token requested for user: '(}'", authentication.getName());
//        String token = tokenService.generateToken(authentication);
//        LOG. debug ("Token granged (}", token);
//        return token;
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        String response = todoUserService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request){
   Authentication authentication = manager
           .authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));

        String jwt = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}
