package com.revature.caliber.controllers;

import com.netflix.ribbon.proxy.annotation.Http;
import com.revature.caliber.beans.Category;
import com.revature.caliber.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @author Kirill Zhdakh
 * <p>
 * Controller for CRUD methods on Category
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

  private static Logger log = Logger.getLogger(CategoryController.class);

  @Autowired
  CategoryService cs;

  /**
   * Return list of categories based on quey, return all by default
   *
   * @param active if true will send all active categories only
   * @param inactive if true will send all inactive categories only
   * @return  204 if empty list for query
   *          200 with array in body as result of query
   */
  @GetMapping("")
  public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) boolean active, @RequestParam(required = false) boolean inactive) {
    log.debug("Fetching category");
    List<Category> cList;

    if (active) {
      log.debug("Getting all active categories from database");
      cList = cs.getAllActiveCategories();
    } else if(inactive) {
      log.debug("Getting all inactive categories");
      cList = cs.getAllInactiveCategories();
    } else {
      log.debug("Getting all categories");
      cList = cs.getAllCategories();
    }

    if (cList.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return new ResponseEntity<>(cList, HttpStatus.OK);
  }

  /**
   * Returns a string version of a single Category object from the database
   *
   * @param id - the id of the Category object to be returned
   * @return - Returns a string of the category object with the id provided
   */
  @GetMapping(value = "{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Integer id) {
    log.debug("Getting category objects with id: " + id);
    Category c = cs.getCategory(id);
    if (c != null)
      return new ResponseEntity<>(c, HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * Waits for http request and calls the CategoryService method createCategory()
   * to save the category to the database
   *
   * @param c
   * @return http response: CREATED
   */
  @PostMapping(value = "")
  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
  public ResponseEntity<Category> createCategory(@Valid @RequestBody Category c) {
    log.debug("Saving new category: " + c);
    Category category;
    if (c == null) {
      log.debug("No category in body");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    try {
      category = cs.createCategory(c);
    } catch (DataIntegrityViolationException e) {
      log.debug("Conflict when saving category: ", e);
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    log.info("Created Category: " + category);
    return new ResponseEntity<>(category, HttpStatus.CREATED);
  }

  /**
   * Updates a Category entry in the database creates if doesn't exist
   *
   * @param - c - the Category entry to be updated
   * @return - returns an http status code: NO_CONTENT
   */
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
  public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category c, @PathVariable int id) {
    c.setCategoryId(id);
    log.debug("Updating category: " + id);
    Category category;
    if (c == null) {
      log.debug("No valid category to create: BAD REQUEST");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Track if category was created or updated
    HttpStatus status;
    try {
      category = cs.updateCategory(c);
      // If null then no category existed
      if (category == null) {
        log.info("Created Category: " + c);
        category = cs.createCategory(c);
        status = HttpStatus.CREATED;
      } else {
        status = HttpStatus.OK;
      }
    } catch (DataIntegrityViolationException e) {
      log.debug("Error creating category on put: ", e);
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    log.debug("Update category " + id +" to: " + c);
    return new ResponseEntity<>(category, status);
  }

  /**
   * Deletes a Category entry from the database, soft delete, sets Category active
   * field to false
   *
   * @param - c - the Category entry to be deleted
   * @return returns an http status code: NO_CONTENT
   */
  @DeleteMapping(value = "/{id}")
  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
  public ResponseEntity<Boolean> deleteCategory(@PathVariable int id) {
    try {
      cs.deleteCategoryById(id);
    } catch (NoSuchElementException e) {
      log.debug("No category to delete " + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    log.debug("Deleted category: " + id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
