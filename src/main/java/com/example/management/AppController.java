package com.example.management;

import com.example.management.auth.AuthRequest;
import com.example.management.auth.AuthResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private final AppService appService;

    public AppController(AppService appService){
        this.appService = appService;
    }

    @GetMapping("/login")
    public String index() {
        return "forward:index.html";
    }

    @GetMapping("/home")
    public String user() {
        return "forward:/views/homepage.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "forward:/views/signup.html";
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("Bye you're out");
    }
}
