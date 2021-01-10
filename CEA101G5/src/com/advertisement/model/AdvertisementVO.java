package com.advertisement.model;

import java.io.Serializable;
import java.sql.Date;

public class AdvertisementVO implements Serializable {
	private Integer adId;
	private String productId;
	private String adTitle;
	private String adContentTxt;
	private byte[] adContentImg;
	private Date adStartDate;
	private Date adEndDate;
	private Integer adStatus;
	private Date adTs;
	
	public Integer getAdId() {
		return adId;
	}
	
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getAdTitle() {
		return adTitle;
	}
	
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	
	public String getAdContentTxt() {
		return adContentTxt;
	}
	
	public void setAdContentTxt(String adContentTxt) {
		this.adContentTxt = adContentTxt;
	}
	
	public byte[] getAdContentImg() {
		return adContentImg;
	}
	
	public void setAdContentImg(byte[] adContentImg) {
		this.adContentImg = adContentImg;
	}
	
	public Date getAdStartDate() {
		return adStartDate;
	}
	
	public void setAdStartDate(Date adStartDate) {
		this.adStartDate = adStartDate;
	}
	
	public Date getAdEndDate() {
		return adEndDate;
	}
	
	public void setAdEndDate(Date adEndDate) {
		this.adEndDate = adEndDate;
	}
	
	public Integer getAdStatus() {
		return adStatus;
	}
	
	public void setAdStatus(Integer adStatus) {
		this.adStatus = adStatus;
	}
	
	public Date getAdTs() {
		return adTs;
	}
	
	public void setAdTs(Date adTs) {
		this.adTs = adTs;
	}
	
	
}
