package com.productphoto.model;

import java.io.Serializable;

public class ProductPhotoVO implements Serializable {
	private Integer productPhotoId;
	private String productId;
	private byte[] productPhoto;
	
	public ProductPhotoVO() {
		super();
	}
	
	public void setProductPhotoId(Integer productPhotoId) {
		this.productPhotoId = productPhotoId;
	}
	
	public Integer getProductPhotoId() {
		return productPhotoId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}
	
	public byte[] getProductPhoto() {
		return productPhoto;
	}
	
}
