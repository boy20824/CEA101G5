package com.promotion.model;

import java.sql.Date;

public class PromotionVO {
	private Integer promoId;
	private String promoName;
	private byte[] promoImg;
	private Date promoStartDate;
	private Date promoEndDate;
	private Integer promoStatus;
	
	public PromotionVO() {
	}
	
	public Integer getPromoId() {
		return promoId;
	}
	
	public void setPromoId(Integer promoId) {
		this.promoId = promoId;
	}
	
	public String getPromoName() {
		return promoName;
	}
	
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	
	public byte[] getPromoImg() {
		return promoImg;
	}
	
	public void setPromoImg(byte[] promoImg) {
		this.promoImg = promoImg;
	}
	
	public Date getPromoStartDate() {
		return promoStartDate;
	}
	
	public void setPromoStartDate(Date promoStartDate) {
		this.promoStartDate = promoStartDate;
	}
	
	public Date getPromoEndDate() {
		return promoEndDate;
	}
	
	public void setPromoEndDate(Date promoEndDate) {
		this.promoEndDate = promoEndDate;
	}
	
	public Integer getPromoStatus() {
		return promoStatus;
	}
	
	public void setPromoStatus(Integer promoStatus) {
		this.promoStatus = promoStatus;
	}

}
