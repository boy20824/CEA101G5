package com.productqa.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productqa.model.ProductQAService;

public class ProductQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductQAServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("submitQA".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String productId = req.getParameter("productId");
			String memPhone = "0921842854";
			String productQues = req.getParameter("productQues").trim();
			String productReply = "";
			
			if (productQues.length() == 0) {
				errorMsgs.add("½Ð¤Å¿é¤JªÅ¥Õ¸ê®Æ");
			}
			
			if (!errorMsgs.isEmpty()) {
				String url = "/shop/product.do?action=displayProductByUrl&productId=" + productId;
				RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
				requestDispatcher.forward(req, res);
				return;
			}
			
		ProductQAService productQAService = new ProductQAService();
		productQAService.addQA(productId, memPhone, productQues, productReply);
		
		String url = req.getContextPath() + "/shop/product.do?action=displayProductByUrl&productId=" + productId;
		res.sendRedirect(url);
			
		}
	}

}
