package com.spring.security.service;

import com.spring.security.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    public User createUser(User user);

    public List<User> getAllUser();

    public User getUser(Long id);

    boolean isExistsByEmail(String email);

    boolean existsByEmail(String email);

    boolean isPasswordMatch(String email, String password);

    boolean deleteUser(Long id);
}

