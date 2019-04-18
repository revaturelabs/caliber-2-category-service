package com.revature.caliber.JUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.Application;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.services.CategoryService;

import io.restassured.RestAssured;

/**
 * @author David Van Alstine
 * 
 * This class will test the category controller methods, using RESTassured to mock Http requests to be tested
 * 
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
	
	private static final Logger log = Logger.getLogger(CategoryControllerTest.class);
	
	@Mock
	static CategoryService mockCategoryService;
	
	@InjectMocks
	static CategoryController mockCategoryController;
	
	@LocalServerPort
	private int port;
	
	private static Category c1 = new Category(1, "Java", true, CategoryOwner.Training);
	private static Category c2 = new Category(2, "Spring", true, CategoryOwner.Panel);
	private static Category c3 = new Category(3, "Hibernate", false, CategoryOwner.Panel);
	private static Category c4 = new Category(4, "SQL", true, CategoryOwner.Panel);
	private static Category c5 = new Category(5, "Microservices", false, CategoryOwner.Training);

	static Category category;
	static List<Category> categories;
	
	@BeforeClass
	public static void classSetup() {
		RestAssured.port = 9000;
		categories = new ArrayList<Category>();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setup() throws Exception {
		categories.clear();
		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		categories.add(c4);
		categories.add(c5);
		
		org.mockito.Mockito.when(mockCategoryService.getAllCategories()).thenReturn(categories);
//		org.mockito.Mockito.when(mockCategoryService.getCategoriesByCategoryOwner(CategoryOwner.Panel)).thenReturn(categories());
		
		org.mockito.Mockito.when(mockCategoryService.getCategory(1)).thenReturn(c1);
		org.mockito.Mockito.when(mockCategoryService.getCategory(2)).thenReturn(c2);
		org.mockito.Mockito.when(mockCategoryService.getCategory(3)).thenReturn(c3);
		org.mockito.Mockito.when(mockCategoryService.getCategory(4)).thenReturn(c4);
		org.mockito.Mockito.when(mockCategoryService.getCategory(5)).thenReturn(c5);
			
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCreateCategory() {
		log.debug("Test creating a category");
		
		mockCategoryService.createCategory(c1);
		verify(mockCategoryService).createCategory(c1);
	}
	
	@Test
	public void testUpdateCategory() {
		log.debug("Test updating a category");
		
		mockCategoryService.updateCategory(c2);
		verify(mockCategoryService).updateCategory(c2);
	}
	
	@Test
	public void testDeleteCategory() {
		log.debug("Tst deleting a category");
		
		mockCategoryService.deleteCategory(c4);
		verify(mockCategoryService).deleteCategory(c4);
	}
	
	@Test
	public void testGetAllCategories() {
		log.debug("Test retrieving all categories");
		
		List<Category> cList = mockCategoryService.getAllCategories();
		System.out.println(cList);
		
		assertEquals("Expected first category to be "+ categories.get(0).getSkillCategory(),categories.get(0).getSkillCategory(),cList.get(0).getSkillCategory());
		assertEquals("Expected second category to be "+ categories.get(1).getSkillCategory(),categories.get(1).getSkillCategory(),cList.get(1).getSkillCategory());
		assertEquals("Expected third category to be "+ categories.get(2).getSkillCategory(),categories.get(2).getSkillCategory(),cList.get(2).getSkillCategory());
		assertEquals("Expected fourth category to be "+ categories.get(3).getSkillCategory(),categories.get(3).getSkillCategory(),cList.get(3).getSkillCategory());
		assertEquals("Expected fifth category to be "+ categories.get(4).getSkillCategory(),categories.get(4).getSkillCategory(),cList.get(4).getSkillCategory());
	}	
	
	@Test
	public void testGetCategoryById() {
		log.debug("Test retrieving category by id");
		
		Category mockCategory = mockCategoryController.getCategoryById(3).getBody();
		assertEquals("Expected category to be " + categories.get(2), categories.get(2), mockCategory);
	}
	
	@Test
	public void testGetCategoriesByOwner() {
		
	}

}
