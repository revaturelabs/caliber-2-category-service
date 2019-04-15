package com.revature.caliber.repository;

import org.springframework.stereotype.Repository;
import com.revature.caliber.beans.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
