package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.repo.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostControllerTest {

    @Mock
    private PostRepository postRepository;

    private PostController postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        postController = new PostController(postRepository);
    }

    @Test
    public void testGetPost() {
        Post post = new Post();
        post.setId(1L);
        post.setBody("Test Body");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        Post returnedPost = postController.getPost(1L);

        assertNotNull(returnedPost);
        assertEquals(post.getId(), returnedPost.getId());
        assertEquals(post.getBody(), returnedPost.getBody());
    }

    @Test
    public void testGetPostNotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            postController.getPost(1L);
        });
    }
}