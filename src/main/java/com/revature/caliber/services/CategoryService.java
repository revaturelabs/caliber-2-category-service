package com.revature.caliber.services;

import java.util.List;

import com.revature.caliber.beans.Category;


public interface CategoryService {

	public Category createCategory(Category c);
	
	public List<Category> getAllCategories();
	
	public Category getCategory(int id);
	
	public Category updateCategory(Category c);
	
	public Boolean deleteCategory(Category c);
	
	public List<Category> getAllActiveCategories();

	public List<Category> getAllInactiveCategories();
	
}
