package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	/**
	 * Returns all Categories for the database
	 * 
	 * @return cList - a List object with all the Category entities from the
	 *         database
	 */
	@GetMapping(value = "all/category", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<List<Category>> getAllCatagories()
	{
		log.debug("Getting all categories from database");
		
		List<Category> cList = cs.getAllCategories();
		if (cList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(cList, HttpStatus.OK);
	}
	
	/**
	 * Returns a string version of a single Category object from the database
	 * 
	 * @param id - the id of the Category object to be returned
	 * 
	 * @return - Returns a string of the category object with the id provided
	 */
	@GetMapping(value = "all/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Integer id)
	{
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
	 */
	@GetMapping(value = "all/category/owner/{owner}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@PostMapping(value="vp/category", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Category> createCategory(Category c) {
		log.debug("Saving new category: " + c);
		cs.createCategory(c);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	/**
	 * Updates a Category entry in the database
	 * 
	 * @param - c - the Category entry to be updated
	 * 
	 * @return - returns an http status code: NO_CONTENT
	 */
	@PutMapping(value="vp/category", consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Void> updateCategory(Category c)
	{
		log.debug("Updating category: " + c);
		cs.updateCategory(c);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes a Category entry from the database, soft delete, sets Category active
	 * field to false
	 * 
	 * @param - c - the Category entry to be deleted
	 * 
	 * @return returns an http status code: NO_CONTENT
	 */
	@DeleteMapping(value="vp/category", consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Void> deleteCategory(Category c)
	{
		log.debug("Deleting category: " + c);
		cs.deleteCategory(c);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
