package com.codecool.windmill.Security;

import com.codecool.windmill.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.codecool.windmill.Model.User webUser = userRepository.findByName(username);
        if(webUser == null){
            throw new UsernameNotFoundException(username);
        }
        UserDetails securityUser = User.builder()
                .username(webUser.getName())
                .password(webUser.getPassword())
                .authorities(new ArrayList<>())
                .build();
        return securityUser;
    }
}
