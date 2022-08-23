package com.codecool.windmill.Controller;

import com.codecool.windmill.DTO.UserDTO;
import com.codecool.windmill.Model.User;
import com.codecool.windmill.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get/{id}")
    public UserDTO getUser(@PathVariable(name = "id") Long id){
        User user = userService.getById(id);
        return modelMapper.map(user, UserDTO.class);
    }
}
