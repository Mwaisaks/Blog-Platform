package com.devtiro.Blog.Platform.repository;

import com.devtiro.Blog.Platform.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Tag, UUID> {
}
