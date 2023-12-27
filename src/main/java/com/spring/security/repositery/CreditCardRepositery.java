package com.spring.security.repositery;

import com.spring.security.user.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepositery extends JpaRepository<CreditCard , Long> {
}
