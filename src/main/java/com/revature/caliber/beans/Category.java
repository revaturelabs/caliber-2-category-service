package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/*
 * Author: Peyton Shriver
 */


@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7741944859376345541L;

  @Id
  @Column(name = "CATEGORY_ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_SEQUENCE")
  @SequenceGenerator(name = "CATEGORY_ID_SEQUENCE", sequenceName = "CATEGORY_ID_SEQUENCE")
  private int categoryId;

  @Column(name = "SKILL_CATEGORY")
  private String skillCategory;

  @Column(name = "IS_ACTIVE")
  @JsonProperty(value = "active")
  private boolean isActive = true;

  public Category() {
    super();
  }


  public Category(String skillCategory, boolean isActive) {
    super();
    this.skillCategory = skillCategory;
    this.isActive = isActive;
  }


  public Category(int categoryId, String skillCategory, boolean isActive) {
    super();
    this.categoryId = categoryId;
    this.skillCategory = skillCategory;
    this.isActive = isActive;
  }


  public int getCategoryId() {
    return categoryId;
  }


  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }


  public String getSkillCategory() {
    return skillCategory;
  }


  public void setSkillCategory(String skillCategory) {
    this.skillCategory = skillCategory;
  }


  public boolean isActive() {
    return isActive;
  }


  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + categoryId;
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
    if (isActive != other.isActive)
      return false;
    if (skillCategory == null) {
      return other.skillCategory == null;
    } else {
      return skillCategory.equals(other.skillCategory);
    }
  }

  @Override
  public String toString() {
    return "Category [categoryId=" + categoryId + ", skillCategory=" + skillCategory + ", isActive=" + isActive
      + "]";
  }

}
