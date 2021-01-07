package com.restaurantcmt.model;


import java.util.List;


public interface RestaurantCmt_interface {
	public void insert(RestaurantCmtVO restaurantCmtVO);
    public void update(RestaurantCmtVO restaurantCmtVO);
    public void delete(String storeCmtId);
    public RestaurantCmtVO findByPrimaryKey(String storeId , String memPhone);
    public List<RestaurantCmtVO> getAll(String storeId);
}
