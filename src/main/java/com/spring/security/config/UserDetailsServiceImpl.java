package com.spring.security.config;

import com.spring.security.repositery.UserRepositery;
import com.spring.security.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepositery userRepositery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Entering in loadUserByUsername Method...");
        User user = userRepositery.findByUsername(username);
        try {
            if(user != null){
                return new CustomUserDetails(user);
            }

        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("could not found user..!!" + e.getMessage());
        }
        return  null;

    }
}
