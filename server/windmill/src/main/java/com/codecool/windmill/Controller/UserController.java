package com.codecool.windmill.Controller;

import com.codecool.windmill.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
}
