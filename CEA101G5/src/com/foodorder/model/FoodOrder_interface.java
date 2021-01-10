package com.foodorder.model;

import java.sql.ResultSet;
import java.util.List;
import com.foodorderdetail.model.FoodOrderDetailVO;

public interface FoodOrder_interface {

	 public ResultSet insert(FoodOrderVO foodOrderVO);
     public void update(FoodOrderVO foodOrderVO);
     public void delete(String foodOrderId);
     public FoodOrderVO findByPrimaryKey(String foodOrderId);
     public List<FoodOrderVO> getAll(String memPhone);
     public List<FoodOrderVO> getAllByStoreId(String storeId);
     public List<FoodOrderVO> getAllByMemberPhoneStatus2(String memPhone);
     public void updateOneByFoodOrderId(String foodOrderId);
     
//     新增訂單同時一起產生訂單明細
     public void insertWithFoodOrderDetail(FoodOrderVO foodOrderVO , List<FoodOrderDetailVO> list);
}
