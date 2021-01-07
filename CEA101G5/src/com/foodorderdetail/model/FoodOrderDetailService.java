package com.foodorderdetail.model;

import java.util.List;

public class FoodOrderDetailService {

	private FoodOrderDetail_interface dao;
	
	public FoodOrderDetailService() {
		dao = new FoodOrderDetailJNDIDAO();
	}
	
	public FoodOrderDetailVO addFoodOrderDetail(String menuId,String foodOrderId,Integer menuNum,Integer menuPrice) {
	
		FoodOrderDetailVO foodOrderDetailVO = new FoodOrderDetailVO();
		
		foodOrderDetailVO.setMenuId(menuId);
		foodOrderDetailVO.setFoodOrderId(foodOrderId);
		foodOrderDetailVO.setMenuNum(menuNum);
		foodOrderDetailVO.setMenuPrice(menuPrice);
		dao.insert(foodOrderDetailVO);
		
		return foodOrderDetailVO;
	}
	public FoodOrderDetailVO updateFoodOrderDetail(String menuId,String foodOrderId,Integer menuNum,Integer menuPrice) {
		FoodOrderDetailVO foodOrderDetailVO = new FoodOrderDetailVO();
		
		foodOrderDetailVO.setMenuNum(menuNum);
		foodOrderDetailVO.setMenuPrice(menuPrice);
		foodOrderDetailVO.setMenuId(menuId);
		foodOrderDetailVO.setFoodOrderId(foodOrderId);
		dao.update(foodOrderDetailVO);
		
		return foodOrderDetailVO;
	}
	
	public void deletFoodOrderDetail(String menu_id,String foodOrderId) {
		dao.delete(menu_id, foodOrderId);
	}
	public FoodOrderDetailVO getOneFoodOrderDetail(String menuId,String foodOrderId) {
		return dao.findByPrimaryKey(menuId, foodOrderId);
	}
	public List<FoodOrderDetailVO> getAll(String foodOrderId) {
		return dao.getAll(foodOrderId);
	}
	
	
	
}

