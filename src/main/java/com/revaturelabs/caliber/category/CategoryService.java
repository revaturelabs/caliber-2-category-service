package com.revaturelabs.caliber.category;

import com.revaturelabs.caliber.category.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(int i);

    Category create(Category category);

    Category update(Category category);

    Category createOrUpdate(Category category);

    void delete(int id);
}
