package com.kolya.easy.controller;

import com.kolya.easy.model.AuthenticationRequest;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AuthenticationRequest data) {
        User userInDb = userRepository.findByUsername(data.getUsername()).orElse(null);
        if (userInDb!=null){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User created!", HttpStatus.CREATED);
    }
}
