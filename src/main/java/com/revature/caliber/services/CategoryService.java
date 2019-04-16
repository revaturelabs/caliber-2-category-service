package com.revature.caliber.services;

import java.util.List;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;


public interface CategoryService {

	public Category createCategory(Category c);
	
	public List<Category> getAllCategories();
	
	public Category getCategory(int id);
	
	public List<Category> getCategoriesByCategoryOwner(CategoryOwner owner);
	
	public Category updateCategory(Category c);
	
	public Boolean deleteCategory(Category c);
	
}
