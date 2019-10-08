package com.revaturelabs.caliber.category.service;

import com.revaturelabs.caliber.category.domain.entity.Category;
import com.revaturelabs.caliber.category.domain.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
       return categoryRepository.findAll();
    }

    public Category create(Category category) {
        log.debug("saving category", kv("category", category));
        log.info("saving category");
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(category.getId());

        Category updatedCategory = null;
        if (existingCategory.isPresent()) {
            updatedCategory = categoryRepository.save(category);
        } else {

        }

        return updatedCategory;
    }
}
