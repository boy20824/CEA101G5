package com.ordermaster.model;

import java.util.List;

public class OrderMasterService {
	
	private OrderMasterDAO_Interface dao;
	
	public OrderMasterService() {
		dao = new OrderMasterJNDIDAO();
	}
	
	public OrderMasterVO addOrder(String memPhone, String recipientName, String recipientMobNumber, String recipientTelNumber, String recipientEmail, String businessNumber, Integer deliveryMethod, String deliveryAddress, String orderMemo, String invoicePrice, Integer orderStatus) {
		OrderMasterVO orderMasterVO = new OrderMasterVO();
		
		orderMasterVO.setMemPhone(memPhone);
		orderMasterVO.setRecipientName(recipientName);
		orderMasterVO.setRecipientMobNumber(recipientMobNumber);
		orderMasterVO.setRecipientTelNumber(recipientTelNumber);
		orderMasterVO.setRecipientEmail(recipientEmail);
		orderMasterVO.setBusinessNumber(businessNumber);
		orderMasterVO.setDeliveryMethod(deliveryMethod);
		orderMasterVO.setDeliveryAddress(deliveryAddress);
		orderMasterVO.setOrderMemo(orderMemo);
		orderMasterVO.setInvoicePrice(invoicePrice);
		orderMasterVO.setOrderStatus(orderStatus);
		dao.insert(orderMasterVO);
		
		return orderMasterVO;
	}
	
	public OrderMasterVO updateOrder(Integer orderId, String memPhone, String recipientName, String recipientMobNumber, String recipientTelNumber, String recipientEmail, String businessNumber, Integer deliveryMethod, String deliveryAddress, String orderMemo, String invoicePrice, Integer orderStatus) {
		OrderMasterVO orderMasterVO = new OrderMasterVO();
		
		orderMasterVO.setOrderId(orderId);
		orderMasterVO.setMemPhone(memPhone);
		orderMasterVO.setRecipientName(recipientName);
		orderMasterVO.setRecipientMobNumber(recipientMobNumber);
		orderMasterVO.setRecipientTelNumber(recipientTelNumber);
		orderMasterVO.setRecipientEmail(recipientEmail);
		orderMasterVO.setBusinessNumber(businessNumber);
		orderMasterVO.setDeliveryMethod(deliveryMethod);
		orderMasterVO.setDeliveryAddress(deliveryAddress);
		orderMasterVO.setOrderMemo(orderMemo);
		orderMasterVO.setInvoicePrice(invoicePrice);
		orderMasterVO.setOrderStatus(orderStatus);
		dao.update(orderMasterVO);
		
		return orderMasterVO;
	}
	
	public OrderMasterVO getOrderById(Integer orderId) {
		return dao.getOne(orderId);
	}
	
	public List<OrderMasterVO> getAllOrders() {
		return dao.getAll();
	}
	
	public void updateOM(Integer orderStatus,Integer orderId) {
		dao.updateOM(orderStatus,orderId);
	}
	
	public List<OrderMasterVO> getAllByMemPhone(String memPhone) {
		return dao.getAllByMemPhone(memPhone);
	}
		
	public static void main(String[] args) {
		OrderMasterService orderMasterService = new OrderMasterService();
		
//		testing : addOrder()
//		orderMasterService.addOrder("0921842850", "���իȤ�W��", "0988-8888888", "04-88888888", "test@testmail.com", "88888888", 0, "���զ���a�}", "�H�U�`�N�ƶ�", "299", 3);
//		System.out.println("Statement Processed...");

//		testing : updateOrder()
//		orderMasterService.updateOrder(6, "0921842850", "���իȤ�W��", "0988-8888888", "04-88888888", "test@testmail.com", "88888888", 0, "���զ���a�}", "�H�U�`�N�ƶ�", "299", 3);
//		System.out.println("Statement Processed...");
		
//		testing : getOrderById()
		OrderMasterVO orderMasterVO = orderMasterService.getOrderById(1);
		System.out.println("ORDER_ID: " + orderMasterVO.getOrderId());
		System.out.println("ORDER_DATE: " + orderMasterVO.getOrderDate());
		System.out.println("MEM_PHONE: " + orderMasterVO.getMemPhone());
		System.out.println("RECIPIENT_NAME: " + orderMasterVO.getRecipientName());
		System.out.println("RECIPIENT_MOB_NUMBER: " + orderMasterVO.getRecipientMobNumber());
		System.out.println("RECIPIENT_TEL_NUMBER: " + orderMasterVO.getRecipientTelNumber());
		System.out.println("RECIPIENT_EMAIL: " + orderMasterVO.getRecipientEmail());
		System.out.println("BUSINESS_NUMBER: " + orderMasterVO.getBusinessNumber());
		System.out.println("DELIVERY_METHOD: " + orderMasterVO.getDeliveryMethod());
		System.out.println("DELIVERY_ADDRESS: " + orderMasterVO.getDeliveryAddress());
		System.out.println("ORDER_MEMO: " + orderMasterVO.getOrderMemo());
		System.out.println("INVOICE_PRICE: " + orderMasterVO.getInvoicePrice());
		System.out.println("INVOICE_PAID_DATE: " + orderMasterVO.getInvoicePaidDate());
		System.out.println("DELIVERY_TIME: " + orderMasterVO.getDeliveryTime());
		System.out.println("ORDER_STATUS: " + orderMasterVO.getOrderStatus());		
		
//		testing : getAllOrders();
//		List<OrderMasterVO> list = orderMasterService.getAllOrders();
//		for (OrderMasterVO orderMasterVO : list) {
//			System.out.println("ORDER_ID: " + orderMasterVO.getOrderId());
//			System.out.println("ORDER_DATE: " + orderMasterVO.getOrderDate());
//			System.out.println("MEM_PHONE: " + orderMasterVO.getMemPhone());
//			System.out.println("RECIPIENT_NAME: " + orderMasterVO.getRecipientName());
//			System.out.println("RECIPIENT_MOB_NUMBER: " + orderMasterVO.getRecipientMobNumber());
//			System.out.println("RECIPIENT_TEL_NUMBER: " + orderMasterVO.getRecipientTelNumber());
//			System.out.println("RECIPIENT_EMAIL: " + orderMasterVO.getRecipientEmail());
//			System.out.println("BUSINESS_NUMBER: " + orderMasterVO.getBusinessNumber());
//			System.out.println("DELIVERY_METHOD: " + orderMasterVO.getDeliveryMethod());
//			System.out.println("DELIVERY_ADDRESS: " + orderMasterVO.getDeliveryAddress());
//			System.out.println("ORDER_MEMO: " + orderMasterVO.getOrderMemo());
//			System.out.println("INVOICE_PRICE: " + orderMasterVO.getInvoicePrice());
//			System.out.println("INVOICE_PAID_DATE: " + orderMasterVO.getInvoicePaidDate());
//			System.out.println("DELIVERY_TIME: " + orderMasterVO.getDeliveryTime());
//			System.out.println("ORDER_STATUS: " + orderMasterVO.getOrderStatus());		
//			System.out.println("-----------------------------------");
//		}
	}

}
