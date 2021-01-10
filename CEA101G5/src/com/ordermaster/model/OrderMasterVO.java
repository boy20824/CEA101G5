package com.ordermaster.model;

import java.io.Serializable;
import java.util.Date;

public class OrderMasterVO implements Serializable {
	private Integer orderId;
	private Date orderDate;
	private String memPhone;
	private String recipientName;
	private String recipientMobNumber;
	private String recipientTelNumber;
	private String recipientEmail;
	private String businessNumber;
	private Integer deliveryMethod;
	private String deliveryAddress;
	private String orderMemo;
	private String invoicePrice;
	private Date invoicePaidDate;
	private Date deliveryTime;
	private Integer orderStatus;
	
	public OrderMasterVO() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientMobNumber() {
		return recipientMobNumber;
	}

	public void setRecipientMobNumber(String recipientMobNumber) {
		this.recipientMobNumber = recipientMobNumber;
	}

	public String getRecipientTelNumber() {
		return recipientTelNumber;
	}

	public void setRecipientTelNumber(String recipientTelNumber) {
		this.recipientTelNumber = recipientTelNumber;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public Integer getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(Integer deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(String invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public Date getInvoicePaidDate() {
		return invoicePaidDate;
	}

	public void setInvoicePaidDate(Date invoicePaidDate) {
		this.invoicePaidDate = invoicePaidDate;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
