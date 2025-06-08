package com.devtiro.Blog.Platform.domain.dtos;

import com.devtiro.Blog.Platform.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequest {

    private UUID id;

    private String title;

    private String content;

    private UUID categoryId;

    private Set<UUID> tagIds = new HashSet<>();

    private PostStatus status;
}
