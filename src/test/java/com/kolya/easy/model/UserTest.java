package com.kolya.easy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testUserId() {
        Long idValue = 1L;
        user.setId(idValue);
        assertEquals(idValue, user.getId());
    }

    @Test
    public void testUserName() {
        String testName = "Test Name";
        user.setUsername(testName);
        assertEquals(testName, user.getUsername());
    }

    @Test
    public void testUserPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        user.setPosts(posts);
        assertEquals(posts, user.getPosts());
    }

    @Test
    public void testUserFollowing() {
        List<User> following = new ArrayList<>();
        following.add(new User());
        user.setFollowing(following);
        assertEquals(following, user.getFollowing());
    }

    @Test
    public void testUserFollowers() {
        List<User> followers = new ArrayList<>();
        followers.add(new User());
        user.setFollowers(followers);
        assertEquals(followers, user.getFollowers());
    }

    @Test
    public void testUserLikedPosts() {
        List<Post> likedPosts = new ArrayList<>();
        likedPosts.add(new Post());
        user.setLikedPosts(likedPosts);
        assertEquals(likedPosts, user.getLikedPosts());
    }

    @Test
    public void givenLombok_whenEqualsAndHashCode_thenCorrect() {
        Object user = new User();
        assertTrue(user.equals(user));  // dummy test of equals
        assertEquals(user.hashCode(), user.hashCode());  // dummy test of hashCode
    }

    @Test
    public void testEquals() {
        User user1 = new User();
        user1.setUsername("Test");

        assertTrue(user1.equals(user1));  // equals itself

        User user2 = null;
        assertFalse(user1.equals(user2)); // does not equal null

        assertFalse(user1.equals("some string")); // does not equal another type

        User user3 = new User();
        user3.setUsername("Test");
        assertTrue(user1.equals(user3));  // equals another user with same username

        User user4 = new User();
        user4.setUsername("Test1");
        assertFalse(user1.equals(user4));  // does not equal another user with different username
    }
}