package com.revature.caliber.JUnit;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.caliber.Application;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.CategoryOwner;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.services.CategoryService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * @author David Van Alstine
 * 
 * This class will test the category controller methods, using RESTassured to mock Http requests to be tested
 * 
 */


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
	
	private static final Logger log = Logger.getLogger(CategoryControllerTest.class);
	
	@Mock
	static CategoryService mockCategoryService;
	
	@InjectMocks
	static CategoryController mockCategoryController;
	
	
	private static final Category c1 = new Category(1, "Java", true, CategoryOwner.TRAINING);
	private static final Category c2 = new Category(18, "Hibernate", true, CategoryOwner.TRAINING);
	private static final Category c3 = new Category(19, "Spring", true, CategoryOwner.TRAINING);
	private static final Category c4 = new Category(26, "Python", true, CategoryOwner.PANEL);
	private static final Category c5 = new Category(36, "Docker", true, CategoryOwner.PANEL);
	private static final Category c6 = null;
	
	

	static List<Category> categories;
	static List<Category> trainingCategories;
	static List<Category> panelCategories;
	
	@BeforeClass
	public static void classSetup() {
		RestAssured.port = 8013;
		categories = new ArrayList<>();
		trainingCategories = new ArrayList<>();
		panelCategories = new ArrayList<>();
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
		
		trainingCategories.add(c1);
		trainingCategories.add(c2);
		trainingCategories.add(c3);
		
		panelCategories.add(c4);
		panelCategories.add(c5);
		
		org.mockito.Mockito.when(mockCategoryService.getAllCategories()).thenReturn(categories);
		org.mockito.Mockito.when(mockCategoryService.getCategoriesByCategoryOwner(CategoryOwner.TRAINING)).thenReturn(trainingCategories);
		org.mockito.Mockito.when(mockCategoryService.getCategoriesByCategoryOwner(CategoryOwner.PANEL)).thenReturn(panelCategories);
		
		org.mockito.Mockito.when(mockCategoryService.getCategory(1)).thenReturn(c1);
		org.mockito.Mockito.when(mockCategoryService.getCategory(18)).thenReturn(c2);
		org.mockito.Mockito.when(mockCategoryService.getCategory(19)).thenReturn(c3);
		org.mockito.Mockito.when(mockCategoryService.getCategory(26)).thenReturn(c4);
		org.mockito.Mockito.when(mockCategoryService.getCategory(36)).thenReturn(c5);
			
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
		log.debug("Test deleting a category");
		
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
		
		Category mockCategory = mockCategoryController.getCategoryById(19).getBody();
		assertEquals("Expected category to be " + categories.get(2), categories.get(2), mockCategory);
	}
	
	@Test
	public void testGetCategoriesByOwner() {
		log.debug("Test retrieving categories by owner");
		
		List<Category> cpList = mockCategoryService.getCategoriesByCategoryOwner(CategoryOwner.PANEL);
		assertEquals("Expected first category to be "+ categories.get(3).getSkillCategory(),categories.get(3).getSkillCategory(),cpList.get(0).getSkillCategory());
		assertEquals("Expected second category to be "+ categories.get(4).getSkillCategory(),categories.get(4).getSkillCategory(),cpList.get(1).getSkillCategory());
		
		List<Category> ctList = mockCategoryService.getCategoriesByCategoryOwner(CategoryOwner.TRAINING);
		assertEquals("Expected first category to be "+ categories.get(0).getSkillCategory(),categories.get(0).getSkillCategory(),ctList.get(0).getSkillCategory());
		assertEquals("Expected second category to be "+ categories.get(1).getSkillCategory(),categories.get(1).getSkillCategory(),ctList.get(1).getSkillCategory());
		assertEquals("Expected third category to be "+ categories.get(2).getSkillCategory(),categories.get(2).getSkillCategory(),ctList.get(2).getSkillCategory());

	}	

	
	@Test
	public void testGetAllReturnsOKStatusCode() {
		log.debug("Testing HTTP get all categories");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).
			
			when().
			
			get("/category/all/category/all").
			
			then().
			
			statusCode(200);
	}
	
	@Test
	public void testGetCategoryByIdReturnsOKStatusCode() {
		log.debug("Testing HTTP get category by id");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).
		
			when().
		
			get("/all/category/1").
		
			then().
		
			statusCode(200);
	}
	
	@Test
	public void testGetCategoryByOwnerReturnsOKStatusCode() {
		log.debug("Testing HTTP get category by id");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).
		
			when().
		
			get("/all/category/owner/PANEL").
		
			then().
		
			statusCode(200);
	}
	
	@Test
	public void testCreateCategoryReturnsCreatedStatusCode() {
		log.debug("Testing HTTP create category");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).body(c1).
		
			when().
			
			post("/vp/category/create").
		
			then().
		
			statusCode(201);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNullCategoryThrowsIllegalArgumentException() {
		log.debug("Testing HTTP create category");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).body(c6).
		
			when().
			
			post("/vp/category/create").
		
			then().
		
			statusCode(500);
	}
	
	@Test
	public void testUpdateCategoryReturnsNoContentStatusCode() {
		log.debug("Testing HTTP update category");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).body(c1).
	
			when().
	
			put("/vp/category/update").
	
			then().
	
			statusCode(204);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNullCategoryThrowsIllegalArgumentException() {
		log.debug("Testing HTTP update category");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).body(c6).
		
			when().
			
			post("/vp/category/update").
		
			then().
		
			statusCode(500);
	}
	
	
	@Test
	public void testDeleteReturnsAcceptedStatusCode() {
		log.debug("Testing HTTP delete category");
		
		given().
		
			standaloneSetup(mockCategoryController).contentType(ContentType.JSON).body(c1).
	
			when().
	
			delete("/vp/category/delete").
	
			then().
	
			statusCode(202);
	}

}
