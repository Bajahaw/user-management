package com.example.management.app;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private final AppService appService;

    public AppController(AppService appService){
        this.appService = appService;
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

    @GetMapping("/dashboard")
    public String dashboard(){
        return "forward:/views/dashboard.html";
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("Bye you're out");
    }
}
