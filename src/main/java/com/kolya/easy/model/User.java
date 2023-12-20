package com.kolya.easy.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="usr")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany
    private List<User> following;

    @JsonIdentityReference(alwaysAsId = true)
    // This will keep track of the users that this user is followed by
    @ManyToMany(mappedBy = "following")
    private List<User> followers;

    @JsonIdentityReference(alwaysAsId = true)
    // This will keep track of liked posts
    @ManyToMany
    private List<Post> likedPosts;
}
