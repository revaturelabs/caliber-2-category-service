package com.revaturelabs.caliber.category.service;

import com.revaturelabs.caliber.category.domain.entity.Category;
import com.revaturelabs.caliber.category.domain.repository.CategoryRepository;
import com.revaturelabs.caliber.category.exception.CategoryConflictException;
import com.revaturelabs.caliber.category.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
       return categoryRepository.findAll();
    }

    public Category getById(int id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(!category.isPresent()) {
            throw new CategoryNotFoundException("Category not found");
        }

        return category.get();
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(category.getId());

        Category updatedCategory = null;
        if (existingCategory.isPresent()) {
            try {
                updatedCategory = categoryRepository.save(category);
            } catch (DataIntegrityViolationException e) {
                throw new CategoryConflictException("Category name already exists");
            }
        } else {
            throw new CategoryNotFoundException("Unable to find category to update");
        }

        return updatedCategory;
    }

    public Category createOrUpdate(Category category) {
        Category updatedCategory = null;
        try {
            updatedCategory = categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new CategoryConflictException("Category name already exists");
        }

        return updatedCategory;
    }

    public void delete(int id) {
        boolean categoryExists = categoryRepository.existsById(id);

        if (!categoryExists) {
            throw new CategoryNotFoundException("Unable to find Category to delete");
        }

        categoryRepository.deleteById(id);
    }
}
