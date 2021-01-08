package com.restaurant.model;

import java.util.List;
import java.util.Map;
import com.restaurantpicture.model.RestaurantPictureVO;

public interface Restaurant_interface {

	public void insert(RestaurantVO restaurantVO);
	public void easyinsert(RestaurantVO restaurantVO);
	public void easyInsertWithPics(RestaurantVO restaurantVO,RestaurantPictureVO restaurantPictureVO);
	public void update(RestaurantVO restaurantVO);
	public void delete(String storeId);
	public RestaurantVO findByPrimaryKey(String storeId);
	public List<RestaurantVO> getAll();
	public List<RestaurantVO> getAll(Map<String, String[]> map); 
}
