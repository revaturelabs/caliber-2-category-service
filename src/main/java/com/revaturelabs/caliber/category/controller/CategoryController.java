package com.revaturelabs.caliber.category.controller;

import com.revaturelabs.caliber.category.domain.entity.Category;
import com.revaturelabs.caliber.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        log.debug("received request to save category", kv("category", category));
        return categoryService.create(category);
    }
}
