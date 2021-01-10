package com.foodorder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FoodOrderVO implements Serializable {
	
	private String foodOrderId;
	private String memPhone;
	private String storeId;
	private Timestamp foodOrderTime;
	private Timestamp foodOrderCompleteTime;
	private Integer foodOrderTotalPrice;
	private String foodOrderNote;
	private Integer foodOrderStatus;
	private Integer foodOrderCmtStatus;
	
	public FoodOrderVO() {
		super();
	}

	public String getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(String foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Timestamp getFoodOrderTime() {
		return foodOrderTime;
	}

	public void setFoodOrderTime(Timestamp foodOrderTime) {
		this.foodOrderTime = foodOrderTime;
	}

	public Timestamp getFoodOrderCompleteTime() {
		return foodOrderCompleteTime;
	}

	public void setFoodOrderCompleteTime(Timestamp foodOrderCompleteTime) {
		this.foodOrderCompleteTime = foodOrderCompleteTime;
	}

	public Integer getFoodOrderTotalPrice() {
		return foodOrderTotalPrice;
	}

	public void setFoodOrderTotalPrice(Integer foodOrderTotalPrice) {
		this.foodOrderTotalPrice = foodOrderTotalPrice;
	}

	public String getFoodOrderNote() {
		return foodOrderNote;
	}

	public void setFoodOrderNote(String foodOrderNote) {
		this.foodOrderNote = foodOrderNote;
	}

	public Integer getFoodOrderStatus() {
		return foodOrderStatus;
	}

	public void setFoodOrderStatus(Integer foodOrderStatus) {
		this.foodOrderStatus = foodOrderStatus;
	}

	public Integer getFoodOrderCmtStatus() {
		return foodOrderCmtStatus;
	}

	public void setFoodOrderCmtStatus(Integer foodOrderCmtStatus) {
		this.foodOrderCmtStatus = foodOrderCmtStatus;
	}
	
	

}
