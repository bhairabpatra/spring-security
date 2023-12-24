package com.spring.security.user.entity;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String message;
    private Integer status;
    private String token;
    private String refreshToken;
}
