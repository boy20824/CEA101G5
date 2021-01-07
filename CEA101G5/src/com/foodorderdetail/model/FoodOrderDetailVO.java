package com.foodorderdetail.model;

public class FoodOrderDetailVO implements java.io.Serializable {

	private String menuId;
	private String foodOrderId;
	private Integer menuNum;
	private Integer menuPrice;
	
	public void orderDetailVO(){
		
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(String foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public Integer getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(Integer menuNum) {
		this.menuNum = menuNum;
	}

	public Integer getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}
	
}
