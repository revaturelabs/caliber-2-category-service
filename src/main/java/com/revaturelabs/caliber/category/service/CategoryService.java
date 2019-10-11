package com.revaturelabs.caliber.category.service;

import com.revaturelabs.caliber.category.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(int i);

    Category create(Category category);

    Category update(Category category);

    Category createOrUpdate(Category category);

    void delete(int id);
}
