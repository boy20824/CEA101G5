package com.productqa.model;

import java.io.Serializable;
import java.util.Date;

public class ProductQAVO implements Serializable {
	private Integer pqaId;
	private String productId;
	private String memPhone;
	private String productQues;
	private Date productQuesTstamp;
	private String productReply;
	private Date productReplyTstamp;
	
	public ProductQAVO() {
		super();
	}
	
	public void setPqaId(Integer pqaId) {
		this.pqaId = pqaId;
	}
	
	public Integer getPqaId() {
		return pqaId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	
	public String getMemPhone() {
		return memPhone;
	}
	
	public void setProductQues(String productQues) {
		this.productQues = productQues;
	}
	
	public String getProductQues() {
		return productQues;
	}
	
	public void setProductQuesTstamp(Date productQuesTstamp) {
		this.productQuesTstamp = productQuesTstamp;
	}
	
	public Date getProductQuesTstamp() {
		return productQuesTstamp;
	}
	
	public void setProductReply(String productReply) {
		this.productReply = productReply;
	}
	
	public String getProductReply() {
		return productReply;
	}
	
	public void setProductReplyTstamp(Date productReplyTstamp) {
		this.productReplyTstamp = productReplyTstamp;
	}
	
	public Date getProductReplyTstamp() {
		return productReplyTstamp;
	}

}
