package com.spring.security.service;

import com.spring.security.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    public User createUser(User user);

    public List<User> getAllUser();

    public User getUser(Long id);

    public Optional<User> findByUserName(String email);
}
