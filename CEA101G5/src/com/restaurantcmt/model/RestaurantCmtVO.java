package com.restaurantcmt.model;

public class RestaurantCmtVO implements java.io.Serializable{

	private String storeCmtId;
	private String storeId;
	private String memPhone;
	private String storeCmtContent;
	private Integer storeRating;
	private Integer storeCmtStatus;
	
	public RestaurantCmtVO() {
		
	}
	
	public String getStoreCmtId() {
		return storeCmtId;
	}
	public void setStoreCmtId(String storeCmtId) {
		this.storeCmtId = storeCmtId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getStoreCmtContent() {
		return storeCmtContent;
	}
	public void setStoreCmtContent(String storeCmtContent) {
		this.storeCmtContent = storeCmtContent;
	}
	public Integer getStoreRating() {
		return storeRating;
	}
	public void setStoreRating(Integer storeRating) {
		this.storeRating = storeRating;
	}
	public Integer getStoreCmtStatus() {
		return storeCmtStatus;
	}
	public void setStoreCmtStatus(Integer storeCmtStatus) {
		this.storeCmtStatus = storeCmtStatus;
	}
}
