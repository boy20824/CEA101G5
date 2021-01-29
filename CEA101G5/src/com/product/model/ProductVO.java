package com.product.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="PRODUCT")
public class ProductVO implements Serializable {
	
	
	@Id
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_DESCRIPTION")
	private String productDescription;
	
	@Column(name="PRODUCT_MSRP")
	private Integer productMSRP;
	
	@Column(name="PRODUCT_PRICE")
	private Integer productPrice;
	
	@Column(name="PRODUCT_QTY_SOLD")
	private Integer productQtySold;
	
	@Column(name="CATEGORY_ID")
	private Integer categoryId;
	
	@Column(name="PRODUCT_STATUS")
	private Integer productStatus;
	
	private Integer productQty;
	private Integer orderId;
	private Integer productPromoPrice;
	
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
	
	public void setProductPromoPrice(Integer productPromoPrice) {
		this.productPromoPrice = productPromoPrice;
	}
	
	public Integer getProductPromoPrice() {
		return productPromoPrice;
	}
	
}
