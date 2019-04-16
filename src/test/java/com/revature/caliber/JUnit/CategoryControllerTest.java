package com.revature.caliber.JUnit;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

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
		mockCategoryService.createCategory(c1);
		verify(mockCategoryService).createCategory(c1);
	}
	
	@Test
	public void testGetAllCategories() {
		
//		List<Category> cList = mockCategoryService.getAllCategories().getBody();
	}
	

}
