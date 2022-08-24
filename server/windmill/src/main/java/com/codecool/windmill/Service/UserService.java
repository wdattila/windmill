package com.codecool.windmill.Service;

import com.codecool.windmill.Model.User;
import com.codecool.windmill.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void addUser(User user){
        userRepository.saveAndFlush(user);
    }

    public boolean userExist(User user){
        if(user != null) {
            return userRepository.existsByName(user.getName())
                    || userRepository.existsByEmail(user.getEmail());
        }
        return false;
    }

    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        addUser(user);
    }
}
