package com.kolya.easy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostTest {

    private Post post;

    @BeforeEach
    public void setUp() {
        post = new Post();
    }

    @Test
    public void testPostId() {
        Long idValue = 1L;
        post.setId(idValue);
        assertEquals(idValue, post.getId());
    }

    @Test
    public void testPostTitle() {
        String testTitle = "Test Title";
        post.setTitle(testTitle);
        assertEquals(testTitle, post.getTitle());
    }

    @Test
    public void testPostBody() {
        String testBody = "Test Body";
        post.setBody(testBody);
        assertEquals(testBody, post.getBody());
    }

    @Test
    public void testPostUser() {
        User author = new User();
        author.setId(1L);
        post.setAuthor(author);
        assertEquals(author, post.getAuthor());
    }

    @Test
    public void testPostLikes() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        List<User> likes = new ArrayList<User>();
        likes.add(user1);
        likes.add(user2);
        post.setLikes(likes);
        assertEquals(likes, post.getLikes());
    }

    @Test
    public void givenLombok_whenEqualsAndHashCode_thenCorrect() {
        Object post = new Post();
        assertTrue(post.equals(post));  // dummy test of equals
        assertEquals(post.hashCode(), post.hashCode());  // dummy test of hashCode
    }

}