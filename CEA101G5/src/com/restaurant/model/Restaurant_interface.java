package com.restaurant.model;

import java.util.List;

public interface Restaurant_interface {

	public void insert(RestaurantVO restaurantVO);

	public void update(RestaurantVO restaurantVO);

	public RestaurantVO findByPrimaryKey(String storeId);

	public List<RestaurantVO> getAll();
}
