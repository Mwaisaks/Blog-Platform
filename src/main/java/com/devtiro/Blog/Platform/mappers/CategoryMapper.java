package com.devtiro.Blog.Platform.mappers;

import com.devtiro.Blog.Platform.domain.PostStatus;
import com.devtiro.Blog.Platform.domain.dtos.CategoryDTO;
import com.devtiro.Blog.Platform.domain.dtos.CreateCategoryRequest;
import com.devtiro.Blog.Platform.domain.entity.Category;
import com.devtiro.Blog.Platform.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDTO toDTO(Category category); //1:22

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default Long calculatePostCount(List<Post> posts){

        if (null == posts) {
            return Long.valueOf(0);
        }
         return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
