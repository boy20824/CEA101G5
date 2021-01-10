package com.productphoto.model;

import java.util.List;

public interface ProductPhotoDAO_Interface {
	public void insert(ProductPhotoVO productPhotoVO);
	public void update(ProductPhotoVO productPhotoVO);
	public void delete(Integer productPhotoId);
	public ProductPhotoVO getOne(Integer productPhotoId, String productId);
	public List<ProductPhotoVO> getAll(String productId);
}