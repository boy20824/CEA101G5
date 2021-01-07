package com.foodorderdetail.model;

import java.util.List;

public interface FoodOrderDetail_interface {
	public void insert(FoodOrderDetailVO  foodOrderDetailVO);
    public void update(FoodOrderDetailVO foodOrderDetailVO);
    public void delete(String menu_id,String foodOrderId);
    public FoodOrderDetailVO findByPrimaryKey(String menuId,String foodOrderId);
    public List<FoodOrderDetailVO> getAll(String foodOrderId);
    
  //同時新增訂單與訂單明細 
    public void insert2 (FoodOrderDetailVO foodOrderDetailVO , java.sql.Connection con);
    
}
