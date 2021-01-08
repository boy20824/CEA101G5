package com.restaurantpicture.model;

import java.util.List;

public interface RestaurantPicture_interface {
	public void insert(RestaurantPictureVO restaurantPictureVO);
	
	public List<RestaurantPictureVO> insert(List<RestaurantPictureVO> list);

	public void update(RestaurantPictureVO restaurantPictureVO);

	public RestaurantPictureVO findByPrimaryKey(String storePictureId);
	
	public List<RestaurantPictureVO> findByStoreId(String storeId);

	public void delete(String storePictureId);
	
	public List<RestaurantPictureVO> getAll();
}
