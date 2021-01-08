package com.restaurantpicture.model;

public class RestaurantPictureVO implements java.io.Serializable{
	private String storePictureId;
	private String storeId;
	private byte[] storePicture;
	
	
	public RestaurantPictureVO() {
		
	}
	
	
	public String getStorePictureId() {
		return storePictureId;
	}
	public void setStorePictureId(String storePictureId) {
		this.storePictureId = storePictureId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public byte[] getStorePicture() {
		return storePicture;
	}
	public void setStorePicture(byte[] storePicture) {
		this.storePicture = storePicture;
	}
}
