package com.empauthcategory.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.empauthcategory.model.EmpAuthCategoryService;
import com.empauthcategory.model.EmpAuthCategoryVO;


@WebServlet("/EmpAuthCategoryServlet")
public class EmpAuthCategoryServlet extends HttpServlet {
       

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_EmpAuthCategory_page.jsp的請求
			
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("auth_no");
			if (str == null || (str.trim()).length() == 0) {// str=null 防呆除錯用，防止請求參數打錯
				errorMsgs.add("請輸入權限編號");				//當getParameter打錯時比較好除錯
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauthcategory/select_EmpAuthCategory_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			String auth_no = null;
			try {
				auth_no = new String(str);
			} catch (Exception e) {
				errorMsgs.add("權限編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauthcategory/select_EmpAuthCategory_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			EmpAuthCategoryService eacSvc = new EmpAuthCategoryService();
			EmpAuthCategoryVO eacVO = eacSvc.getOneEmpAuthCategory(auth_no);
			if (eacVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauthcategory/select_EmpAuthCategory_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("eacVO", eacVO); // 資料庫取出的empVO物件,存入req
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/empauthcategory/listOneEmpAuthCategory.jsp"); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/empauthcategory/select_EmpAuthCategory_page.jsp");
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
			String auth_no =req.getParameter("auth_no");
			
			/***************************2.開始查詢資料****************************************/
			EmpAuthCategoryService eacSvc = new EmpAuthCategoryService();
			EmpAuthCategoryVO eacVO = eacSvc.getOneEmpAuthCategory(auth_no);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("eacVO", eacVO);         // 資料庫取出的empVO物件,存入req
			String url = "/back-end/empauthcategory/update_empauthcategory_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_empauthcategory_input.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/empauthcategory/listAllEmpAuthCategory.jsp");
			failureView.forward(req, res);
		}
	}
	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		EmpAuthCategoryVO eacVO = new EmpAuthCategoryVO();
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String auth_no =(req.getParameter("auth_no").trim());
			
			String auth_name = req.getParameter("auth_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			if (auth_name == null || auth_name.trim().length() == 0) {
				errorMsgs.add("權限名稱: 請勿空白");
			} else if(!auth_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("權限名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
            }
			
			
			
			
			eacVO.setAuth_no(auth_no);
			eacVO.setAuth_name(auth_name);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("eacVO", eacVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauthcategory/update_empauthcategory_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			EmpAuthCategoryService eacSvc = new EmpAuthCategoryService();
			eacVO = eacSvc.updateEmpAuthCategory(auth_no,auth_name);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("eacVO", eacVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/empauthcategory/listOneEmpAuthCategory.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			e.printStackTrace();
			req.setAttribute("eacVO", eacVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/empauthcategory/update_empauthcategory_input.jsp");
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
			String auth_name = req.getParameter("auth_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			if (auth_name == null || auth_name.trim().length() == 0) {
				errorMsgs.add("權限名稱: 請勿空白");
			} else if(!auth_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("權限名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
            }
			
			String auth_no = req.getParameter("auth_no").trim();
			if (auth_no == null || auth_no.trim().length() == 0) {
				errorMsgs.add("權限編號請勿空白");
			}
					
			EmpAuthCategoryVO eacVO = new EmpAuthCategoryVO();
			eacVO.setAuth_no(auth_no);
			eacVO.setAuth_name(auth_name);	
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("eacVO", eacVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauthcategory/addEmpAuthCategory.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			EmpAuthCategoryService eacSvc = new EmpAuthCategoryService();
			eacVO = eacSvc.addEmpAuthCategory(auth_no,auth_name);
			
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back-end/empauthcategory/listAllEmpAuthCategory.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);				
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/empauthcategory/addEmpAuthCategory.jsp");
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
			String auth_no =req.getParameter("auth_no");
			
			/***************************2.開始刪除資料***************************************/
			EmpAuthCategoryService eacSvc = new EmpAuthCategoryService();
			eacSvc.deleteEmpAuthCategory(auth_no);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/back-end/empauthcategory/listAllEmpAuthCategory.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/empauthcategory/listAllEmpAuthCategory.jsp");
			failureView.forward(req, res);
		}
		}
    }
}
		
//	}

//}
