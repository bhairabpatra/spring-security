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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepositery.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepositery.findAll();
    }


    @Override
    public boolean isExistsByEmail(String email) {
        User isEmailExist = userRepositery.findByEmail(email);
        if (isEmailExist != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        User isEmailExist = userRepositery.findByEmail(email);
        if (isEmailExist != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isPasswordMatch(String email, String password) {
        User existUser = userRepositery.findByEmail(email);
        boolean isPasswordMatch = passwordEncoder.matches(password, existUser.getPassword());
        if (isPasswordMatch) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepositery.findById(id);
        if (user.isPresent()) {
            userRepositery.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepositery.findById(id);
        return user.orElse(null);
    }
}

