package com.latestnews.model;

import java.io.Serializable;
import java.util.Date;

public class LatestNewsVO implements Serializable {
	private Integer newsId;
	private String newsTitle;
	private String newsContentTxt;
	private byte[] newsContentImg;
	private Integer newsStatus;
	private Date newsTS;
	
	public Integer getNewsId() {
		return newsId;
	}
	
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	
	public String getNewsTitle() {
		return newsTitle;
	}
	
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public String getNewsContentTxt() {
		return newsContentTxt;
	}
	
	public void setNewsContentTxt(String newsContentTxt) {
		this.newsContentTxt = newsContentTxt;
	}
	
	public byte[] getNewsContentImg() {
		return newsContentImg;
	}
	
	public void setNewsContentImg(byte[] newsContentImg) {
		this.newsContentImg = newsContentImg;
	}
	
	public Integer getNewsStatus() {
		return newsStatus;
	}
	
	public void setNewsStatus(Integer newsStatus) {
		this.newsStatus = newsStatus;
	}
	
	public Date getNewsTS() {
		return newsTS;
	}
	
	public void setNewsTS(Date newsTS) {
		this.newsTS = newsTS;
	}
	
}
