package com.restaurantpicture.model;

import java.sql.Connection;
import java.util.List;

public interface RestaurantPicture_interface {
	public void insert(RestaurantPictureVO restaurantPictureVO);
	public void insertWithStore(RestaurantPictureVO restaurantPictureVO , Connection con);
	public List<RestaurantPictureVO> insert(List<RestaurantPictureVO> list);
	public void update(RestaurantPictureVO restaurantPictureVO);
	public RestaurantPictureVO findByPrimaryKey(String storePictureId);
	public List<RestaurantPictureVO> findByStoreId(String storeId);
	public void delete(String storePictureId);
	public List<RestaurantPictureVO> getAll();
	public RestaurantPictureVO findOneByStoreId(String storeId);
}
