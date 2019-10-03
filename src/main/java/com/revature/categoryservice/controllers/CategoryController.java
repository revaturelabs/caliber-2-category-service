package com.revature.categoryservice.controllers;

import com.revature.categoryservice.beans.DynamicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @Autowired
  private DynamicConfig dynamicConfig;

  @GetMapping("/")
  public String getCategory() {
    return dynamicConfig.getMessage();
  }

}
