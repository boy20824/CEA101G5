package com.promotiondetail.model;

public class PromotionDetailVO {

	private Integer promoId;
	private String productId;
	private Integer productPrice;
	private Integer productPromoQty;
	
	public PromotionDetailVO() {
		
	}
	
	public Integer getPromoId() {
		return promoId;
	}
	
	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
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
	
	public Integer getProductPromoQty() {
		return productPromoQty;
	}
	
	public void setProductPromoQty(Integer productPromoQty) {
		this.productPromoQty = productPromoQty;
	}
}
