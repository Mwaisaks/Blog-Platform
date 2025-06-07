package com.devtiro.Blog.Platform.controller;

import com.devtiro.Blog.Platform.domain.dtos.TagRequest;
import com.devtiro.Blog.Platform.domain.dtos.TagResponse;
import com.devtiro.Blog.Platform.domain.entity.Tag;
import com.devtiro.Blog.Platform.mappers.TagMapper;
import com.devtiro.Blog.Platform.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping()
    public ResponseEntity<List<TagResponse>> getAllTags(){

        List<Tag> tags = tagService.getTags();

        List<TagResponse> tagResponses = tags.stream()
                .map(tagMapper::toTagResponse)
                .toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTag(@Valid @RequestBody TagRequest tagRequest){
        List<Tag> savedTags = tagService.createTag(tagRequest.getName());
        List<TagResponse> createdTagResponses = savedTags.stream()
                .map(tagMapper::toTagResponse)
                .toList();

        return new ResponseEntity<>(
                createdTagResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
