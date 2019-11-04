package com.revaturelabs.caliber.category;

import com.revaturelabs.caliber.category.exception.CategoryConflictException;
import com.revaturelabs.caliber.category.exception.CategoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        //log.info(name);
        return categoryService.getAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        try {
            return categoryService.getById(id);
        } catch (CategoryNotFoundException e) {
            log.debug("Category {} does not exit when getting by id", kv("id", id), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found",e);
        }
    }

    @PutMapping("/{id}")
    public Category createOrUpdate(@RequestBody Category category, @PathVariable int id) {
        category.setId(id);
        try {
            return categoryService.createOrUpdate(category);
        } catch (CategoryConflictException e) {
            log.debug("Category name conflict when create/updating", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category name already exists", e);
        }
    }

    @PatchMapping("/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable int id) {
        category.setId(id);
        try {
            return categoryService.update(category);
        } catch (CategoryConflictException e) {
            log.debug("Category name conflict when updating: {}", kv("exception", e));
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category name already exists", e);
        } catch (CategoryNotFoundException e) {
            log.debug("Could not find Category to update: {} {}", kv("id", id), kv("exception", e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        try {
            categoryService.delete(id);
        } catch (CategoryNotFoundException e) {
            log.debug("Category {} does not exist when deleting", kv("id", id), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", e);
        }
    }
}
