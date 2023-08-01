package com.spring.security.controller;
import com.spring.security.service.UserServices;
import com.spring.security.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServices userService;

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
//        Optional<User>  isExist = userService.findByUserName(user.getUsername());
//        if(isExist.isPresent()){
//            System.out.println("User Already Exist");
//            return null;
//        }
        User newUser = userService.createUser(user);
        return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("allUser")
    public  ResponseEntity<List<User>> getUsers(){
        List<User>  users = userService.getAllUser();
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }


}
