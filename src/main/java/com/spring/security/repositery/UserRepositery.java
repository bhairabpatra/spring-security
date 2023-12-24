package com.spring.security.repositery;

import com.spring.security.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositery extends JpaRepository<User , Long> {
    public User findByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
    public User findByEmail(String email);



    public User findByPassword(String password);
}
