package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.PostRepository;
import com.kolya.easy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    @PostMapping()
    public Post createPost(@AuthenticationPrincipal User user, @RequestBody Post post) {
        post.setAuthor(user);
        return postRepository.save(post);
    }

    @PostMapping("/like/{postId}")
    public User likePost(@AuthenticationPrincipal User user, @PathVariable Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        user.getLikedPosts().add(post);
        return userRepository.save(user);
    }
}