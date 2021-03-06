package com.ordermaster.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.orderdetail.model.OrderDetailVO;

@Entity
@Table(name="ORDER_MASTER")
public class OrderMasterVO implements Serializable {
	
	@Id
	@Column(name="ORDER_ID")
	@SequenceGenerator(name="SEQ_ORDER_ID", sequenceName="SEQ_ORDER_ID", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_ORDER_ID")
	private Integer orderId;
	
	@Column(name="ORDER_DATE")
	@CreationTimestamp
	private Date orderDate;
	
	@Column(name="MEM_PHONE")
	private String memPhone;
	
	@Column(name="RECIPIENT_NAME")
	private String recipientName;
	
	@Column(name="RECIPIENT_MOB_NUMBER")
	private String recipientMobNumber;
	
	@Column(name="RECIPIENT_TEL_NUMBER")
	private String recipientTelNumber;
	
	@Column(name="RECIPIENT_EMAIL")
	private String recipientEmail;
	
	@Column(name="BUSINESS_NUMBER")
	private String businessNumber;
	
	@Column(name="DELIVERY_METHOD")
	private Integer deliveryMethod;
	
	@Column(name="DELIVERY_ADDRESS")
	private String deliveryAddress;
	
	@Column(name="ORDER_MEMO")
	private String orderMemo;
	
	@Column(name="INVOICE_PRICE")
	private String invoicePrice;
	
	@Column(name="INVOICE_PAID_DATE")
	@CreationTimestamp
	private Date invoicePaidDate;
	
	@Column(name="DELIVERY_TIME")
	@CreationTimestamp
	private Date deliveryTime;
	
	@Column(name="ORDER_STATUS")
	private Integer orderStatus;
	
	@OneToMany(mappedBy="orderMasterVO", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<OrderDetailVO> orderDetailVOList = new HashSet<OrderDetailVO>();
	
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
	
	public void setOrderDetailVOList(Set<OrderDetailVO> orderDetailVOList) {
		this.orderDetailVOList = orderDetailVOList;
	}
	
	public Set<OrderDetailVO> getOrderDetailVOList() {
		return orderDetailVOList;
	}
	
}
