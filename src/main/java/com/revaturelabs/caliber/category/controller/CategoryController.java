package com.revaturelabs.caliber.category.controller;

import com.revaturelabs.caliber.category.domain.entity.Category;
import com.revaturelabs.caliber.category.exception.CategoryConflictException;
import com.revaturelabs.caliber.category.exception.CategoryNotFoundException;
import com.revaturelabs.caliber.category.service.CategoryService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PatchMapping("/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable int id) {
        category.setId(id);
        try {
            return categoryService.update(category);
        } catch (CategoryConflictException e) {
            log.debug("Category name conflict: {}", kv("exception", e));
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category name already exists", e);
        } catch (CategoryNotFoundException e) {
            log.debug("Could not find Category to update: {} {}", kv("id", id), kv("exception", e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", e);
        }
    }
}
