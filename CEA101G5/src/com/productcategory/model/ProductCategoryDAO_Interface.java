package com.productcategory.model;

import java.util.List;
import java.util.Set;

public interface ProductCategoryDAO_Interface {
	public void insert(ProductCategoryVO productCategoryVO);
	public void update(ProductCategoryVO productCategoryVO);
	public void delete(Integer categoryId);
	public ProductCategoryVO getOne(Integer categoryId);
	public List<ProductCategoryVO> getAll();
	
	public void add(ProductCategoryVO productCategoryVO);
}
