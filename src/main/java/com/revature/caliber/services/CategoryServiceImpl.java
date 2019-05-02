package com.revature.caliber.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;
import com.revature.caliber.repository.CategoryRepository;

/**
 * 
 * @author David Van Alstine
 * 
 * Service class for communication with repository.
 *
 */

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	
	@Autowired
	CategoryRepository cr;
	
	/**
	 * Create a new category to be added to the database
	 * 
	 * @param c - the category to be added
	 * 
	 */
	@Override
	public Category createCategory(Category c) {
		
		if(cr.findOne(c.getCategoryId()) == null)
			return cr.save(c);
		else
			return null;
	}

	
	/**
	 * Retrieve all categories from the database
	 * 
	 * @return - returns all Category items
	 * 
	 */
	@Override
	public List<Category> getAllCategories() {
		
		return cr.findAll();
	}

	
	/**
	 * Retrieve a single Category, based on Category id
	 * 
	 * @param - id - the id of the Category to be retrieved
	 * 
	 * @return - the Category with a matching id if it exists, otherwise null
	 */
	@Override
	public Category getCategory(int id) {
		
		return cr.findOne(id);
	}
	
	/**
	 * Retrieve a single Category, based on the Category owner
	 */
	@Override
	public List<Category> getCategoriesByCategoryOwner(CategoryOwner owner) {
		
		return cr.findCategoriesByCategoryOwner(owner);
	}


	
	/**
	 * Updates a provided Category, preserves the id
	 * 
	 * @param - c - the Category item to be updated
	 */
	@Override
	public Category updateCategory(Category c) {
		
		if((cr.findOne(c.getCategoryId()) != null))
			return cr.save(c);
		return null;
	}

	
	/**
	 * Removes a Category from the database
	 * 
	 * @param - c - the Category to to be deleted
	 */
	@Override
	public Boolean deleteCategory(Category c) {
		
		try
		{
			cr.delete(c);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}
