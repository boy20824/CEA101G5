package com.emp.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.empauth.model.EmpAuthService;
import com.empauth.model.EmpAuthVO;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getEmpPic".equals(action)) {
			res.setContentType("image/gif, image/png");
			ServletOutputStream out = res.getOutputStream();
			try {
				String emp_id = req.getParameter("emp_id");
				EmpService empSvc = new EmpService();
				byte[] baos = empSvc.getOneEmp(emp_id).getEmp_image();
				out.write(baos);

			} catch (Exception e) {
				System.out.println("沒圖片");
			} finally {
				out.close();
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_emp_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {// str=null 防呆除錯用，防止請求參數打錯
					errorMsgs.add("請輸入員工編號"); // 當getParameter打錯時比較好除錯
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String emp_id = null;
				try {
					emp_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
			

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/emp/listOneEmp.jsp"); // 成功轉交
																											// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);

			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_id = req.getParameter("emp_id");

				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			EmpVO empVO = new EmpVO();
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String emp_id = (req.getParameter("emp_id").trim());

				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
				}

				String emp_accont = req.getParameter("emp_account").trim();
				if (emp_accont == null || emp_accont.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				String emp_pwd = null;
				try {
					emp_pwd = (req.getParameter("emp_pwd").trim());
				} catch (NumberFormatException e) {
//						pwd = 0;
					errorMsgs.add("請填入密碼.");
				}

				java.sql.Date emp_date = null;
				try {
					emp_date = java.sql.Date.valueOf(req.getParameter("emp_date").trim());
				} catch (IllegalArgumentException e) {
					emp_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer emp_status = null;
				try {
					emp_status = new Integer(req.getParameter("emp_status").trim());
				} catch (NumberFormatException e) {
					emp_status = 0;
					errorMsgs.add("請選擇員工狀態.");
				}
				InputStream in = req.getPart("emp_image").getInputStream();
				byte[] emp_image = null;
				if (session.getAttribute("emp_image") == null) {
					if (in.available() == 0) {
						EmpService empSvc = new EmpService();
						EmpVO memVO = empSvc.getOneEmp(emp_id);
						emp_image = memVO.getEmp_image();
					} else {
						emp_image = new byte[in.available()];
						session.setAttribute("emp_image", emp_image);
						in.read(emp_image);
						in.close();
					}
				} else {
					emp_image = (byte[]) session.getAttribute("emp_image");
					in.read(emp_image);
					in.close();
				}

				empVO.setEmp_id(emp_id);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_account(emp_accont);
				empVO.setEmp_password(emp_pwd);
				empVO.setEmp_date(emp_date);
				empVO.setEmp_status(emp_status);
				empVO.setEmp_image(emp_image);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_id, emp_accont, emp_pwd, emp_name, emp_date, emp_status, emp_image);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				req.setAttribute("empVO", empVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
				}

				String emp_account = req.getParameter("emp_account").trim();
				if (emp_account == null || emp_account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				String emp_pwd = null;
				try {
					emp_pwd = new String(req.getParameter("emp_pwd").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填入密碼.");
				}

				java.sql.Date emp_date = null;
				try {
					emp_date = java.sql.Date.valueOf(req.getParameter("emp_date").trim());
				} catch (IllegalArgumentException e) {
					emp_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				byte[] emp_image = null;
				Part part = req.getPart("emp_image");
				InputStream in = part.getInputStream();
				if (in.available() == 0) {
					errorMsgs.add("尚未選擇圖片");
				} else {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					emp_image = buf;
				}
				Integer emp_status = null;
				if (req.getParameter("emp_status") == null) {
					errorMsgs.add("請選擇員工在職狀態");
				} else {
					emp_status = new Integer(req.getParameter("emp_status").trim());
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmp_name(emp_name);
				empVO.setEmp_account(emp_account);
				empVO.setEmp_password(emp_pwd);
				empVO.setEmp_date(emp_date);
				empVO.setEmp_status(emp_status);
				empVO.setEmp_image(emp_image);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_name, emp_account, emp_pwd, emp_date, emp_status, emp_image);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String emp_id = req.getParameter("emp_id");

				/*************************** 2.開始刪除資料 ***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(emp_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);

			}
		}

		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String empAccount = req.getParameter("empAccount");
				if (empAccount == null || empAccount.trim().length() == 0) {
					errorMsgs.add("帳號號碼不得為空");
				}

				String empPwd = req.getParameter("empPwd").trim();
				if (empPwd == null || empPwd.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}


				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/EmpLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empLogin = empSvc.getOneAccount(empAccount);

				if (empLogin == null || !empLogin.getEmp_account().equals(empAccount)) {
					errorMsgs.add("查無此帳號!");
				}

				if (empLogin == null || !empLogin.getEmp_password().equals(empPwd)) {
					errorMsgs.add("密碼錯誤!");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/EmpLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("empLogin", empLogin);
				
				
				String empId = empSvc.getOneAccount(empAccount).getEmp_id();
				
				EmpAuthService empauthSvc = new EmpAuthService();
				
				EmpAuthVO empFeature = empauthSvc.getOneEmpAuth(empId);
				EmpAuthVO empShop = empauthSvc.getOneEmpAuth(empId);
				EmpAuthVO empStore = empauthSvc.getOneEmpAuth(empId);
				EmpAuthVO empMem = empauthSvc.getOneEmpAuth(empId);
				EmpAuthVO empEmp = empauthSvc.getOneEmpAuth(empId);
				
				List<EmpAuthVO> empauthList = empauthSvc.getAll();
				
				for(EmpAuthVO empAuthVO : empauthList) {
					if(empAuthVO.getEmp_id().equals(empId)) {
						if(empAuthVO.getAuth_no().equals(01)) {
							session.setAttribute("empFeature", empFeature);
						}
						if(empAuthVO.getAuth_no().equals(02)) {
							session.setAttribute("empShop", empShop);
						}
						if(empAuthVO.getAuth_no().equals(03)) {
							session.setAttribute("empStore", empStore);
						}
						if(empAuthVO.getAuth_no().equals(04)) {
							session.setAttribute("empMem", empMem);
						}
						if(empAuthVO.getAuth_no().equals(05)) {
							session.setAttribute("empEmp", empEmp);
				
						}
					}
				}
				
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
					ignored.printStackTrace();
				}

				res.sendRedirect(req.getContextPath() + "/back-end/emp/empfront.jsp");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("沒有此帳號!!!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/EmpLogin.jsp");
				failureView.forward(req, res);
			}
		}

		/*******************************************
		 * 會員登出
		 ************************************************/

		if ("logout".equals(action)) {

			try {
				session.invalidate();
				res.sendRedirect(req.getContextPath() + "/back-end/emp/EmpLogin.jsp");
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

	}

}
