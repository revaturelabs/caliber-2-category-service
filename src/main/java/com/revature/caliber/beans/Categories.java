package com.revature.caliber.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.services.CategoriesService;

@Component
public class Categories {
	
	@Autowired
	CategoriesService cs;

}
