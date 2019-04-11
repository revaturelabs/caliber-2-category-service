package com.revature.caliber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.services.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	CategoryService cs;
}
