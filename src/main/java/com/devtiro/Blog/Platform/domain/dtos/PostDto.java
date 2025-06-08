package com.devtiro.Blog.Platform.domain.dtos;

import com.devtiro.Blog.Platform.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private UUID id;
    private String title;
    private String content; //even when the content will be a lot?
    private  AuthorDto author;
    private CategoryDTO category;
    private Set<TagResponse> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PostStatus status;

}
