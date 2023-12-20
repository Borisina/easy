package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.PostRepository;
import com.kolya.easy.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userRepository, postRepository);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userController.create(user);
        assertEquals(createdUser, user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User returnedUser = userController.getUserById(1L);
        assertEquals(returnedUser.getId(), user.getId());
    }

    @Test
    public void testGetAllPostsByUser() {
        User user = new User();
        user.setId(1L);
        Post post = new Post();
        post.setId(1L);
        post.setAuthor(user);
        List<Post> postList = new ArrayList<>();
        postList.add(post);
        user.setPosts(postList);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        List<Post> returnedPostList = userController.getAllPostsByUser(1L);
        assertEquals(returnedPostList, postList);
    }

    @Test
    public void testCreatePost() {
        User user = new User();
        user.setId(1L);
        Post post = new Post();
        post.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.save(post)).thenReturn(post);
        Post returnedPost = userController.createPost(1L, post);
        assertEquals(returnedPost, post);
    }

    @Test
    public void testFollowUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFollowing(new ArrayList<>());
        User user2 = new User();
        user2.setId(2L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.save(user1)).thenReturn(user1);
        User returnedUser = userController.followUser(1L, 2L);
        assertEquals(returnedUser, user1);
    }

    @Test
    public void testLikePost() {
        User user = new User();
        user.setId(1L);
        user.setLikedPosts(new ArrayList<>());
        Post post = new Post();
        post.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(userRepository.save(user)).thenReturn(user);
        User returnedUser = userController.likePost(1L, 1L);
        assertEquals(returnedUser, user);
    }
}