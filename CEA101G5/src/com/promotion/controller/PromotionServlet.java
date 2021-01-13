package com.promotion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.*;
import com.promotiondetail.model.PromotionDetailService;
import com.promotiondetail.model.PromotionDetailVO;

public class PromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PromotionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("queryByProductId".equals(action)) {
			String productId = req.getParameter("productId");
			
			ProductService productService = new ProductService();
			ProductVO productVO = productService.getProductById(productId);
			
			Gson gson = new Gson();
			String productVOToJson = gson.toJson(productVO);
			PrintWriter out = res.getWriter();
			out.write(productVOToJson);
		}
		
		if ("addToPromo".equals(action)) {
			String productId = req.getParameter("productId");
			int productPromoPrice = Integer.parseInt(req.getParameter("productPromoPrice"));
			
			PromotionDetailService promotionDetailService = new PromotionDetailService();
			promotionDetailService.addPromotionDetail(1, productId, productPromoPrice, 100);
			
			ProductService productService = new ProductService();
			ProductVO productVO = productService.getProductById(productId);
			productVO.setProductPromoPrice(productPromoPrice);
			
			
			Gson gson = new Gson();
			String productVOToJson = gson.toJson(productVO);
			PrintWriter out = res.getWriter();
			out.write(productVOToJson);
		}
		
		if ("removeFromPromo".equals(action)) {
			String productId = req.getParameter("productId");
			
			PromotionDetailService promotionDetailService = new PromotionDetailService();
			promotionDetailService.deletePromotionDetail(1, productId);
		}
		
	}

}



















