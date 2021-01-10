package com.productcategory.model;

import java.io.Serializable;

public class ProductCategoryVO implements Serializable  {
	private Integer categoryId;
	private String categoryName;
	private Integer categoryStatus;
	
	public ProductCategoryVO() {
		super();
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryStatus(Integer categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	public Integer getCategoryStatus() {
		return categoryStatus;
	}
	
}
