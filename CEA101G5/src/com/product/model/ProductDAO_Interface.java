package com.product.model;

import java.util.List;

public interface ProductDAO_Interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(String productId);
	public ProductVO getOne(String productId);
	public List<ProductVO> getAll();
	
	public List<ProductVO> getAllByCategoryId(Integer categoryId);
	public List<ProductVO> getAllByProductQtySold();
	public List<ProductVO> getAllByKeywordSearch(String keyword);
	public List<ProductVO> getAllByPriceLTH();
	public List<ProductVO> getAllByPriceHTL();
	
	public void testU(String productId,String productName, String productDescription, Integer productMSRP, Integer productPrice,Integer categoryId, Integer productStatus);
	public void add(ProductVO productVO);
	public String getPID();
}
