package com.kolya.easy.repo;

import com.kolya.easy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}