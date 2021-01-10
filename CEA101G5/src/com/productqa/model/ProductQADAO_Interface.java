package com.productqa.model;

import java.util.List;

public interface ProductQADAO_Interface {
	public void insert(ProductQAVO productQAVO);
	public void update(ProductQAVO productQAVO);
	public void delete(Integer pqaId);
	public ProductQAVO getOne(Integer pqaId);
	public List<ProductQAVO> getAll();
	public List<ProductQAVO> getAllNull();
	public List<ProductQAVO> getQAByProductId(String productId);
}
