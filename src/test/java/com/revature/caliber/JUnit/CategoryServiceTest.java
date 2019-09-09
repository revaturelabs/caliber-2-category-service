package com.revature.caliber.JUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.revature.caliber.beans.Category;
import com.revature.caliber.repository.CategoryRepository;
import com.revature.caliber.services.CategoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	
	private static final Logger log = Logger.getLogger(CategoryServiceTest.class);

	
	@Mock
	CategoryRepository cr;
	
	@InjectMocks
	CategoryServiceImpl cs;
	
	private static Category c;
	
	private static List<Category> clist;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clist = new ArrayList<Category>();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		c = new Category(1, "Java", true);
		
		clist.addAll(Arrays.asList(new Category[] {c, new Category()}));
		
		when(cr.findAll()).thenReturn(clist);
		
		when(cr.findOne(1)).thenReturn(c);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCreateCategory() {
		log.debug("Test creating a category service");
		
		cs.createCategory(c);
		verify(cr).findOne(c.getCategoryId());
	}
	
	@Test
	public void testGetAllCategories() {
		log.debug("Test getting all categories service");

		assertEquals("All categories should be retrieved", clist, this.cs.getAllCategories());
	}
	
	@Test
	public void testGetSingleCategory() {
		log.debug("Test getting single category service");
		
		assertEquals("A single category should be retrieved", c, this.cs.getCategory(1));
	}
	
	@Test
	public void testupdateCategory() {
		log.debug("Test category update service");
		
		cs.updateCategory(c);
		verify(cr).save(c);
	}
	
	@Test
	public void testDeleteCategory() {
		log.debug("Test category delete service");
		
		cs.deleteCategory(c);
		verify(cr).delete(c);
	}
}
