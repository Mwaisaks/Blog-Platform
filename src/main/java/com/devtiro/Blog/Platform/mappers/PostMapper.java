package com.devtiro.Blog.Platform.mappers;

import com.devtiro.Blog.Platform.domain.CreatePostRequest;
import com.devtiro.Blog.Platform.domain.dtos.CreatePostRequestDto;
import com.devtiro.Blog.Platform.domain.dtos.PostDto;
import com.devtiro.Blog.Platform.domain.dtos.UpdatePostRequest;
import com.devtiro.Blog.Platform.domain.dtos.UpdatePostRequestDto;
import com.devtiro.Blog.Platform.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}
