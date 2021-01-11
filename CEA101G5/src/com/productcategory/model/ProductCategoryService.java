package com.productcategory.model;

import java.util.List;

public class ProductCategoryService {
	
	private ProductCategoryDAO_Interface dao;
	
	public ProductCategoryService() {
		dao = new ProductCategoryJNDIDAO();
	}
	
	public ProductCategoryVO addCategory(String categoryName, Integer categoryStatus) {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		
		productCategoryVO.setCategoryName(categoryName);
		productCategoryVO.setCategoryStatus(categoryStatus);
		dao.insert(productCategoryVO);
		
		return productCategoryVO;
	}
	
	public ProductCategoryVO updateCategory(Integer categoryId, String categoryName, Integer categoryStatus) {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		
		productCategoryVO.setCategoryId(categoryId);
		productCategoryVO.setCategoryName(categoryName);
		productCategoryVO.setCategoryStatus(categoryStatus);
		dao.update(productCategoryVO);
		
		return productCategoryVO;
	}
	
	public ProductCategoryVO getCategoryById(Integer categoryId) {
		return dao.getOne(categoryId);
	}
	
	public List<ProductCategoryVO> getAllCategories() {
		return dao.getAll();
	}
	
	public ProductCategoryVO add(String categoryName) {
		ProductCategoryVO productCategoryVO = new ProductCategoryVO();
		
		productCategoryVO.setCategoryName(categoryName);
		dao.add(productCategoryVO);
		
		return productCategoryVO;
	}
	
	public static void main(String[] args) {
		ProductCategoryService productCategoryService = new ProductCategoryService();
		
//		testing : addCategory()
//		productCategoryService.addCategory("�������O", 1);
//		System.out.println("Statement Processed...");
		
//		testing : updateCategory()
//		productCategoryService.updateCategory(3, "�������O", 1);
//		System.out.println("Statement Processed...");
		
//		testing : getCategoryById()
		ProductCategoryVO productCategoryVO = productCategoryService.getCategoryById(1);
		System.out.println("CATEGORY ID: " + productCategoryVO.getCategoryId());
		System.out.println("CATEGORY NAME: " + productCategoryVO.getCategoryName());
		System.out.println("CATEGORY STATUS: " + productCategoryVO.getCategoryStatus());
		
//		testing : getAllCategories();
//		List<ProductCategoryVO> list = productCategoryService.getAllCategories();
//		for (ProductCategoryVO productCategoryVO : list) {
//			System.out.println("CATEGORY ID: " + productCategoryVO.getCategoryId());
//			System.out.println("CATEGORY NAME: " + productCategoryVO.getCategoryName());
//			System.out.println("CATEGORY STATUS: " + productCategoryVO.getCategoryStatus());
//			System.out.println("-----------------------------------");
//		}
	}

}
