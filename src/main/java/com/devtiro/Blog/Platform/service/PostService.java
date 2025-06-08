package com.devtiro.Blog.Platform.service;

import com.devtiro.Blog.Platform.domain.CreatePostRequest;
import com.devtiro.Blog.Platform.domain.dtos.UpdatePostRequest;
import com.devtiro.Blog.Platform.domain.entity.Post;
import com.devtiro.Blog.Platform.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getAllDraftPosts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);

    Post updatepost(UUID id, UpdatePostRequest updatePostRequest);

    Post getPostById(UUID id);
}
