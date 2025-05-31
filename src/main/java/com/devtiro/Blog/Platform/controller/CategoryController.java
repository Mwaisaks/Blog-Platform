package com.devtiro.Blog.Platform.controller;

import com.devtiro.Blog.Platform.domain.dtos.CategoryDTO;
import com.devtiro.Blog.Platform.domain.entity.Category;
import com.devtiro.Blog.Platform.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor //Creates a constructor for us with any instance variables that we declare as final
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories(){
        List<Category> categories = categoryService.listCategories();
        return categories;
    }
}
