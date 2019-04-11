package com.revature.caliber.beans;

import org.springframework.data.annotation.Id;
@Entity
@Table(name="CALIBER_CATEGORY")
public class Category {
	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;
	private String skillCategory;
	private boolean isActive;

}
