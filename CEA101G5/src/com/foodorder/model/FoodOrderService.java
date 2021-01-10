package com.foodorder.model;

import java.sql.Timestamp;
import java.util.List;

import com.foodorderdetail.model.FoodOrderDetailVO;


public class FoodOrderService {

	private FoodOrder_interface dao;
	
	public FoodOrderService () {
		dao = new FoodOrderJNDIDAO();
	}
	
	public FoodOrderVO addFoodOrder(String memPhone,String storeId,Integer foodOrderTotalPrice,String foodOrderNote) {
	
		FoodOrderVO foodOrderVO = new FoodOrderVO();
		
		foodOrderVO.setMemPhone(memPhone);
		foodOrderVO.setStoreId(storeId);
		foodOrderVO.setFoodOrderTotalPrice(foodOrderTotalPrice);
		foodOrderVO.setFoodOrderNote(foodOrderNote);
//		dao.insert(foodOrderVO);
		
		return foodOrderVO;//是否要回傳自增主鍵值綁定?
	}
	public FoodOrderVO updateFoodOrder(String foodOrderId,Timestamp foodOrderCompleteTime,Integer foodOrderStatus) {
		
		FoodOrderVO foodOrderVO = new FoodOrderVO();
		
		foodOrderVO.setFoodOrderCompleteTime(foodOrderCompleteTime);
		foodOrderVO.setFoodOrderStatus(foodOrderStatus);
		foodOrderVO.setFoodOrderId(foodOrderId);
		dao.update(foodOrderVO);
		
		return foodOrderVO;
	}
	public void deletFoodOrder(String foodOrderId) {
		dao.delete(foodOrderId);
	}
	public  FoodOrderVO getOneFoodOrder(String foodOrderId) {
		return dao.findByPrimaryKey(foodOrderId);
	}
	public List<FoodOrderVO> getAll(String memPhone) {
		return dao.getAll(memPhone);
	}
	public void insertWithFoodOrderDetail(FoodOrderVO foodOrderVO,List<FoodOrderDetailVO> foodOrderDetailList) {
		 dao.insertWithFoodOrderDetail(foodOrderVO, foodOrderDetailList);
	}
	public List<FoodOrderVO> getAllByStoreId(String storeId){
		return dao.getAllByStoreId(storeId);
	}
	public List<FoodOrderVO> getAllByMemberPhoneStatus2(String memPhone){
		return dao.getAllByMemberPhoneStatus2(memPhone);
	}
	public void updateOneByFoodOrderId(String foodOrderId) {
		 dao.updateOneByFoodOrderId(foodOrderId);
	}
}
