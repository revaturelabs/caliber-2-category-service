package com.revature.caliber.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;
import com.revature.caliber.services.CategoryService;


/**
 * 
 * @author Kirill Zhdakh
 * 
 *         Controller for CRUD methods on Category
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
	
	
	private static Logger log = Logger.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService cs;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Category>> getAllActiveCategories(@RequestParam(required = false) boolean active, @RequestHeader Map<String, String> headers) {
		log.debug(headers);
		log.debug("Fetching category");
		List<Category> cList;
		
		if (active) {
			log.debug("Getting all active categories from database");
			cList = cs.getAllActiveCategories();	
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
	 * 
	 * @return - Returns a string of the category object with the id provided
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Integer id)  {
		log.debug("Getting category objects with id: " + id);
		Category c = cs.getCategory(id);
		if (c != null)
			return new ResponseEntity<>(c, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Returns all Categories for the database by owner
	 * 
	 * @return cList - a List object with all the Category entities from the
	 *         database
	 *         
	 * {owner} value MUST BE WRITTEN IN ALL CAPS -allowed compliance with Sonar Cloud/Travis integration
	 */
	@GetMapping(value = "owner/{owner}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Category>> getCategoryByOwner(@PathVariable(name = "owner") CategoryOwner owner)
	{
		log.debug("Getting all categories from database by owner");
		
		List<Category> cList = cs.getCategoriesByCategoryOwner(owner);
		if (cList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(cList, HttpStatus.OK);
	}
	
	/**
	 * Waits for http request and calls the CategoryService method createCategory()
	 * to save the category to the database
	 * 
	 * @param c
	 * 
	 * @return http response: CREATED
	 */
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Category> createCategory(@RequestBody Category c) {
		log.debug("Saving new category: " + c);
		Category category; 
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			category = cs.createCategory(c);
			return new ResponseEntity<>(category, HttpStatus.CREATED);
		}
	}
	
	/**
	 * Updates a Category entry in the database
	 * 
	 * @param - c - the Category entry to be updated
	 * 
	 * @return - returns an http status code: NO_CONTENT
	 */
	@PutMapping(value="/update", consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category c) {
		log.debug("Updating category: " + c);
		Category category;
		if (c == null) {
			log.debug("No valid category to create: BAD REQUEST");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		category = cs.updateCategory(c);
		log.debug("Create categroy: " + c);
		return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes a Category entry from the database, soft delete, sets Category active
	 * field to false
	 * 
	 * @param - c - the Category entry to be deleted
	 * 
	 * @return returns an http status code: NO_CONTENT
	 */
	@DeleteMapping(value="/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Boolean> deleteCategory(@Valid @RequestBody Category c)
	{
		if (cs.deleteCategory(c) != null) {
			log.debug("Deleting category: " + c);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			log.debug("No valid category to delete");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
