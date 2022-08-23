package com.codecool.windmill.Controller;

import com.codecool.windmill.DTO.UserDTO;
import com.codecool.windmill.Model.User;
import com.codecool.windmill.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
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

    @PostMapping("/post")
    public ResponseEntity<?> postUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }
}
