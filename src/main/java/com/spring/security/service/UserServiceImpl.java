package com.spring.security.service;

import com.spring.security.repositery.UserRepositery;
import com.spring.security.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepositery userRepositery;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        return userRepositery.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepositery.findAll();
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User checkLogin(User user) {
        User isUserExist = userRepositery.findByUsername(user.getUsername());
        if (isUserExist != null) {
            String password = user.getPassword();
            String encodePassword = isUserExist.getPassword();
            Boolean isPasswordMatch = passwordEncoder.matches(password, encodePassword);
            if (isPasswordMatch) {
                User isUser = userRepositery.findByUsernameAndPassword(user.getUsername(), user.getPassword());
                System.out.println(isUser);
                if (isUser != null) {
                    System.out.println("Login SuccessFully Done !");
                } else {
                    System.out.println("Login Failed!");
                }
            } else {
                System.out.println("Password Not Match");
            }

        } else {
            System.out.println(user.getUsername() + "Not exist");
        }


        return null;
    }


}

//https://springjavatutorial.medium.com/login-and-registration-rest-api-with-spring-security-d7ee48820bd0