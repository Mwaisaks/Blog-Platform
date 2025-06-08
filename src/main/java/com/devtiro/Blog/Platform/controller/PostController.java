package com.devtiro.Blog.Platform.controller;

import com.devtiro.Blog.Platform.domain.CreatePostRequest;
import com.devtiro.Blog.Platform.domain.dtos.CreatePostRequestDto;
import com.devtiro.Blog.Platform.domain.dtos.PostDto;
import com.devtiro.Blog.Platform.domain.entity.User;
import com.devtiro.Blog.Platform.service.UserService;
import lombok.RequiredArgsConstructor;
import com.devtiro.Blog.Platform.domain.entity.Post;
import com.devtiro.Blog.Platform.mappers.PostMapper;
import com.devtiro.Blog.Platform.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false)UUID categoryId,
            @RequestParam(required = false) UUID tagId){ //These two allow us to filter the posts based on the category or tag or both

        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);

    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId){
        User loggedInUser = userService.getUserById(userId);

        List<Post> draftPosts = postService.getAllDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();

        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId
            ){

        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post createdPost = postService.createPost(loggedInUser, createPostRequest);
        PostDto createdPostDto = postMapper.toDto(createdPost);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
    )
}
