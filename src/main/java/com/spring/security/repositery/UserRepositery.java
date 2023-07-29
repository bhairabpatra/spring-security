package com.spring.security.repositery;

import com.spring.security.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositery extends JpaRepository<User , Long> {
      Optional<User> findByUserName(String email);
}
