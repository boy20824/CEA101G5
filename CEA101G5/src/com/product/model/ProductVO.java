package com.product.model;

import java.io.Serializable;

public class ProductVO implements Serializable {
	private String productId;
	private String productName;
	private String productDescription;
	private Integer productMSRP;
	private Integer productPrice;
	private Integer productQtySold;
	private Integer categoryId;
	private Integer productStatus;
	
	private Integer productQty;
	private Integer orderId;
	
	public ProductVO() {
		super();
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	
	public void setProductMSRP(Integer productMSRP) {
		this.productMSRP = productMSRP;
	}
	
	public Integer getProductMSRP() {
		return productMSRP;
	}
	
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	public Integer getProductPrice() {
		return productPrice;
	}
	
	public void setProductQtySold(Integer productQtySold) {
		this.productQtySold = productQtySold;
	}
	
	public Integer getProductQtySold() {
		return productQtySold;
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	
	public Integer getProductStatus() {
		return productStatus;
	}
	
	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}
	
	public Integer getProductQty() {
		return productQty;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
}
