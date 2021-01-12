package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.MemRedis;
import com.member.model.MemSMSSender;
import com.member.model.MemService;
import com.member.model.MemVO;
import com.restaurant.model.RestaurantService;
import com.restaurant.model.RestaurantVO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import redis.clients.jedis.Jedis;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class MemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String ACCOUNT_SID = "ACd807bf62b6cf033835cc5d6e7f0c8a52";
	public static final String AUTH_TOKEN = "5a5437b229b95f39202dec5be94ca6f6";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) { // 來自select_member_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("memPhone");
				String memPhoneReg = "^09[0-9]{8}$";
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員手機號碼");
				} else if (!str.trim().matches(memPhoneReg)) {// 格式需要使用正則表達式驗證^09[0-9]{8}$
					errorMsgs.add("手機格式錯誤");
				}
				// Send the use back to the form, if there were errors
				// 如果有錯誤，返回原本JSP
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_member_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				String memPhone = null;
				try {
					memPhone = new String(str);
				} catch (Exception e) {
					errorMsgs.add("手機號碼格式不正確");
				}
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memPhone);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_member_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/listOneMem.jsp"); // 送出資料至listOneMem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_member_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { //// 來自listAllMem.jsp的請求
			String comeFrom = req.getParameter("comeFrom");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memPhone = new String(req.getParameter("memPhone"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memPhone);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				if ("fromMemberPage".equals(comeFrom)) {
					String url = "/front-customer-end/member/memberPageUpdate.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
					successView.forward(req, res);
				} else {
					String url = "/back-end/member/update_member_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
					successView.forward(req, res);
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_Account_Check".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			/*************************** 1.接收請求參數 ****************************************/
			String memPhone = req.getParameter("memPhone");
			/*************************** 2.開始查詢資料 ****************************************/
			MemService memSvc = new MemService();
			List<MemVO> list = memSvc.getAll();
			PrintWriter out = res.getWriter();
			for (MemVO memVO : list) {
				if (memVO.getMemPhone().equals(memPhone) || memPhone.length() != 10) {
					out.write("false");
					break;
				}
			}
			out.flush();
			out.close();

		}

		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			String comeFrom = req.getParameter("comeFrom");
			System.out.println(action);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memPhone = req.getParameter("memPhone");
				System.out.println(req.getParameter("memPhone"));

				String memPwd = req.getParameter("memPwd").trim();
				String memPwdReg = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*$";
				// 密碼規則至少八位數，有一個大寫英文，至少要有一個英文字，
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgs.add("密碼不得空白");
				} else if (!memPwd.trim().matches(memPwdReg)) { // 密碼格式驗證
					errorMsgs.add("密碼設定不符合規定");
				}

				String memName = req.getParameter("memName").trim();
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("姓名不得空白");
				} else if (!memName.trim().matches(memNameReg)) { // 姓名格式驗證
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String memAddress = req.getParameter("memAddress").trim();
				String memAddressReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if (!memAddress.trim().matches(memAddressReg)) {
					errorMsgs.add("地址: 只能是中文和數字，且不能空白");
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^.+[\\x40]{1}.+[.]{1}.*$";
				if (!memEmail.trim().matches(memEmailReg)) { // 信箱格式驗證
					errorMsgs.add("Email格式錯誤");
				}

				String memNick = req.getParameter("memNick").trim();
				String memNickReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (!memNick.trim().matches(memNickReg)) {
					errorMsgs.add("暱稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer memLice = new Integer(req.getParameter("memLice").trim());

				Integer memCondition = new Integer(req.getParameter("memCondition").trim());
				;
				if (0 > memCondition || 6 < memCondition) {
					errorMsgs.add("會員狀態: 只能是0-5的數字");
				}

				String memAuth = req.getParameter("memAuth");
				if (memAuth == null || memAuth.trim().length() == 0) {
					errorMsgs.add("會員權限不得空白");
				}

				Integer memTotalRechar = new Integer(req.getParameter("memTotalRechar").trim());
				;
				if (0 > memTotalRechar) {
					errorMsgs.add("儲值金額必須是大於零整數");
				}

				Integer memSex = new Integer(req.getParameter("memSex"));
				System.out.println(req.getParameter("memSex"));
				String memIdentity = req.getParameter("memIdentity").trim();
				java.sql.Date memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());

				InputStream in = req.getPart("memPhoto").getInputStream();
				byte[] memPhoto = null;
				if (session.getAttribute("memPhoto") == null) {
					if (in.available() == 0) {
						MemService memSvc = new MemService();
						MemVO memVO = memSvc.getOneMem(memPhone);
						memPhoto = memVO.getMemPhoto();
					} else {
						memPhoto = new byte[in.available()];
						session.setAttribute("memPhoto", memPhoto);
						in.read(memPhoto);
						in.close();
					}
				} else {
					memPhoto = (byte[]) session.getAttribute("memPhoto");
					in.read(memPhoto);
					in.close();
				}

				System.out.println(memPhoto);

				String memCardNumber = req.getParameter("memCardNumber");
				String memCardHolder = req.getParameter("memCardHolder");
				String memCardExpirationDate = req.getParameter("memCardExpirationDate");
				String memCardCCV = req.getParameter("memCardCCV");

				MemVO memVO = new MemVO();
				memVO.setMemPhone(memPhone);
				memVO.setMemPwd(memPwd);
				memVO.setMemName(memName);
				memVO.setMemAddress(memAddress);
				memVO.setMemSex(memSex);
				memVO.setMemEmail(memEmail);
				memVO.setMemIdentity(memIdentity);
				memVO.setMemBirth(memBirth);
				memVO.setMemNick(memNick);
				memVO.setMemLice(memLice);
				memVO.setMemCondition(memCondition);
				memVO.setMemAuth(memAuth);
				memVO.setMemTotalRechar(memTotalRechar);
				memVO.setMemPhoto(memPhoto);
				memVO.setMemCardNumber(memCardNumber);
				memVO.setMemCardHolder(memCardHolder);
				memVO.setMemCardExpirationDate(memCardExpirationDate);
				memVO.setMemCardCCV(memCardCCV);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMem(memPhone, memPwd, memName, memAddress, memSex, memEmail, memIdentity, memBirth,
						memNick, memLice, memCondition, memAuth, memTotalRechar, memPhoto, memCardNumber, memCardHolder,
						memCardExpirationDate, memCardCCV);
				session.removeAttribute("memPhoto");

				/*************************** 3.修改完成轉交成功畫面(Send the Success view) *************/
				req.setAttribute("memVO", memVO);
				if ("fromMemUpdate".equals(comeFrom)) {
					String url = "/front-customer-end/member/memberPage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					MemVO memLogin = memSvc.getOneMem(memPhone);
					session.setAttribute("memLogin", memLogin);
					successView.forward(req, res);
				} else {
					String url = "/back-end/member/listAllMem.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

				/*************************** 其他錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("其他錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateMemLice".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memPhone = req.getParameter("memPhone");
				Integer memLice = new Integer(req.getParameter("memLice").trim());
				Integer memCondition = new Integer(req.getParameter("memCondition").trim());

				MemVO memVO = new MemVO();
				memVO.setMemPhone(memPhone);
				memVO.setMemLice(memLice);
				memVO.setMemCondition(memCondition);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-custom-end/restaurant/application.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMemLice(memPhone, memLice, memCondition);

				/*************************** 3.修改完成轉交成功畫面(Send the Success view) *************/
				req.setAttribute("memVO", memVO);
				String url = "/front-customer-end/restaurant/alreadyApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("其他錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/restaurant/application.jsp");
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
				String memPhone = req.getParameter("memPhone");
				String memPhoneReg = "^09[0-9]{8}$";
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("會員手機號碼不得空白");
				} else if (!memPhone.trim().matches(memPhoneReg)) { // 手機號碼格式驗證
					errorMsgs.add("手機號碼格式錯誤");
				}

				String memPwd = req.getParameter("memPwd").trim();
				String memPwdReg = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*$";
				// 密碼規則至少八位數，有一個大寫英文，至少要有一個英文字，
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgs.add("密碼不得空白");
				} else if (!memPwd.trim().matches(memPwdReg)) { // 密碼格式驗證
					errorMsgs.add("密碼設定不符合規定");
				}

				String memName = req.getParameter("memName").trim();
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("姓名不得空白");
				} else if (!memName.trim().matches(memNameReg)) { // 姓名格式驗證
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String memAddress = req.getParameter("memAddress").trim();
				String memAddressReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if (!memAddress.trim().matches(memAddressReg)) { // 地址可以為空白。地址格式驗證
					errorMsgs.add("地址: 只能是中文和數字");
				}

				Integer memSex = null;
				if (req.getParameter("memSex") == null) {
					errorMsgs.add("請選擇性別");
				} else {
					memSex = new Integer(req.getParameter("memSex").trim());
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^.+[\\x40]{1}.+[.]{1}.*$";
				if (!memEmail.trim().matches(memEmailReg)) { // 信箱可以為空白。信箱格式驗證
					errorMsgs.add("Email格式錯誤");
				}

				String memIdentity = req.getParameter("memIdentity").trim();
				if (memIdentity == null || memIdentity.trim().length() == 0) {
					errorMsgs.add("身分證字號格式錯誤");
				}

				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日日期!");
				}

				String memNick = req.getParameter("memNick").trim();
				String memNickReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (!memNick.trim().matches(memNickReg)) {
					errorMsgs.add("暱稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				InputStream in = req.getPart("memPhoto").getInputStream();
				byte[] memPhoto = null;
				if (in.available() == 0) {
					if (session.getAttribute("memPhoto") == null) {
						errorMsgs.add("頭相未選擇上傳圖片");
					} else {
						memPhoto = (byte[]) session.getAttribute("memPhoto");
						in.read(memPhoto);
						in.close();
					}
				} else {
					memPhoto = new byte[in.available()];
					session.setAttribute("memPhoto", memPhoto);
					in.read(memPhoto);
					in.close();
				}

				Integer memLice = new Integer(req.getParameter("memLice"));
				String memCardNumber = req.getParameter("memCardNumber");
				String memCardHolder = req.getParameter("memCardHolder");
				String memCardExpirationDate = req.getParameter("memCardExpirationDate");
				String memCardCCV = req.getParameter("memCardCCV");

				MemVO memVO = new MemVO();
				memVO.setMemPhone(memPhone);
				memVO.setMemPwd(memPwd);
				memVO.setMemName(memName);
				memVO.setMemAddress(memAddress);
				memVO.setMemSex(memSex);
				memVO.setMemEmail(memEmail);
				memVO.setMemIdentity(memIdentity);
				memVO.setMemBirth(memBirth);
				memVO.setMemNick(memNick);
				memVO.setMemLice(memLice);
				memVO.setMemPhoto(memPhoto);
				memVO.setMemCardNumber(memCardNumber);
				memVO.setMemCardHolder(memCardHolder);
				memVO.setMemCardExpirationDate(memCardExpirationDate);
				memVO.setMemCardCCV(memCardCCV);

				// 判斷帳號是否有重複
				MemService memSvctest = new MemService();
				List<MemVO> listall = memSvctest.getAll();
				for (MemVO memVOList : listall) {
					if (memVOList.getMemPhone().equals(memPhone)) {
						errorMsgs.add("手機號碼已被註冊，請重新輸入");
					}
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/addMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.addMem(memPhone, memPwd, memName, memAddress, memSex, memEmail, memIdentity, memBirth,
						memNick, memLice, memPhoto, memCardNumber, memCardHolder, memCardExpirationDate, memCardCCV);
				session.removeAttribute("memPhoto");

				/*************************** 3.新增完成轉交成功畫面(Send the Success view) ***********/
				/*************************** 寄送SMS驗證訊息 ***********/
//				MemRedis memRedis = new MemRedis();
//				String memAuthCode = memRedis.getAuthCode();
//				
//				System.out.println(memAuthCode);
//				
//				Jedis jedis = new Jedis("localhost", 6379);
//				jedis.auth("123456");
//				
//				jedis.set(memPhone,memAuthCode);
//				jedis.expire(memPhone, 300);
//				
//				System.out.println(memAuthCode);
//				String messageText = "HI！ " + memName + " 歡迎加入Enak，你要的美食都在這裡。" + "驗證碼：" + memAuthCode;
//				System.out.println(messageText);
//				MemSMSSender memSMS = new MemSMSSender();
//				memSMS.sendSMS(memPhone, messageText);
//				
//				MemVO memRegister = memSvc.getOneMem(memPhone);
//				session.setAttribute("memRegister", memRegister);
//				
//				jedis.close();
//				
//				String url = "/front-customer-end/member/SMSAuth.jsp";
				String url = "/front-customer-end/member/JoinSuccess.jsp";
				req.setAttribute("memVO", memVO); 
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/addMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("checkAuth".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memPhone = req.getParameter("memPhone");
				String memAuthCode = req.getParameter("memAuthCode");
				Integer memCondition = new Integer(req.getParameter("memCondition").trim());

				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				

				String testAuth = jedis.get(memPhone);
				if (!memAuthCode.trim().equals(testAuth)) {
					errorMsgs.add("驗證有誤，請重新確認");
				} else if (testAuth == null) {
					errorMsgs.add("驗證碼已逾時，請重新申請");
				}

				MemVO memVO = new MemVO();
				memVO.setMemPhone(memPhone);
				memVO.setMemCondition(memCondition);

				jedis.close();
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-custom-end/member/SMSAuth.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMemCondition(memPhone, memCondition);

				/*************************** 3.修改完成轉交成功畫面(Send the Success view) *************/
				req.setAttribute("memVO", memVO);
				String url = "/front-customer-end/member/JoinSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("其他錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-custom-end/member/SMSAuth.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action))

		{

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				
				 ***************************************/
				String memPhone = new String(req.getParameter("memPhone"));

				/***************************
				 
				 ***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(memPhone);

				/***************************
				*/
				String url = "/back-end/member/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//
				successView.forward(req, res);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println(req.getParameter("memPhone"));

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memPhone = req.getParameter("memPhone");
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("手機號碼不得為空");
				}

				String memPwd = req.getParameter("memPwd").trim();
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}

				if (memPhone.equals("admin") && memPwd.equals("admin")) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/EmpLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memLogin = memSvc.getOneMem(memPhone);

				if (memLogin == null || !memLogin.getMemPhone().equals(memPhone)) {
					errorMsgs.add("查無此帳號!");
				}

				if (memLogin == null || !memLogin.getMemPwd().equals(memPwd)) {
					errorMsgs.add("密碼錯誤!");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				RestaurantService restSvc = new RestaurantService();
				List<RestaurantVO> list = restSvc.getAll();

				RestaurantVO storeLogin = null;
				for (RestaurantVO RestaurantVO : list) {
					if (RestaurantVO.getMemPhone().equals(memPhone)) {
						String nowStoreId = RestaurantVO.getStoreId();
						storeLogin = restSvc.getOneRestaurant(nowStoreId);
					}
				}

				session.setAttribute("storeLogin", storeLogin);
				session.setAttribute("memLogin", memLogin);

				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {

				}

				res.sendRedirect(req.getContextPath() + "/front-customer-end/front/front.jsp");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("沒有此帳號!!!");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/MemLogin.jsp");
				failureView.forward(req, res);
			}
		}

		/*******************************************
		 * 會員登出
		 ************************************************/

		if ("logout".equals(action)) {

			try {
				session.invalidate();
				res.sendRedirect(req.getContextPath() + "/front-customer-end/front/front.jsp");
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		
//		Logging In & Out For Shop
		if ("shopLogin".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println(req.getParameter("memPhone"));
			
			String refererUrlRaw = req.getHeader("referer");
			String refererUrl = refererUrlRaw.substring(refererUrlRaw.indexOf("CEA101G5") + 8);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memPhone = req.getParameter("memPhone");
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("手機號碼不得為空");
				}

				String memPwd = req.getParameter("memPwd").trim();
				if (memPwd == null || memPwd.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}

				if (memPhone.equals("admin") && memPwd.equals("admin")) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/EmpLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memLogin = memSvc.getOneMem(memPhone);

				if (memLogin == null || !memLogin.getMemPhone().equals(memPhone)) {
					errorMsgs.add("查無此帳號!");
				}

				if (memLogin == null || !memLogin.getMemPwd().equals(memPwd)) {
					errorMsgs.add("密碼錯誤!");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/MemLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				RestaurantService restSvc = new RestaurantService();
				List<RestaurantVO> list = restSvc.getAll();

				RestaurantVO storeLogin = null;
				for (RestaurantVO RestaurantVO : list) {
					if (RestaurantVO.getMemPhone().equals(memPhone)) {
						String nowStoreId = RestaurantVO.getStoreId();
						storeLogin = restSvc.getOneRestaurant(nowStoreId);
					}
				}

				session.setAttribute("storeLogin", storeLogin);
				session.setAttribute("memLogin", memLogin);

				res.sendRedirect(req.getContextPath() + refererUrl);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("沒有此帳號!!!");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/MemLogin.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("shopLogout".equals(action)) {
			
			try {
				session.invalidate();
				res.sendRedirect(req.getContextPath() + "/front-end/shopMain.jsp");
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
//		Logging In & Out For Shop
		
	}
}
