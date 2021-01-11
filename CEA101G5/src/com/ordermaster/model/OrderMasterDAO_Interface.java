package com.ordermaster.model;

import java.util.List;

import com.product.model.ProductVO;

public interface OrderMasterDAO_Interface {
	public void insert(OrderMasterVO orderMasterVO);
	public void update(OrderMasterVO orderMasterVO);
	public void delete(Integer orderId);
	public OrderMasterVO getOne(Integer orderId);
	public List<OrderMasterVO> getAll();
	
	public void insertWithOrderDetail(OrderMasterVO orderMasterVO, List<ProductVO> list);
	public void updateOM(Integer orderStatus,Integer orderId);
	public List<OrderMasterVO> getAllByMemPhone(String memPhone);
}
