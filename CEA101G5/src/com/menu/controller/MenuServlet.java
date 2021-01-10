package com.menu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.menu.model.MenuService;
import com.menu.model.MenuVO;

@WebServlet("/menu/MenuServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024) // 一定要的註解
public class MenuServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
//		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String menuId = req.getParameter("menuId");
				String menuIdReg = "^[M]\\d{6}$";
				if (menuId == null || (menuId.trim()).length() == 0) {
					errorMsgs.add("請輸入餐點編號");
				} else if (!menuId.trim().matches(menuIdReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點編號: 只能為 M開頭,後面為6組數字,且長度必需為7");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/menu/SelectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MenuService menuSvc = new MenuService();
				MenuVO menuVO = menuSvc.getOneMenu(menuId);
				if (menuVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/menu/SelectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("menuVO", menuVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/menu/ListOneMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/menu/SelectPage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("add".equals(action)) {
//			//原本想用redis處理
//			Jedis jedis = new Jedis("localhost", 6379);
//			jedis.auth("123456");
//			jedis.hset(memberId,menuId,quantity);
//			jedis.close();

			req.setCharacterEncoding("UTF-8");
			String menuId = req.getParameter("menuId");
			String quantity = req.getParameter("quantity");
//			String memberId = req.getParameter("memberId");

//			開始查詢該比訂單資訊
			MenuService menuSvc = new MenuService();

//			查詢到該筆資料
			MenuVO menuVO = menuSvc.getOneMenu(menuId);
			menuVO.setMenuPic(null);
//			存入前端送來的數量
			menuVO.setQuantity(Integer.parseInt(quantity));

			// session 將購物車資訊存入sessionScope
			HttpSession session = req.getSession();
			List<MenuVO> list = (ArrayList<MenuVO>) session.getAttribute("memuList");
//			先確認session有無購物車集合物件(沒有則進入迴圈並產生新的購物車集合並且加入產品)
			if (list == null) {
				list = new ArrayList<>();
				list.add(menuVO);
			} else {
//				確認該餐點物件是否存在
				if (list.contains(menuVO)) {
					for (MenuVO vo : list) {
//						判斷餐店編號是否存在
						if (vo.getMenuId().equals(menuVO.getMenuId())) {
//						如果存在只要更新數量
							vo.setQuantity(Integer.parseInt(quantity));
							break;
						}
					}
				} else {
//					餐點不存在所以新增數量並且加入購物車
					menuVO.setQuantity(Integer.parseInt(quantity));
					list.add(menuVO);
				}
			}
//			將購物車集合存入session
			session.setAttribute("memuList", list);
//			將餐點物件包裝成JSON資料型態傳送給前端使用
			JSONObject menuVO1 = new JSONObject(menuVO);

			// 傳送給前端
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.print(menuVO1);
			out.flush();
			out.close();
		}
		
//		刪除購物車清單
		if ("deletCar".equals(action)) {
			String menuId = req.getParameter("menuId");
//			開始查詢該比訂單資訊
			MenuService menuSvc = new MenuService();

//			查詢到該筆資料
			MenuVO menuVO = menuSvc.getOneMenu(menuId);
			menuVO.setMenuPic(null);
			HttpSession session = req.getSession();
			List<MenuVO> list = (ArrayList<MenuVO>) session.getAttribute("memuList");
			
			for (MenuVO vo : list) {
//				判斷餐店編號是否存在
				if (vo.getMenuId().equals(menuVO.getMenuId())) {
					list.remove(vo);
					break;
				}
			}
//			將購物車集合存入session
			session.setAttribute("memuList", list);
			
			
		}
		
		
		
		
		

//		處理餐點的部分
		if ("menuAjax".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String menuChar = req.getParameter("menuChar");
			String storeId = req.getParameter("storeId");

			/*************************** 2.開始查詢資料 *****************************************/
			MenuService menuSvc = new MenuService();
			JSONArray menuVO = menuSvc.getOneByMenuChar(menuChar, storeId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.print(menuVO);
			out.flush();
			out.close();

			/*************************** 其他可能的錯誤處理 *************************************/

		}

//		處理圖片部分
		if ("getOnePicture".equals(action)) { // 顯示單張圖片
			ServletOutputStream out = null;
			res.setContentType("image/gif");
			out = res.getOutputStream();
			byte[] menuPic;
			String menuId = req.getParameter("menuId");
			MenuService menuSvc = new MenuService();
			MenuVO menuVO = menuSvc.getOneMenu(menuId);
			if (!(menuVO.getMenuPic() == null)) { // 如果資料庫沒有檔案,則執行else
				menuPic = menuVO.getMenuPic();
				out.write(menuPic);
				out.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/back-end/menu/NoData/null.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
				out.close();
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String menuId = req.getParameter("menuId");

				/*************************** 2.開始查詢資料 ****************************************/
				MenuService menuSvc = new MenuService();
				MenuVO menuVO = menuSvc.getOneMenu(menuId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("menuVO", menuVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/menu/updatemenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/menu/menulist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updatesellstatus".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer size = new Integer(req.getParameter("size").trim());
				String[] menuId = req.getParameterValues("menuId");
				String[] menuStatus = req.getParameterValues("menuStatus");
				for (int i = 0; i < size; i++) {
					String menuIdOne = menuId[i];
					Integer menuStatusOne = new Integer(menuStatus[i]);
					/*************************** 2.開始查詢資料 ****************************************/
					MenuService menuSvc = new MenuService();
					MenuVO menuVO = menuSvc.updateSellStatus(menuIdOne, menuStatusOne);
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/front-store-end/menu/sellstatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/menu/sellstatus.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String menuId = req.getParameter("menuId");
				String storeId = req.getParameter("storeId");

//				判斷餐點名稱
				String menuName = req.getParameter("menuName");
				String menuNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,20}$";
				if (menuName == null || menuName.trim().length() == 0) {
					errorMsgs.add("餐點名稱: 請勿空白");
				} else if (!menuName.trim().matches(menuNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點名稱: 只能是中、英文字母 , 且長度必需在1到20之間");
				}
//				判斷餐點描述(只能0~100字)
				String menuDetail = req.getParameter("menuDetail").trim();

				String menuDetailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9, )]{0,300}$";
				if (!menuDetail.trim().matches(menuDetailReg)) {
					errorMsgs.add("餐點描述: 只能是中、英文字母、數字和 逗號, 且長度必需在0到300之間");
				}
				// 檔案上傳
				Part part = req.getPart("menuPic");
				InputStream in = part.getInputStream();
				byte[] menuPic = null;
				if (in.available() == 0) { // 如果沒有檔案,則從原本的資料庫將資料重新寫入
					MenuService menuSvc = new MenuService();
					MenuVO menuVO = menuSvc.getOneMenu(menuId);
					menuPic = menuVO.getMenuPic();

				} else {
					menuPic = new byte[in.available()];
					in.read(menuPic);
					in.close();
				}

//				餐點種類
				String menuChar = req.getParameter("menuChar").trim();

				String menuCharReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,10}$";
				if (menuChar == null || menuChar.trim().length() == 0) {
					errorMsgs.add("餐點種類: 請勿空白");
				} else if (!menuChar.trim().matches(menuCharReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點種類: 只能是中、英文字母 , 且長度必需在1到10之間");
				}

//				餐點可售狀態(0:是已售完;1:是可銷售)
				Integer menuStatus = new Integer(req.getParameter("menuStatus").trim());

//				餐點價格
				String menuPrice = req.getParameter("menuPrice").trim();

				String menuPriceReg = "^[0-9]{1,10}$";
				Integer menuPrice1 = 0;
				if (menuPrice == null) {
					errorMsgs.add("餐點價格: 請勿空白");
				} else if (!menuPrice.trim().matches(menuPriceReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點價格: 只能是數字 , 且長度必需在1到10之間");
				} else {
					menuPrice1 = new Integer(menuPrice);
				}
//				餐點上下架狀態(0:已下架;1:上架中)
				Integer menuSellStatus = new Integer(req.getParameter("menuSellStatus").trim());

				MenuVO menuVO = new MenuVO();

				menuVO.setMenuName(menuName);
				menuVO.setMenuDetail(menuDetail);
				menuVO.setMenuPic(menuPic);
				menuVO.setMenuChar(menuChar);
				menuVO.setMenuStatus(menuStatus);
				menuVO.setMenuPrice(menuPrice1);
				menuVO.setMenuSellStatus(menuSellStatus);
				menuVO.setMenuId(menuId);
				menuVO.setStoreId(storeId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/menu/updatemenu.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				MenuService menuSvc = new MenuService();
				menuVO = menuSvc.updateMenu(menuId, storeId, menuName, menuDetail, menuPic, menuChar, menuStatus,
						menuPrice1, menuSellStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

				req.setAttribute("menuVO", menuVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-store-end/menu/menulist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/menu/updatemenu.jsp");
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

				String storeId = req.getParameter("storeId");
//				判斷餐廳名稱
				String menuName = req.getParameter("menuName");
				String menuNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,20}$";
				if (menuName == null || menuName.trim().length() == 0) {
					errorMsgs.add("餐點名稱: 請勿空白");
				} else if (!menuName.trim().matches(menuNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點名稱: 只能是中、英文字母 , 且長度必需在1到20之間");
				}
//				判斷餐點描述(只能0~100字)
				String menuDetail = req.getParameter("menuDetail").trim();
				String menuDetailReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9, )]{0,300}$";
				if (!menuDetail.trim().matches(menuDetailReg)) {
					errorMsgs.add("餐點描述: 只能是中、英文字母、數字和 逗號  ,且長度必需在0到300之間");
				}
				// 檔案上傳
				Part part = req.getPart("menuPic");
				InputStream in = part.getInputStream();
				byte[] menuPic = new byte[in.available()];
				in.read(menuPic);
				in.close();

//				餐點種類
				String menuChar = req.getParameter("menuChar").trim();
				String menuCharReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,10}$";
				if (menuChar == null || menuChar.trim().length() == 0) {
					errorMsgs.add("餐點種類: 請勿空白");
				} else if (!menuChar.trim().matches(menuCharReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點種類: 只能是中、英文字母 , 且長度必需在1到10之間");
				}

//				餐點可售狀態(0:是已售完;1:是可銷售)
				Integer menuStatus = new Integer(req.getParameter("menuStatus").trim());

//				餐點價格
				String menuPrice = req.getParameter("menuPrice").trim();
				String menuPriceReg = "^[0-9]{1,10}$";
				Integer menuPrice1 = 0;
				if (menuPrice == null) {
					errorMsgs.add("餐點價格: 請勿空白");
				} else if (!menuPrice.trim().matches(menuPriceReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點價格: 只能是數字 , 且長度必需在1到10之間");
				} else {
					menuPrice1 = new Integer(menuPrice);
				}
//				餐點上下架狀態(0:已下架;1:上架中)
				Integer menuSellStatus = new Integer(req.getParameter("menuSellStatus").trim());
//				搭配Service 給值
				String menuId = "";

				MenuVO menuVO = new MenuVO();

				menuVO.setMenuName(menuName);
				menuVO.setMenuDetail(menuDetail);
				menuVO.setMenuPic(menuPic);
				menuVO.setMenuChar(menuChar);
				menuVO.setMenuStatus(menuStatus);
				menuVO.setMenuPrice(menuPrice1);
				menuVO.setMenuSellStatus(menuSellStatus);
				menuVO.setMenuId(storeId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("menuVO", menuVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/menu/addmenu.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MenuService menuSvc = new MenuService();
				menuVO = menuSvc.addMenu(menuId, storeId, menuName, menuDetail, menuPic, menuChar, menuStatus,
						menuPrice1, menuSellStatus);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-store-end/menu/menulist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/menu/addmenu.jsp");
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
				String menuId = req.getParameter("menuId");

				/*************************** 2.開始刪除資料 ***************************************/
				MenuService menuSvc = new MenuService();
				menuSvc.deleteMenu(menuId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/menu/listAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗 :" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/menu/listAllMenu.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
