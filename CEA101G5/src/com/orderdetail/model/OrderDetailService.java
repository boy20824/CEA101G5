package com.orderdetail.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class OrderDetailService {
	
	private OrderDetailDAO_Interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailJDBCDAO();
	}
	
	public OrderDetailVO addOrderDetail(Integer orderId, String productId, Integer productPrice, Integer quantity, String productReview, byte[] productReviewPhoto, Integer productReviewStatus) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setProductId(productId);
		orderDetailVO.setProductPrice(productPrice);
		orderDetailVO.setQuantity(quantity);
		orderDetailVO.setProductReview(productReview);
		orderDetailVO.setProductReviewPhoto(productReviewPhoto);
		orderDetailVO.setProductReviewStatus(productReviewStatus);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO updateOrderDetail(Integer orderId, String productId, Integer productPrice, Integer quantity, String productReview, byte[] productReviewPhoto, Integer productReviewStatus) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setProductId(productId);
		orderDetailVO.setProductPrice(productPrice);
		orderDetailVO.setQuantity(quantity);
		orderDetailVO.setProductReview(productReview);
		orderDetailVO.setProductReviewPhoto(productReviewPhoto);
		orderDetailVO.setProductReviewStatus(productReviewStatus);
		dao.update(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO getOrderDetail(Integer orderId, String productId) {
		return dao.getOne(orderId, productId);
	}
	
	public List<OrderDetailVO> getAllOrderDetail(Integer orderId) {
		return dao.getAll(orderId);
	}
	
	public List<OrderDetailVO> getAllReviewByProductId(String productId) {
		return dao.getReviewByProductId(productId);
	}
	
	public List<OrderDetailVO> getReviewById(String productId) {
		return dao.getReviewById(productId);
	}
	
	public List<OrderDetailVO> getAllReview() {
		return dao.getAllReview();
	}
	
	public void updateReview(Integer productReviewStatus,Integer orderId, String productId) {
		dao.updateReview(productReviewStatus,orderId, productId);
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		OrderDetailService orderDetailService = new OrderDetailService();
		
//		testing : addOrderDetail()
//		try {
//			byte[] productReviewPhoto = getPictureByteArray("/Users/jordan/desktop/cat.png");
//			orderDetailService.addOrderDetail(5, "ENP0009", 799, 4, "���յ���", productReviewPhoto, 1);
//			System.out.println("Statement Processed...");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		testing : updateOrderDetail()
//		try {
//			byte[] productReviewPhoto = getPictureByteArray("/Users/jordan/desktop/cat.png");
//			orderDetailService.updateOrderDetail(5, "ENP0001", 1000, 1000, "���յ���XX", productReviewPhoto, 1);
//			System.out.println("Statement Processed...");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		testing : getOrderDetail()
//		OrderDetailVO orderDetailVO = orderDetailService.getOrderDetail(5, "ENP0001");
//		System.out.println("ORDER_ID: " + orderDetailVO.getOrderId() );
//		System.out.println("PRODUCT_ID: " + orderDetailVO.getProductId() );
//		System.out.println("PRODUCT_PRICE: " + orderDetailVO.getProductPrice());
//		System.out.println("QUANTITY: " + orderDetailVO.getQuantity());
//		System.out.println("PRODUCT_REVIEW: " + orderDetailVO.getProductReview());
//		System.out.println("PRODUCT_REVIEW_PHOTO: " + orderDetailVO.getProductReviewPhoto());
//		System.out.println("PRODUCT_REVIEW_TS: " + orderDetailVO.getProductReviewTS());
//		System.out.println("PRODUCT_REVIEW_STATUS: " +  orderDetailVO.getProductReviewStatus());
		
//		testing : getAllOrderDetail()
		List<OrderDetailVO> list = orderDetailService.getAllOrderDetail(4);
		for (OrderDetailVO orderDetailVO : list) {
			System.out.println("ORDER_ID: " + orderDetailVO.getOrderId() );
			System.out.println("PRODUCT_ID: " + orderDetailVO.getProductId() );
			System.out.println("PRODUCT_PRICE: " + orderDetailVO.getProductPrice());
			System.out.println("QUANTITY: " + orderDetailVO.getQuantity());
			System.out.println("PRODUCT_REVIEW: " + orderDetailVO.getProductReview());
			System.out.println("PRODUCT_REVIEW_PHOTO: " + orderDetailVO.getProductReviewPhoto());
			System.out.println("PRODUCT_REVIEW_TS: " + orderDetailVO.getProductReviewTS());
			System.out.println("PRODUCT_REVIEW_STATUS: " +  orderDetailVO.getProductReviewStatus());
			System.out.println("-----------------------------------");
		}
		
//		testing : getAllReviewByProductId()
//		List<OrderDetailVO> list = orderDetailService.getAllReviewByProductId("ENP0001");
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println("ORDER_ID: " + orderDetailVO.getOrderId() );
//			System.out.println("PRODUCT_ID: " + orderDetailVO.getProductId() );
//			System.out.println("PRODUCT_PRICE: " + orderDetailVO.getProductPrice());
//			System.out.println("QUANTITY: " + orderDetailVO.getQuantity());
//			System.out.println("PRODUCT_REVIEW: " + orderDetailVO.getProductReview());
//			System.out.println("PRODUCT_REVIEW_PHOTO: " + orderDetailVO.getProductReviewPhoto());
//			System.out.println("PRODUCT_REVIEW_TS: " + orderDetailVO.getProductReviewTS());
//			System.out.println("PRODUCT_REVIEW_STATUS: " +  orderDetailVO.getProductReviewStatus());
//			System.out.println("-----------------------------------");
//		}
	}
		
}
