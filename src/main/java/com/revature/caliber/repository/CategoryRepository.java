package com.revature.caliber.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.caliber.beans.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("SELECT c FROM Category c WHERE c.isActive = true")
	List<Category> getAllActiveCategories();

	@Query("SELECT c FROM Category c WHERE c.isActive = false")
	List<Category> getAllInavctiveCategories();

	@Query("SELECT c FROM Category c WHERE lower(c.skillCategory) = lower(?1)")
	List<Category> getCategoryByName(String categoryName);
	
}
