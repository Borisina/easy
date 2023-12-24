package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.PostRepository;
import com.kolya.easy.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PostControllerTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    Authentication auth;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPost() {
        Post post = new Post();
        post.setId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        Post returnedPost = postController.getPost(1L);

        assertNotNull(returnedPost, "Expected non null Post");
        assertEquals(post, returnedPost, "Expected returned Post to be the same as the mock Post");
    }

    @Test
    public void testGetPostNotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> postController.getPost(1L));
    }

    @Test
    public void testCreatePost() {
        User user = new User();
        Post post = new Post();

        when(postRepository.save(post)).thenReturn(post);

        Post returnedPost = postController.createPost(user, post);

        assertEquals(post, returnedPost);
    }

    @Test
    public void testLikePost() {
        User user = new User();
        user.setLikedPosts(new ArrayList<>());
        Post post = new Post();

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(userRepository.save(user)).thenReturn(user);

        User returnedUser = postController.likePost(user, 1L);

        assertEquals(user, returnedUser);
    }
}