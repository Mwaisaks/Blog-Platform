package com.devtiro.Blog.Platform.service.impl;

import com.devtiro.Blog.Platform.domain.entity.Category;
import com.devtiro.Blog.Platform.repository.CategoryRepository;
import com.devtiro.Blog.Platform.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    //You have to ensure that category with posts won't be deleted
    @Override
    public void deleteCategory(UUID id) {

        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            if (!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("Category has posts associated with it");
            }
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    }
}
