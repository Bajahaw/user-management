package com.example.management;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private final AppRepository appRepository;
    private final AppService appService;

    public AppController(AppRepository appRepository, AppService appService){
        this.appRepository = appRepository;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user){

        System.out.println("user: "+user.username() + " --> pass:" + user.password());

        AppUser appUser = appRepository.getUserByName(user.username());
        if (appUser == null || !appUser.password().equals(user.password()))
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login/" + user.username()).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("Bye you're out");
    }

    @PutMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AppUser user){
        appRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User created");
    }
}
