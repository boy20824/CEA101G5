package com.restaurantpicture.model;

import java.util.List;

public interface RestaurantPicture_interface {
	public void insert(RestaurantPictureVO restaurantPictureVO);

	public void update(RestaurantPictureVO restaurantPictureVO);

	public RestaurantPictureVO findByPrimaryKey(String storePictureId);

	public void delete(String storePictureId);
	
	public List<RestaurantPictureVO> getAll();
}
