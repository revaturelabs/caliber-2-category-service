package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Category;

@Service
public interface CategoryService {

	public void createCategory(Category c);
	
	public List<Category> getAllCategories();
	
	public Category getCategory(int id);
	
	public void updateCategory(Category c);
	
	public void deleteCategory(Category c);
	
}
