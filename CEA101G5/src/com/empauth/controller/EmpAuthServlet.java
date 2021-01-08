package com.empauth.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empauth.model.EmpAuthService;
import com.empauth.model.EmpAuthVO;
import com.empauthcategory.model.EmpAuthCategoryService;
import com.empauthcategory.model.EmpAuthCategoryVO;


@WebServlet("/EmpAuthServlet")
public class EmpAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {// str=null 防呆除錯用，防止請求參數打錯
					errorMsgs.add("請輸入員工編號");				//當getParameter打錯時比較好除錯
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empauth/select_empauth_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_id = null;
				try {
					emp_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("權限編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empauth/select_empauth_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpAuthService eaSvc = new EmpAuthService();
				EmpAuthVO eaVO = eaSvc.getOneEmpAuth(emp_id);
				if (eaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empauth/select_empauth_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("eaVO", eaVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/empauth/listOneEmpAuth.jsp"); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauth/select_empauth_page.jsp");
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
				String emp_id =req.getParameter("emp_id");
				
				/***************************2.開始查詢資料****************************************/
				EmpAuthService eaSvc = new EmpAuthService();
				EmpAuthVO eaVO = eaSvc.getOneEmpAuth(emp_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("eaVO", eaVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/empauth/update_empauth_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_empauthcategory_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauth/listAllEmpAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			EmpAuthVO eaVO = new EmpAuthVO();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String emp_id =(req.getParameter("emp_id").trim());
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				}	
			
				String auth_no = req.getParameter("auth_no").trim();
				if (auth_no == null || auth_no.trim().length() == 0) {
					errorMsgs.add("權限編號請勿空白");
				}	
				
				
				eaVO.setEmp_id(emp_id);
				eaVO.setAuth_no(auth_no);
				
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eaVO", eaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empauth/update_empauth_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpAuthService eaSvc = new EmpAuthService();
				eaVO = eaSvc.updateEmpAuth(emp_id, auth_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("eaVO", eaVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/empauth/listOneEmpAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				req.setAttribute("eaVO", eaVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauth/update_empauth_input.jsp");
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
					String emp_id = req.getParameter("emp_id");
					if (emp_id == null || emp_id.trim().length() == 0) {
						errorMsgs.add("員工編號請勿空白");
					}	
					String auth_no = req.getParameter("auth_no").trim();
					if (auth_no == null || auth_no.trim().length() == 0) {
						errorMsgs.add("權限編號請勿空白");
					}
							
					EmpAuthVO eaVO = new EmpAuthVO();
					eaVO.setEmp_id(emp_id);
					eaVO.setAuth_no(auth_no);	
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("eaVO", eaVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/empauth/addEmpAuth.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					EmpAuthService eaSvc = new EmpAuthService();
					eaVO = eaSvc.addEmpAuth(emp_id,auth_no);
					
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/empauth/listAllEmpAuth.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/empauth/addEmpAuth.jsp");
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
				String emp_id =req.getParameter("emp_id");
				String auth_no =req.getParameter("auth_no");
				
				
				/***************************2.開始刪除資料***************************************/
				EmpAuthService eaSvc = new EmpAuthService();
				eaSvc.deleteEmpAuth(emp_id,auth_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/empauth/listAllEmpAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/empauth/listAllEmpAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
