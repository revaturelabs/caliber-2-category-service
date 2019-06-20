package com.revature.caliber.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public List<Category> findCategoriesByCategoryOwner(CategoryOwner categoryOwner);
	
	@Query("SELECT c FROM Category c WHERE c.isActive = true")
	List<Category> getAllActiveCategories();
	
}
