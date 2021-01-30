package com.promotiondetail.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="PROMOTION_DETAIL")
public class PromotionDetailVO implements Serializable {

	@Id
	@Column(name="PROMO_ID")
	private Integer promoId;
	
	@Id
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="PRODUCT_PRICE")
	private Integer productPrice;
	
	@Column(name="PRODUCT_PROMO_QTY")
	private Integer productPromoQty;
	
	public PromotionDetailVO() {
	}
	
	public PromotionDetailVO(Integer promoId, String productId) {
		this.promoId = promoId;
		this.productId = productId;
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
