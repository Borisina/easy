package com.kolya.easy.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="usr")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
