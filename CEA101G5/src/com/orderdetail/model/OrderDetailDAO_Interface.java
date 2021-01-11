package com.orderdetail.model;

import java.sql.Connection;
import java.util.List;

import com.product.model.ProductVO;

public interface OrderDetailDAO_Interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public void delete(Integer orderId);
	public OrderDetailVO getOne(Integer orderid, String productId);
	public List<OrderDetailVO> getAll(Integer orderId);
	
	public List<OrderDetailVO> getReviewByProductId(String productId);
	public void insert(ProductVO productVO, Connection con);
	
	public void updateReview(Integer productReviewStatus,Integer orderId, String productId);
	public List<OrderDetailVO> getReviewById(String productId);
	public List<OrderDetailVO> getAllReview();
}
