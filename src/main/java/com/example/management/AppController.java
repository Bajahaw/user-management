package com.example.management;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AppController {

    @GetMapping("/login")
    public String index() {
        return "forward:index.html";
    }

    @GetMapping("/user")
    public String user() {
        return "forward:index.html";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user){
        System.out.println("user: "+user.username() + " --> pass:" + user.password());
        return ResponseEntity.ok("cool all good");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("cool you're out");
    }
}
