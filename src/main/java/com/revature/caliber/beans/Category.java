package com.revature.caliber.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;


@Entity
@Table(name="CALIBER_CATEGORY")
@Document(collection = "category")
public class Category {
	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;
	private String skillCategory;
	private boolean isActive;

}
