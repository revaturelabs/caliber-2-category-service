package com.revature.caliber.JUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class CategoriesServiceTest {

	
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
		c = new Category(1, "Java", true, "Owner" );
		
		clist.addAll(Arrays.asList(new Category[] {c, new Category()}));
		
		when(cr.findAll()).thenReturn(clist);
		
		when(cr.getOne(1)).thenReturn(c);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCreateCategory() {
		cs.createCategory(c);
		verify(cr).save(c);		
	}
	
	@Test
	public void testGetAllCategories() {
		assertEquals("All categories should be retrieved", clist, this.cs.getAllCategories());
	}
	
	@Test
	public void testGetSingleCategory() {
		assertEquals("A single category should be retrieved", c, this.cs.getCategory(1));
	}
	
	@Test
	public void testupdateCategory() {
		cs.updateCategory(c);
		verify(cr).save(c);
	}
	
	@Test
	public void testDeleteCategory() {
		cs.deleteCategory(c);
		verify(cr).delete(c);
	}
}
