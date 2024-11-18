package com.example.management.app;

import com.example.management.user.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
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

    @GetMapping("/403")
    public String forbidden() {
        return "forward:/views/auth/403.html";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "forward:/views/dashboard.html";
    }

    @GetMapping("/dashboard/users")
    @ResponseBody
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.status(200).body(appService.getAllUsers());
    }

    @DeleteMapping("/delete/{user}")
    public ResponseEntity<String> delete(@PathVariable String user) {
        return appService.deleteUser(user) ?
                ResponseEntity.status(200).body("Deleted") :
                ResponseEntity.status(404).body("Not Found");
    }
}
