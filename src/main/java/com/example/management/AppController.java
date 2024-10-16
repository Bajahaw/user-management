package com.example.management;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private final AppService appService;
    Logger log = org.slf4j.LoggerFactory.getLogger(AppController.class);

    public AppController(AppService appService){
        this.appService = appService;
    }

    @ExceptionHandler(AuthenticationException.class)
    public void handleException(Exception e){
        log.error(e.getMessage());
    }

    @GetMapping
    public String index() {
        return "forward:index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/views/auth/login.html";
    }

    @GetMapping("/home")
    public String user() {
        return "forward:/views/homepage.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "forward:/views/auth/signup.html";
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("Bye you're out");
    }
}
