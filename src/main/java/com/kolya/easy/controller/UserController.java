package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.PostRepository;
import com.kolya.easy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{userId}/posts")
    public List<Post> getAllPostsByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getPosts();
    }

    @PostMapping("/{userId}/posts")
    public Post createPost(@PathVariable Long userId, @RequestBody Post post) {
        User user = userRepository.findById(userId).orElseThrow();
        post.setAuthor(user);
        return postRepository.save(post);
    }

    // Endpoint to follow another user
    @PostMapping("/{id}/follow/{followId}")
    public User followUser(@PathVariable Long id, @PathVariable Long followId) {
        User user = userRepository.findById(id).orElseThrow();
        User toFollow = userRepository.findById(followId).orElseThrow();
        user.getFollowing().add(toFollow);
        return userRepository.save(user);
    }

    // Endpoint to like a post
    @PostMapping("/{id}/like/{postId}")
    public User likePost(@PathVariable Long id, @PathVariable Long postId) {
        User user = userRepository.findById(id).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        user.getLikedPosts().add(post);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }
}