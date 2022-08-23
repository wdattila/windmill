package com.codecool.windmill.Service;

import com.codecool.windmill.Model.User;
import com.codecool.windmill.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
