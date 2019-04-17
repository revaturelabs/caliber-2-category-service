package com.revature.caliber.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Author: Peyton Shriver
 */


@Entity
@Table(name="CATEGORY")
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7741944859376345541L;

	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="SKILL_CATEGORY")
	private String skillCategory;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="CATEGORY_OWNER")
	private CategoryOwner categoryOwner;

	public Category() {
		super();
	}
	
	

	public Category(String skillCategory, Boolean isActive, CategoryOwner categoryOwner) {
		super();
		this.skillCategory = skillCategory;
		this.isActive = isActive;
		this.categoryOwner = categoryOwner;
	}



	public Category(Integer categoryId, String skillCategory, Boolean isActive, CategoryOwner categoryOwner) {
		super();
		this.categoryId = categoryId;
		this.skillCategory = skillCategory;
		this.isActive = isActive;
		this.categoryOwner = categoryOwner;
	}



	public Integer getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}



	public String getSkillCategory() {
		return skillCategory;
	}



	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}



	public Boolean isActive() {
		return isActive;
	}



	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}



	public CategoryOwner getCategoryOwner() {
		return categoryOwner;
	}



	public void setCategoryOwner(CategoryOwner categoryOwner) {
		this.categoryOwner = categoryOwner;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((categoryOwner == null) ? 0 : categoryOwner.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((skillCategory == null) ? 0 : skillCategory.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryOwner != other.categoryOwner)
			return false;
		if (isActive != other.isActive)
			return false;
		if (skillCategory == null) {
			if (other.skillCategory != null)
				return false;
		} else if (!skillCategory.equals(other.skillCategory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", skillCategory=" + skillCategory + ", isActive=" + isActive
				+ ", categoryOwner=" + categoryOwner + "]";
	}

}
