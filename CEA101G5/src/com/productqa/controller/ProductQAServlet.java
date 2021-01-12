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
import com.productqa.model.ProductQAVO;

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
			String memPhone = req.getParameter("memPhone");
			String productQues = req.getParameter("productQues").trim();
			String productReply = "";
			
			if (productQues.length() == 0) {
				errorMsgs.add("請勿輸入空白！");
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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_emp_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("pqaId");
				if (str == null || (str.trim()).length() == 0) {// str=null 防呆除錯用，防止請求參數打錯
					errorMsgs.add("請輸入編號");				//當getParameter打錯時比較好除錯
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productqa/select_productqa_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer pqaId = null;
				try {
					pqaId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productqa/select_productqa_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductQAService productqaSvc = new ProductQAService();
				ProductQAVO productqaVO= productqaSvc.getQAById(pqaId);
				if (productqaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productqa/select_productqa_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productqaVO", productqaVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/productqa/listOneProductQA.jsp"); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productqa/select_productqa_page.jsp");
				failureView.forward(req, res);
				
			}
		}	
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer pqaId = new Integer(req.getParameter("pqaId"));
				/***************************2.開始查詢資料****************************************/
				ProductQAService productqaSvc = new ProductQAService();
				ProductQAVO productqaVO = productqaSvc.getQAById(pqaId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				                  
				req.setAttribute("productqaVO", productqaVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/productqa/update_productqa_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productqa/listAllProductQA.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer pqaId = new Integer(req.getParameter("pqaId").trim());
				
				String productId = req.getParameter("productId").trim();
				if (productId == null || productId.trim().length() == 0) {
					errorMsgs.add("商品編號請勿空白");
				}	
				
				String memPhone = req.getParameter("memPhone").trim();
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("會員請勿空白");
				}	
				

				String productQues = req.getParameter("productQues");
				if (productQues == null || productQues.trim().length() == 0) {
					errorMsgs.add("顧客回覆: 請勿空白");
	            }

				String productReply = req.getParameter("productReply");
				if (productReply == null || productReply.trim().length() == 0) {
					errorMsgs.add("商城回覆: 請勿空白");
	            }


				ProductQAVO productqaVO = new ProductQAVO();
				productqaVO.setPqaId(pqaId);
				productqaVO.setProductId(productId);
				productqaVO.setMemPhone(memPhone);
				productqaVO.setProductQues(productQues);
				productqaVO.setProductReply(productReply);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productqaVO", productqaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productqa/update_productqa_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductQAService produtcqaSvc = new ProductQAService();
				productqaVO = produtcqaSvc.updateQA(pqaId, productId, memPhone, productQues, productReply);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productqaVO", productqaVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/productqa/listOneProductQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/update_productqa_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String productId = req.getParameter("productId").trim();
					if (productId == null || productId.trim().length() == 0) {
						errorMsgs.add("商品編號請勿空白");
					}
					
					String memPhone = req.getParameter("memPhone").trim();
					if (memPhone == null || memPhone.trim().length() == 0) {
						errorMsgs.add("會員請勿空白");
					}
					
					
					String productQues = req.getParameter("productQues");
					if (productQues == null || productQues.trim().length() == 0) {
						errorMsgs.add("顧客提問: 請勿空白");
					} 
					
					String productReply = req.getParameter("productReply");
					

					ProductQAVO productqaVO = new ProductQAVO();
					productqaVO.setProductId(productId);
					productqaVO.setMemPhone(memPhone);
					productqaVO.setProductQues(productQues);
					productqaVO.setProductReply(productReply);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("productqaVO", productqaVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/productqa/addProductQA.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ProductQAService produtcqaSvc = new ProductQAService();
					productqaVO = produtcqaSvc.addQA(productId, memPhone, productQues, productReply);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/productqa/listAllProductQA.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/productqa/addProductQA.jsp");
					failureView.forward(req, res);
				}
			}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer pqaId = new Integer(req.getParameter("pqaId"));
				
				/***************************2.開始刪除資料***************************************/
				ProductQAService productqaSvc = new ProductQAService();
				productqaSvc.deletePqaId(pqaId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/productqa/listAllProductQA.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/productqa/listAllProductQA.jsp");
				failureView.forward(req, res);
			}
		}
	}

}