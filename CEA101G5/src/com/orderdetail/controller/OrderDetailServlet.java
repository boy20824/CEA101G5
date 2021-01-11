package com.orderdetail.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.*;
import com.product.model.ProductService;

public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("updateodr".equals(action)) {
			String productId = req.getParameter("productId");
			Integer orderId = new Integer (req.getParameter("orderId"));
			Integer productReviewStatus = new Integer (req.getParameter("productReviewStatus"));
			
			//因為原本的update要給他一個VO  所以所有屬性都要set才會過
			OrderDetailService odSvc = new OrderDetailService();
			odSvc.updateReview(productReviewStatus,orderId, productId);
			
			String searchyn = req.getParameter("searchyn");
			if ("yes".equals(searchyn)) {
				String url = "/back-end/shopProductReviewListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			}
			else {
				String url = "/back-end/shopProductReviewListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			}
			
			
		}
	}
}
