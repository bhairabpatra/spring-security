package com.spring.security.controller;

import com.spring.security.exception.NoUserFound;
import com.spring.security.service.UserServices;
import com.spring.security.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServices userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user) throws NoUserFound {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.existsByEmail(user.getEmail())) {
            throw new NoUserFound(user.getEmail() + " Email already found");
        } else {
            User newUser = userService.createUser(user);
            throw new NoUserFound(" user registered successfully ");
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) throws NoUserFound {
        if (userService.isExistsByEmail(user.getEmail())) {
            throw new NoUserFound(user.getEmail() + " Email Not found");
        }
        if (userService.isPasswordMatch(user.getEmail(), user.getPassword())) {
            throw new NoUserFound("Password Not Match");
        } else {
            throw new NoUserFound("You are successfully logged in");
        }
    }

    @GetMapping("allUser")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/home")
    public String home() {
        String details = "Hai iam Home page";
        return details;
    }

    @GetMapping("/profile")
    public String profile() {
        String details = "Hai iam profile page";
        return details;
    }
}
