package com.spring.security.service;

import com.spring.security.repositery.UserRepositery;
import com.spring.security.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepositery userRepositery;
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


}
