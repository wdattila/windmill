package com.codecool.windmill.Controller;

import com.codecool.windmill.DTO.UserDTO;
import com.codecool.windmill.Model.User;
import com.codecool.windmill.Service.UserService;
import com.codecool.windmill.Util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/get/{id}")
    public UserDTO getUser(@PathVariable(name = "id") Long id){
        User user = userService.getById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    @PostMapping("/post")
    public ResponseEntity<?> postUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        auth = authenticate(auth);

        String token = jwtTokenUtil.generateToken(user.getName());
        return ResponseEntity.ok(token);
    }

    private Authentication authenticate(Authentication authentication) throws Exception {
        try {
            return authenticationManager.authenticate(authentication);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
