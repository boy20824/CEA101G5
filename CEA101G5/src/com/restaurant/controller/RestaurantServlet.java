package com.restaurant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.restaurant.model.RestaurantService;
import com.restaurant.model.RestaurantVO;
import com.restaurantpicture.model.RestaurantPictureService;
import com.restaurantpicture.model.RestaurantPictureVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String storeId = req.getParameter("storeId");
				String memPhone = req.getParameter("memPhone");
				String storeChar = req.getParameter("storeChar");
				String storeInfo = req.getParameter("storeInfo");
				String storeName = req.getParameter("storeName");
				String storePhone = req.getParameter("storePhone");
				String storeAddress = req.getParameter("storeAddress");
				Integer storeStatus = new Integer(req.getParameter("storeStatus"));
				Integer storeFinalReservDate = new Integer(req.getParameter("storeFinalReservDate"));
				Integer storeOrderCondition = new Integer(req.getParameter("storeOrderCondition"));
				Integer storeReservCondition = new Integer(req.getParameter("storeReservCondition"));
				Integer storeQueueCondition = new Integer(req.getParameter("storeQueueCondition"));
				Integer storeOrderWaitTime = new Integer(req.getParameter("storeOrderWaitTime"));

				java.sql.Timestamp storeOpenTime = java.sql.Timestamp.valueOf(req.getParameter("storeOpenTime"));
				java.sql.Timestamp storeCloseTime = java.sql.Timestamp.valueOf(req.getParameter("storeCloseTime"));
				java.sql.Timestamp storeStartOrderDate = java.sql.Timestamp
						.valueOf(req.getParameter("storeStartOrderDate"));
				java.sql.Timestamp storeEndOrderDate = java.sql.Timestamp
						.valueOf(req.getParameter("storeEndOrderDate"));

				Integer acceptGroups = new Integer(req.getParameter("acceptGroups"));
				Integer numOfGroups = new Integer(req.getParameter("numOfGroups"));
				Integer storePeopleTotal = new Integer(req.getParameter("storePeopleTotal"));
				Integer storeRatingTotal = new Integer(req.getParameter("storeRatingTotal"));

				RestaurantVO restVO = new RestaurantVO();

				restVO.setStoreId(storeId);
				restVO.setMemPhone(memPhone);
				restVO.setStoreChar(storeChar);
				restVO.setStoreInfo(storeInfo);
				restVO.setStoreName(storeName);
				restVO.setStorePhone(storePhone);
				restVO.setStoreAddress(storeAddress);
				restVO.setStoreStatus(storeStatus);
				restVO.setStoreFinalReservDate(storeFinalReservDate);
				restVO.setStoreOrderCondition(storeOrderCondition);
				restVO.setStoreReservCondition(storeReservCondition);
				restVO.setStoreQueueCondition(storeQueueCondition);
				restVO.setStoreOrderWaitTime(storeOrderWaitTime);
				restVO.setStoreOpenTime(storeOpenTime);
				restVO.setStoreCloseTime(storeCloseTime);
				restVO.setStoreStartOrderDate(storeStartOrderDate);
				restVO.setStoreEndOrderDate(storeEndOrderDate);
				restVO.setAcceptGroups(acceptGroups);
				restVO.setNumOfGroups(numOfGroups);
				restVO.setStorePeopleTotal(storePeopleTotal);
				restVO.setStoreRatingTotal(storeRatingTotal);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RestaurantService restSvc = new RestaurantService();
				restVO = restSvc.updateRestaurant(storeId, memPhone, storeChar, storeInfo, storeName, storePhone,
						storeAddress, storeStatus, storeFinalReservDate, storeOrderCondition, storeReservCondition,
						storeQueueCondition, storeOrderWaitTime, storeOpenTime, storeCloseTime, storeStartOrderDate,
						storeEndOrderDate, acceptGroups, numOfGroups, storePeopleTotal, storeRatingTotal);

				/*************************** 3.修改完成轉交成功畫面(Send the Success view) *************/
				req.setAttribute("restVO", restVO);
				String url = "/back-end/member/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("其他錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String storeId = req.getParameter("storeId");
				String memPhone = req.getParameter("memPhone");
				String storeChar = req.getParameter("storeChar");
				String storeInfo = req.getParameter("storeInfo");
				String storeName = req.getParameter("storeName");
				String storePhone = req.getParameter("storePhone");
				String storeAddress = req.getParameter("storeAddress");
				Integer storeStatus = new Integer(req.getParameter("storeStatus"));
				Integer storeFinalReservDate = new Integer(req.getParameter("storeFinalReservDate"));
				Integer storeOrderCondition = new Integer(req.getParameter("storeOrderCondition"));
				Integer storeReservCondition = new Integer(req.getParameter("storeReservCondition"));
				Integer storeQueueCondition = new Integer(req.getParameter("storeQueueCondition"));
				Integer storeOrderWaitTime = new Integer(req.getParameter("storeOrderWaitTime"));

				java.sql.Timestamp storeOpenTime = java.sql.Timestamp.valueOf(req.getParameter("storeOpenTime"));
				java.sql.Timestamp storeCloseTime = java.sql.Timestamp.valueOf(req.getParameter("storeCloseTime"));
				java.sql.Timestamp storeStartOrderDate = java.sql.Timestamp
						.valueOf(req.getParameter("storeStartOrderDate"));
				java.sql.Timestamp storeEndOrderDate = java.sql.Timestamp
						.valueOf(req.getParameter("storeEndOrderDate"));

				Integer acceptGroups = new Integer(req.getParameter("acceptGroups"));
				Integer numOfGroups = new Integer(req.getParameter("numOfGroups"));
				Integer storePeopleTotal = new Integer(req.getParameter("storePeopleTotal"));
				Integer storeRatingTotal = new Integer(req.getParameter("storeRatingTotal"));

				RestaurantVO restVO = new RestaurantVO();

				restVO.setStoreId(storeId);
				restVO.setMemPhone(memPhone);
				restVO.setStoreChar(storeChar);
				restVO.setStoreInfo(storeInfo);
				restVO.setStoreName(storeName);
				restVO.setStorePhone(storePhone);
				restVO.setStoreAddress(storeAddress);
				restVO.setStoreStatus(storeStatus);
				restVO.setStoreFinalReservDate(storeFinalReservDate);
				restVO.setStoreOrderCondition(storeOrderCondition);
				restVO.setStoreReservCondition(storeReservCondition);
				restVO.setStoreQueueCondition(storeQueueCondition);
				restVO.setStoreOrderWaitTime(storeOrderWaitTime);
				restVO.setStoreOpenTime(storeOpenTime);
				restVO.setStoreCloseTime(storeCloseTime);
				restVO.setStoreStartOrderDate(storeStartOrderDate);
				restVO.setStoreEndOrderDate(storeEndOrderDate);
				restVO.setAcceptGroups(acceptGroups);
				restVO.setNumOfGroups(numOfGroups);
				restVO.setStorePeopleTotal(storePeopleTotal);
				restVO.setStoreRatingTotal(storeRatingTotal);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/addMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RestaurantService restSvc = new RestaurantService();
				restVO = restSvc.addRestaurant(storeId, memPhone, storeChar, storeInfo, storeName, storePhone,
						storeAddress, storeStatus, storeFinalReservDate, storeOrderCondition, storeReservCondition,
						storeQueueCondition, storeOrderWaitTime, storeOpenTime, storeCloseTime, storeStartOrderDate,
						storeEndOrderDate, acceptGroups, numOfGroups, storePeopleTotal, storeRatingTotal);

				/*************************** 3.新增完成轉交成功畫面(Send the Success view) ***********/
				String url = "/front-customer-end/member/JoinSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("easyinsert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				System.out.println(req.getParameter("storeOpenTime"));

				String storeId = req.getParameter("storeId");
				String memPhone = req.getParameter("memPhone");
				String storeChar = req.getParameter("storeChar");
				String storeInfo = req.getParameter("storeInfo");
				String storeName = req.getParameter("storeName");
				String storePhone = req.getParameter("storePhone");
				String storeAddress = req.getParameter("storeAddress");
				Integer storeStatus = new Integer(req.getParameter("storeStatus"));
				java.sql.Timestamp storeOpenTime = java.sql.Timestamp.valueOf(req.getParameter("storeOpenTime"));
				java.sql.Timestamp storeCloseTime = java.sql.Timestamp.valueOf(req.getParameter("storeCloseTime"));

				RestaurantVO restVO = new RestaurantVO();

				restVO.setStoreId(storeId);
				restVO.setMemPhone(memPhone);
				restVO.setStoreChar(storeChar);
				restVO.setStoreInfo(storeInfo);
				restVO.setStoreName(storeName);
				restVO.setStorePhone(storePhone);
				restVO.setStoreAddress(storeAddress);
				restVO.setStoreStatus(storeStatus);
				restVO.setStoreOpenTime(storeOpenTime);
				restVO.setStoreCloseTime(storeCloseTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/addMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RestaurantService restSvc = new RestaurantService();
				restVO = restSvc.easyAddRestaurant(memPhone, storeChar, storeInfo, storeName, storePhone,
						storeAddress, storeStatus, storeOpenTime, storeCloseTime);

				/*************************** 3.新增完成轉交成功畫面(Send the Success view) ***********/
				String url = "/front-customer-end/member/JoinSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("easyinsertWithPic".equals(action)) { 
			
			System.out.println(action);
			Map<String, String> errorMsgs = new HashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
				
				String memPhone = req.getParameter("memPhone");
				String storeChar = req.getParameter("storeChar");
				String storeInfo = req.getParameter("storeInfo");
				String storeName = req.getParameter("storeName");
				String storePhone = req.getParameter("storePhone");
				String storeAddress = req.getParameter("storeAddress");
				Integer storeStatus = new Integer(req.getParameter("storeStatus"));
	
				java.sql.Timestamp storeOpenTime = java.sql.Timestamp.valueOf("1970-01-01 "+req.getParameter("storeOpenTime")+":00");
				java.sql.Timestamp storeCloseTime = java.sql.Timestamp.valueOf("1970-01-01 "+req.getParameter("storeCloseTime")+":00");
				
				System.out.println(storeOpenTime);
				
//				Part part = req.getPart("storePic");
//				InputStream in = part.getInputStream();
//				byte[] storePic = null;
//				if(part.getSize()==0) {
//					if(session.getAttribute("storePic") == null) {
//						errorMsgs.put("image","請上傳圖片");
//					}else {
//						storePic = (byte[])session.getAttribute("storePic");
//						in.read(storePic);
//						in.close();
//					}
//				}else {				
//					storePic = new byte[in.available()];
//					session.setAttribute("storePic", storePic);
//					in.read(storePic);
//					in.close();
//				}
				
				InputStream in = req.getPart("storePic").getInputStream();
				byte[] storePic = null;
				if (in.available() == 0) {
					if (session.getAttribute("storePic") == null) {
						errorMsgs.put("image","請上傳圖片");
					} else {
						storePic = (byte[]) session.getAttribute("storePic");
						in.read(storePic);
						in.close();
					}
				} else {
					storePic = new byte[in.available()];
					session.setAttribute("storePic", storePic);
					in.read(storePic);
					in.close();
				}
				
				RestaurantVO restVO = new RestaurantVO();
				RestaurantPictureVO restPicVO = new RestaurantPictureVO();
				
				restVO.setMemPhone(memPhone);
				restVO.setStoreChar(storeChar);
				restVO.setStoreInfo(storeInfo);
				restVO.setStoreName(storeName);
				restVO.setStorePhone(storePhone);
				restVO.setStoreAddress(storeAddress);
				restVO.setStoreStatus(storeStatus);
				restVO.setStoreOpenTime(storeOpenTime);
				restVO.setStoreCloseTime(storeCloseTime);
				restVO.setStoreCloseTime(storeCloseTime);
				
				restPicVO.setStorePicture(storePic);
				System.out.println(storePic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/restaurant/addrestaurant.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				RestaurantService restSvc = new RestaurantService();
				restSvc.easyAddRestaurantWithPic(restVO,restPicVO);
				
				/*************************** 3.新增完成轉交成功畫面(Send the Success view) ***********/
				String url = "/back-end/restaurant/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put(e.getMessage(), "抓到你了!");
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/restaurant/addrestaurant.jsp");
//				failureView.forward(req, res);
//			}
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
				String storeId = new String(req.getParameter("storeId"));

				/***************************
				 
				 ***************************************/
				RestaurantService restVO = new RestaurantService();
				restVO.deleteRestaurant(storeId);

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
		if ("listRestaurants_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession sessionStore = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)sessionStore.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					sessionStore.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				RestaurantService restSvc = new RestaurantService();
				List<RestaurantVO> list  = restSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listRestaurants_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-customer-end/restaurant/Browser.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-customer-end/front/front.jsp");
//				failureView.forward(req, res);
//			}
		}
	}
}
