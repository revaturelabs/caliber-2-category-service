package com.revature.caliber.repository;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public List<Category> findCategoriesByCategoryOwner(CategoryOwner categoryOwner);
}
