package com.devtiro.Blog.Platform.service.impl;

import com.devtiro.Blog.Platform.domain.entity.Category;
import com.devtiro.Blog.Platform.repository.CategoryRepository;
import com.devtiro.Blog.Platform.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }
}
