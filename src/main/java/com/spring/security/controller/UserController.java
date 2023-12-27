package com.spring.security.controller;

import com.spring.security.config.jwtservice.JwtService;
import com.spring.security.exception.NoUserFound;
import com.spring.security.service.UserServices;
import com.spring.security.user.entity.JwtAuthenticationResponse;
import com.spring.security.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserServices userService;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> createUser(@RequestBody User user) throws NoUserFound {

        if (userService.existsByEmail(user.getEmail())) {
            throw new NoUserFound(user.getEmail() + " Email already exist");
        } else {
            User newUser = userService.createUser(user);
            throw new NoUserFound(" user registered successfully ");
        }
    }

    @PostMapping("signin")
    public ResponseEntity<?> login(@RequestBody User user) throws NoUserFound {

        if (userService.findByUsername(user.getUsername())) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                var jwt = jwtService.generateToken(user.getUsername());
//            var refreshToken = jwtService.generateRefreshToken((new HashMap<>()), user);
                JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
                jwtAuthenticationResponse.setMessage("Login successfully done");
                jwtAuthenticationResponse.setStatus(HttpStatus.OK.value());
                jwtAuthenticationResponse.setToken(jwt);
//            jwtAuthenticationResponse.setRefreshToken(refreshToken);
                return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("invalid user request..!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("User" + user.getUsername() + "Not found", HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("allUser")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-user/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
