package com.productqa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_QA")
public class ProductQAVO implements Serializable {
	
	@Id
	@Column(name="PQA_ID")
	@SequenceGenerator(name="name2", sequenceName="SEQ_PQA_ID", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="name2")
	private Integer pqaId;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="MEM_PHONE")
	private String memPhone;
	
	@Column(name="PRODUCT_QUES")
	private String productQues;
	
	@Column(name="PRODUCT_QUES_TSTAMP")
	private Date productQuesTstamp;
	
	@Column(name="PRODUCT_REPLY")
	private String productReply;
	
	@Column(name="PRODUCT_REPLY_TSTAMP")
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
