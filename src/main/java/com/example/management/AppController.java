package com.example.management;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private final UserRepository userRepository;
    private final AppService appService;

    public AppController(UserRepository userRepository, AppService appService){
        this.userRepository = userRepository;
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

        System.out.println("user: "+user.getUsername() + " --> pass:" + user.getPassword());

        AppUser appUser = userRepository.getUserByUsername(user.getUsername());
        if (appUser == null || !appUser.getPassword().equals(user.getPassword()))
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login/" + user.getUsername()).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok("Bye you're out");
    }

    @PutMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AppUser user){
        userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User created");
    }
}
