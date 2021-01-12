package com.product.model;

import java.util.List;

public class ProductService {
	
	private ProductDAO_Interface dao;
	
	public ProductService() {
		dao = new ProductJNDIDAO();
	}
	
	public ProductVO addProduct(String productId, String productName, String productDescription, Integer productMSRP, Integer productPrice, Integer productQtySold, Integer categoryId, Integer productStatus) {
		ProductVO productVO = new ProductVO();
		
		productVO.setProductId(productId);
		productVO.setProductName(productName);
		productVO.setProductDescription(productDescription);
		productVO.setProductMSRP(productMSRP);
		productVO.setProductPrice(productPrice);
		productVO.setProductQtySold(productQtySold);
		productVO.setCategoryId(categoryId);
		productVO.setProductStatus(productStatus);
		dao.insert(productVO);
		
		return productVO;
	}
	
	public ProductVO updateProduct(String productId, String productName, String productDescription, Integer productMSRP, Integer productPrice, Integer productQtySold, Integer categoryId, Integer productStatus) {
		ProductVO productVO = new ProductVO();
		
		productVO.setProductId(productId);
		productVO.setProductName(productName);
		productVO.setProductDescription(productDescription);
		productVO.setProductMSRP(productMSRP);
		productVO.setProductPrice(productPrice);
		productVO.setProductQtySold(productQtySold);
		productVO.setCategoryId(categoryId);
		productVO.setProductStatus(productStatus);
		dao.update(productVO);
		
		return productVO;
	}
	
	public ProductVO getProductById(String productId) {
		return dao.getOne(productId);
	}
	
	public List<ProductVO> getAllProducts() {
		return dao.getAll();
	}
	
	public List<ProductVO> getAllProductsByCategory(Integer categoryId) {
		return dao.getAllByCategoryId(categoryId);
	}
	
	public List<ProductVO> getAllProductsByQtySold() {
		return dao.getAllByProductQtySold();
	}
	
	public List<ProductVO> getAllByKeywordSearch(String keyword) {
		return dao.getAllByKeywordSearch(keyword);
	}
	
	public List<ProductVO> getAllByProductPriceLTH() {
		return dao.getAllByPriceLTH();
	}
	
	public List<ProductVO> getAllByProductPriceHTL() {
		return dao.getAllByPriceHTL();
	}
	
	public void testU(String productId,String productName, String productDescription, Integer productMSRP, Integer productPrice ,Integer categoryId,Integer productStatus) {
		dao.testU(productId,productName,productDescription,productMSRP,productPrice,categoryId,productStatus);
		
	}
	
	public ProductVO add(String productName, String productDescription, Integer productMSRP, Integer productPrice, Integer productQtySold, Integer categoryId, Integer productStatus) {
		ProductVO productVO = new ProductVO();
		
		productVO.setProductName(productName);
		productVO.setProductDescription(productDescription);
		productVO.setProductMSRP(productMSRP);
		productVO.setProductPrice(productPrice);
		productVO.setProductQtySold(productQtySold);
		productVO.setCategoryId(categoryId);
		productVO.setProductStatus(productStatus);
		dao.add(productVO);
		
		return productVO;
	}
	
	public String getPID() {
		return dao.getPID();
	}
	
	public static void main(String[] args) {
		ProductService productService = new ProductService();
		
//		testing : addProduct()
//		productService.addProduct("ENP0016", "���ղ��~", "���ղ��~�ԭz", 111, 222, 333, 1, 1);
//		System.out.println("Statement Processed...");
		
//		testing : updateProduct()
//		productService.updateProduct("ENP0016", "���ղ��~", "���ղ��~�ԭz", 777, 888, 999, 1, 1);
//		System.out.println("Statement Processed...");
		
//		testing : getProductById()
		ProductVO productVO = productService.getProductById("ENP0007");
		System.out.println("PRODUCT ID: " + productVO.getProductId());
		System.out.println("PRODUCT NAME: " + productVO.getProductName());
		System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
		System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
		System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
		System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
		System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
		System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
		
//		testing : getAllProducts();
//		List<ProductVO> list = productService.getAllProducts();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllProductsByCategory();
//		List<ProductVO> list = productService.getAllProductsByCategory(1);
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllProductsByQtySold();
//		List<ProductVO> list = productService.getAllProductsByQtySold();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByKeywordSearch();
//		List<ProductVO> list = productService.getAllByKeywordSearch("     ����    ");
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByProductPriceLTH();
//		List<ProductVO> list = productService.getAllByProductPriceLTH();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getAllByProductPriceHTL();
//		List<ProductVO> list = productService.getAllByProductPriceHTL();
//		for (ProductVO productVO : list) {
//			System.out.println("PRODUCT ID: " + productVO.getProductId());
//			System.out.println("PRODUCT NAME: " + productVO.getProductName());
//			System.out.println("PRODUCT DESCRIPTION: " + productVO.getProductDescription());
//			System.out.println("PRODUCT MSRP: " + productVO.getProductMSRP());
//			System.out.println("PRODUCT PRICE: " + productVO.getProductPrice());
//			System.out.println("PRODUCT SOLD: " + productVO.getProductQtySold());
//			System.out.println("PRODUCT CATEGORY: " + productVO.getCategoryId());
//			System.out.println("PRODUCT STATUS: " + productVO.getProductStatus());
//			System.out.println("-----------------------------------");
//		}
		
	}
}
