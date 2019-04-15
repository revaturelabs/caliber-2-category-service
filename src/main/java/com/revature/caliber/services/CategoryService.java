package com.revature.caliber.services;

import java.util.List;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;


public interface CategoryService {

	public void createCategory(Category c);
	
	public List<Category> getAllCategories();
	
	public Category getCategory(int id);
	
	public List<Category> getCategoriesByCategoryOwner(CategoryOwner owner);
	
	public void updateCategory(Category c);
	
	public void deleteCategory(Category c);
	
}
