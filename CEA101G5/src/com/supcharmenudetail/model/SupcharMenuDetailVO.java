package com.supcharmenudetail.model;

public class SupcharMenuDetailVO implements java.io.Serializable{
	private String foodOrderId; 
	private String menuId;
	private String menuSupcharDetailId;
	private String menuSupcharId;
	
	public SupcharMenuDetailVO() {
		
	}
	
	public String getFoodOrderId() {
		return foodOrderId;
	}
	public void setFoodOrderId(String foodOrderId) {
		this.foodOrderId = foodOrderId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuSupcharDetailId() {
		return menuSupcharDetailId;
	}
	public void setMenuSupcharDetailId(String menuSupcharDetailId) {
		this.menuSupcharDetailId = menuSupcharDetailId;
	}
	public String getMenuSupcharId() {
		return menuSupcharId;
	}
	public void setMenuSupcharId(String menuSupcharId) {
		this.menuSupcharId = menuSupcharId;
	}
	
}
