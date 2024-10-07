package com.example.management;

import com.example.management.auth.AuthRequest;
import com.example.management.auth.AuthResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/login/{userId}")
    public String user(@PathVariable String userId) {
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

    @PostMapping("/perform_login")
    public AuthResponse performLogin(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        String token = appService.performLogin(authRequest);
        Cookie jwtCookie = new Cookie("JWT", token);
        jwtCookie.setHttpOnly(true); // Prevent access from JavaScript
        jwtCookie.setPath("/"); // Make cookie available to all paths
        response.addCookie(jwtCookie);
        return new AuthResponse(token);
    }
}
