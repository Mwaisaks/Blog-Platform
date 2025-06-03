package com.devtiro.Blog.Platform.service.impl;

import com.devtiro.Blog.Platform.domain.entity.Category;
import com.devtiro.Blog.Platform.repository.CategoryRepository;
import com.devtiro.Blog.Platform.service.CategoryService;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public Category createCategory(Category category) {

        String categoryName = category.getName();
        if (categoryRepository.existsByNameIgnoreCase(categoryName))
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);

        return categoryRepository.save(category);
    }
}
