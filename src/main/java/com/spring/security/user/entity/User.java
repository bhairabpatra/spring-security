package com.spring.security.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_tbl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//    )
    private Set<Role> roles = new HashSet<>();
}
