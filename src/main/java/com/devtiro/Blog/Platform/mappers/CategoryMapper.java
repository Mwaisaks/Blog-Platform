package com.devtiro.Blog.Platform.mappers;

import com.devtiro.Blog.Platform.domain.dtos.CategoryDTO;
import com.devtiro.Blog.Platform.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);
}
