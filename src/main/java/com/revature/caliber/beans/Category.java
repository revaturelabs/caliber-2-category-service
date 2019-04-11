package com.revature.caliber.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.services.CategoryService;

@Component
public class Category {
	
	@Autowired
	CategoryService cs;

}
