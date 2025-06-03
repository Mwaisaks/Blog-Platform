package com.devtiro.Blog.Platform.repository;

import com.devtiro.Blog.Platform.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts") //hql - Hibernate Query Language
    List<Category> findAllWithPostCount();

    boolean existsByNameIgnoreCase(String categoryName);

    //using hql side step the n+1 problem.
    // Using findAll() returns a list of categories, hibernate examines each category
    // and then query to get a the list of posts under that particular category - to many queries
}
