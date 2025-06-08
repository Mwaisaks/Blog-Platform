package com.devtiro.Blog.Platform.repository;

import com.devtiro.Blog.Platform.domain.PostStatus;
import com.devtiro.Blog.Platform.domain.entity.Category;
import com.devtiro.Blog.Platform.domain.entity.Post;
import com.devtiro.Blog.Platform.domain.entity.Tag;
import com.devtiro.Blog.Platform.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status,
                                                           Category category,
                                                           Tag tags
    );

    List<Post> findAllByStatusAndCategory(PostStatus status,
                                          Category category);

    List<Post> findAllByStatusAndTagsContaining(PostStatus status,
                                                Tag tags);

    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
