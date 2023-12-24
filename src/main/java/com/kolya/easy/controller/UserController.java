package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.PostRepository;
import com.kolya.easy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/posts")
    public List<Post> getAllPostsByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getPosts();
    }

    @PostMapping("/follow/{followId}")
    public User followUser(@AuthenticationPrincipal User user, @PathVariable Long followId) {
        User toFollow = userRepository.findById(followId).orElseThrow();
        user.getFollowing().add(toFollow);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }
}