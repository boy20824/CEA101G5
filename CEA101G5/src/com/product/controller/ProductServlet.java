package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.*;
import com.product.model.*;
import com.productphoto.model.*;
import com.productqa.model.*;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// Display Products By Keyword
		if ("searchByKeyword".equals(action)) {
			String keyword = req.getParameter("keyword");
			ProductService productService = new ProductService();
			List<ProductVO> productVOList = productService.getAllByKeywordSearch(keyword);
			
			req.setAttribute("productVOList", productVOList);
			String url = "/front-end/shopProductListing.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}
		
		// Sort Products By Newest
		Boolean sortByNewestStatus = false;
		
		if ("sortByNewest".equals(action)) {
			if (sortByNewestStatus == false) {
				ProductService productService = new ProductService();
				List<ProductVO> productVOList = productService.getAllProducts();
				Collections.reverse(productVOList);
				
				req.setAttribute("productVOList", productVOList);
				sortByNewestStatus = true;
			} else {
				ProductService productService = new ProductService();
				List<ProductVO> productVOList = productService.getAllProducts();
				
				req.setAttribute("productVOList", productVOList);
				sortByNewestStatus = false;
			}
			
			String url = "/front-end/shopProductListing.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}
		
		// Sort Products By Bestsellers
		if ("sortByBestSelling".equals(action)) {
			ProductService productService = new ProductService();
			List<ProductVO> productVOList = productService.getAllProductsByQtySold();
			
			req.setAttribute("productVOList", productVOList);
			String url = "/front-end/shopProductListing.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}
		
		// Sort Products By Category
		if ("sortByCategory".equals(action)) {
			Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
			ProductService productService = new ProductService();
			List<ProductVO> productVOList = productService.getAllProductsByCategory(categoryId);
			
			req.setAttribute("productVOList", productVOList);
			String url = "/front-end/shopProductListing.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}

		// Sort Products By Prices (Lowest <> Highest)
		if ("sortByPrice".equals(action)) {
			String orderBy = req.getParameter("orderBy");
			List<ProductVO> productVOList = null;
			
			ProductService productService = new ProductService();
			if ("lth".equals(orderBy)) {
				productVOList = productService.getAllByProductPriceLTH();
			}
			if ("htl".equals(orderBy)) {
				productVOList = productService.getAllByProductPriceHTL();
			}
			
			req.setAttribute("productVOList", productVOList);
			String url = "/front-end/shopProductListing.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}
		
		// Display Product By URL
		if ("displayProductByUrl".equals(action)) {
			String productId = req.getParameter("productId");
			
			ProductService productService = new ProductService();
			ProductVO productVO = productService.getProductById(productId);
			
			ProductPhotoService productPhotoService = new ProductPhotoService();
			List<ProductPhotoVO> productPhotoVOList = productPhotoService.getAllProductPhotoById(productId);
			
			ProductQAService productQAService = new ProductQAService();
			List<ProductQAVO> productQAVOList = productQAService.getAllQAByProductId(productId);
			
			OrderDetailService orderDetailService = new OrderDetailService();
			List<OrderDetailVO> orderDetailVOList = orderDetailService.getAllReviewByProductId(productId);
			
			List<OrderDetailVO> orderDetailVOListNumOfReviews = new ArrayList<OrderDetailVO>();
			for (OrderDetailVO orderDetailVO : orderDetailVOList) {
				if (orderDetailVO.getProductReview() != null) {
					orderDetailVOListNumOfReviews.add(orderDetailVO);
				}
			}
			
			req.setAttribute("productVO", productVO);
			req.setAttribute("productPhotoVOList", productPhotoVOList);
			req.setAttribute("productQAVOList", productQAVOList);
			req.setAttribute("orderDetailVOList", orderDetailVOList);
			req.setAttribute("orderDetailVOListNumOfReviews", orderDetailVOListNumOfReviews);
			
			String url = "/front-end/shopProduct.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
			requestDispatcher.forward(req, res);
		}
		
		

	}

}
