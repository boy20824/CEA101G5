package com.productcategory.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productcategory.model.*;

public class ProductCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProductCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String addCategory = req.getParameter("addCategory");
		
		if ("yes".equals(addCategory)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String categoryName = req.getParameter("categoryName").trim();
				if (categoryName == null || categoryName.trim().length() == 0) {
					errorMsgs.add("類別名稱: 請勿空白");
	            }
				//是否要設定不能與資料庫中的名稱重複?
				
				
				ProductCategoryVO pcVO = new ProductCategoryVO();
				pcVO.setCategoryName(categoryName);
				

				// Send the use back to the form, if there were errors 只要上面有任一錯誤
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pcVO", pcVO); // 含有輸入格式錯誤的empVO物件,也存入req(這樣才不用全部重打, addEmp那邊的FORM會抓到你輸入的值)
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shopProductAddProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductCategoryService pcSvc = new ProductCategoryService();
				pcVO = pcSvc.add(categoryName);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/shopProductAddProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("新增類別失敗");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shopProductAddProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
