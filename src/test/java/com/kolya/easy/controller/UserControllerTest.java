package com.kolya.easy.controller;

import com.kolya.easy.model.Post;
import com.kolya.easy.model.User;
import com.kolya.easy.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPostsByUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPosts(List.of(new Post()));

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        List<Post> posts = userController.getAllPostsByUser(1L);

        assertNotNull(posts);
    }

    @Test
    public void testFollowUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setFollowing(new ArrayList<>());
        User follow = new User();
        follow.setUsername("followUser");
        follow.setFollowing(new ArrayList<>());

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(follow));
        when(userRepository.save(user)).thenReturn(user);

        User returnedUser = userController.followUser(user, 1L);

        assertNotNull(returnedUser);
        assertEquals(returnedUser.getFollowing().iterator().next().getUsername(), "followUser");
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        User returnedUser = userController.getUserById(1L);

        assertNotNull(returnedUser);
        assertEquals(returnedUser.getUsername(), "testUser");
    }
}