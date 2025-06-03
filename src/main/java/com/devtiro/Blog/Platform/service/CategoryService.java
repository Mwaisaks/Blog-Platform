package com.devtiro.Blog.Platform.service;

import com.devtiro.Blog.Platform.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();

    Category createCategory(Category category);
}
