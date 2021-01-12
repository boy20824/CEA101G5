package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.orderdetail.model.*;
import com.product.model.*;
import com.productphoto.model.*;
import com.productqa.model.*;
@MultipartConfig
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
		
		if ("updateProduct".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String productId = req.getParameter("productId");
			String productDescription = req.getParameter("productDescription");
			String productName=req.getParameter("productName");
			
			Integer productMSRP = null;
			try {
				productMSRP = new Integer(req.getParameter("productMSRP").trim());
				if (productMSRP != null && productMSRP <= 0) {
					errorMsgs.add("價格請輸入正整數");
				}
			}catch (Exception e) {
				errorMsgs.add("價格請輸入正整數!");
			}
			
			Integer categoryId = new Integer(req.getParameter("categoryId").trim());
			
			Integer productPrice = null;
			try {
				productPrice = new Integer(req.getParameter("productPrice").trim());
				if (productPrice != null && productPrice <= 0) {
					errorMsgs.add("價格請輸入正整數!");
				}
			}catch (Exception e) {
				errorMsgs.add("價格請輸入正整數!");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
				return;//蝔�葉�
			}
			Integer productStatus = new Integer(req.getParameter("productStatus").trim());
			
			//因為原本的update要給他一個VO  所以所有屬性都要set才會過
			ProductService pSvc = new ProductService();
			pSvc.testU(productId,productName ,productDescription , productMSRP, productPrice,categoryId,productStatus );
			
			String searchyn = req.getParameter("searchyn");
			if ("yes".equals(searchyn)) {
				req.setAttribute("turn","turn");
				String url = "/back-end/shopProductListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);
			}
			else {
				String url = "/back-end/shopProductListAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
			successView.forward(req, res);
			}
			
		}
		
		if ("add".equals(action)) { // 靘addEmp.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("ADD");

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String productName = req.getParameter("productName").trim();
				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
	            }
				//是否要設定不能與資料庫中的名稱重複?
				
				Integer productMSRP = null;
				try {
					productMSRP = new Integer(req.getParameter("productMSRP").trim());
					if (productMSRP <= 0) {
						errorMsgs.add("價格請填正整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("價格請填正整數");
				}
				
				
				Integer productPrice = null;
				try {
					productPrice = new Integer(req.getParameter("productPrice").trim());
					if (productPrice <= 0) {
						errorMsgs.add("價格請填正整數");
					}
					if (productPrice > productMSRP) {
						errorMsgs.add("售價請低於原價");
					}
					
				} catch (NumberFormatException e) {
					errorMsgs.add("價格請填正整數");
				}
				
				Integer categoryId = new Integer(req.getParameter("categoryId"));
				Integer productStatus = new Integer(req.getParameter("productStatus"));
				Integer productQtySold = new Integer(req.getParameter("productQtySold"));
				String productDescription = req.getParameter("productDescription");
				
				ProductVO pVO = new ProductVO();
				pVO.setProductName(productName);
				pVO.setProductMSRP(productMSRP);
				pVO.setProductPrice(productPrice);
				pVO.setCategoryId(categoryId);
				pVO.setProductStatus(productStatus);
				pVO.setProductQtySold(productQtySold);
				pVO.setProductDescription(productDescription);
				

				// Send the use back to the form, if there were errors 只要上面有任一錯誤
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pVO", pVO); // ���撓��撘隤斤�mpVO�隞�,銋�req(�見���������, addEmp����ORM���雿撓�����)
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shopProductAddProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				ProductService pSvc = new ProductService();
				pVO = pSvc.add(productName, productDescription, productMSRP, productPrice, productQtySold, categoryId, productStatus);
//				System.out.println("PRODUCTID:" + pVO.getProductId());
				String PID = "ENP";
				if (pSvc.getPID().length() == 4) {
					PID += pSvc.getPID();
				}
				else if (pSvc.getPID().length() == 3) {
					PID += "0" + pSvc.getPID();
				}
				else if (pSvc.getPID().length() == 2) {
					PID += "00" + pSvc.getPID();
				}
				else if (pSvc.getPID().length() == 1) {
					PID += "000" + pSvc.getPID();
				}
				System.out.println(PID);
				req.setAttribute("PID",PID);

				
				//隞乩�憓撘萄���
//				byte[] productPhoto = null;
//				Part part = req.getPart("productPhoto");
//				InputStream in = part.getInputStream();
//				if (in.available() == 0) {
//					errorMsgs.add("撠������");
//				} else {
//					byte[] buf = new byte[in.available()];
//					in.read(buf);
//					productPhoto = buf;
//				}
				
//				String productId = PID;
//				
//				ProductPhotoVO ppVO = new ProductPhotoVO();
//				ppVO.setProductId(productId);
//				ppVO.setProductPhoto(productPhoto);
//				
//				ProductPhotoService ppSvc = new ProductPhotoService();
//				ppVO = ppSvc.addProductPhoto(productId, productPhoto);
				
				
				//隞乩�憓�撐����
				String productId = PID;
				ProductPhotoVO ppVO = new ProductPhotoVO();
				ProductPhotoService ppSvc = new ProductPhotoService();
				
				byte[] productPhoto = null;
				List<Part> parts = (List<Part>) req.getParts();	//�������nput��镼�
				for(Part part:parts) {							//for each 韏唬���arts
					if (part.getContentType() != null) {		//input text���null
						if (part.getContentType().contains("image")) {	//��蕪�隞��銝����
							InputStream in = part.getInputStream();
							byte[] buf = new byte[in.available()];
							in.read(buf);
							productPhoto = buf;
							
							ppVO.setProductId(productId);
							ppVO.setProductPhoto(productPhoto);
							
							ppVO = ppSvc.addProductPhoto(productId, productPhoto);
						}
					}
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/shopProductListAll.jsp";
				System.out.println("新增成功");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("新增失敗");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shopProductListAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		

	}

}
