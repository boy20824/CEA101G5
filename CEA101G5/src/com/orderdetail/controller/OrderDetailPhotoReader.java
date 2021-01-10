package com.orderdetail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.*;

public class OrderDetailPhotoReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderDetailPhotoReader() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		String productId = req.getParameter("productId");
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		
		OrderDetailService orderDetailService = new OrderDetailService();
		OrderDetailVO orderDetailVO = orderDetailService.getOrderDetail(orderId, productId);
		
		byte[] photoByteArr = orderDetailVO.getProductReviewPhoto();
		out.write(photoByteArr);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
