package com.orderdetail.model;

import java.io.Serializable;
import java.util.Date;

public class OrderDetailVO implements Serializable {
	private Integer orderId;
	private String productId;
	private Integer productPrice;
	private Integer quantity;
	private String productReview;
	private byte[] productReviewPhoto;
	private Date productReviewTS;
	private Integer productReviewStatus;
	
	public OrderDetailVO() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProductReview() {
		return productReview;
	}

	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}

	public byte[] getProductReviewPhoto() {
		return productReviewPhoto;
	}

	public void setProductReviewPhoto(byte[] productReviewPhoto) {
		this.productReviewPhoto = productReviewPhoto;
	}

	public Date getProductReviewTS() {
		return productReviewTS;
	}

	public void setProductReviewTS(Date productReviewTS) {
		this.productReviewTS = productReviewTS;
	}

	public Integer getProductReviewStatus() {
		return productReviewStatus;
	}

	public void setProductReviewStatus(Integer productReviewStatus) {
		this.productReviewStatus = productReviewStatus;
	}	

}
