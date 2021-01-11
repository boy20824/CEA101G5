package com.restaurantpicture.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemService;
import com.restaurantpicture.model.RestaurantPictureService;
import com.restaurantpicture.model.RestaurantPictureVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024, maxFileSize = 5 * 1024 *1024* 1024, maxRequestSize = 5 * 5  *1024* 1024 * 1024)

//@WebServlet("/restaurantpicturepicture/restaurantpicturepicture")
public class RestaurantPictureServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");

		System.out.println("action: "+action);

		if ("getOne_For_Display".equals(action)) {
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			try {
				String storeId = req.getParameter("storeId");
				RestaurantPictureService restPicSvc = new RestaurantPictureService();
				byte[] baos = restPicSvc.getOneStorePicByStoreId(storeId).getStorePicture();
				out.write(baos);
				
			} catch (Exception e) {
				System.out.println("沒有圖片!!!");
			}finally {
				out.close();
			}
		}

		if ("insert".equals(action)) {
			//從req取得part物件
			Collection<Part> parts = req.getParts();
			String storeid = req.getParameter("storeId");
			for (Part part : parts) {
				System.out.print("type: "+part.getContentType()+"\n");
				if (part.getContentType() != null && part.getContentType().indexOf("image") >= 0) {
					InputStream is = part.getInputStream();
					
					byte[] storePic = new byte[is.available()];
					is.read(storePic);
					is.close();
					RestaurantPictureService restPicSvc = new RestaurantPictureService();
					RestaurantPictureVO restPicVO = restPicSvc.addRestaurantPicture(storeid, storePic);
					req.setAttribute("restPicVO", restPicVO);
				}
			}
			String url = "/front-end/restaurantPicture/restaurantPictureEdit/listAllResPic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		if("delete".equals(action)) {
			String storePicId = req.getParameter("storePictureId");
			RestaurantPictureService restPicSvc = new RestaurantPictureService();
			restPicSvc.deleteRestaurantPicture(storePicId);
			String url = "/front-end/restaurantPicture/restaurantPictureEdit/listAllResPic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		}
		if("getOne_For_Update".equals(action)) {
			String storepicid = req.getParameter("storePictureId");
			System.out.println("updatepicid:"+storepicid);
			//查
			RestaurantPictureService resPicSvc = new RestaurantPictureService();
			RestaurantPictureVO restPicVO = resPicSvc.getOneStorePicByStoreId(storepicid);
			
			req.setAttribute("restaurantPictureVO", restPicVO);
			String url = "/front-end/restaurantPicture/restaurantPictureEdit/pictureUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			System.out.println("send");
		}
		
		if("update".equals(action)){
			Collection<Part> parts = req.getParts();
			String storepicid = req.getParameter("storePictureId");
			System.out.println("picid"+storepicid);
			for (Part part : parts) {
				System.out.print("type: "+part.getContentType()+"\n");
				if (part.getContentType() != null && part.getContentType().indexOf("image") >= 0) {
					InputStream is = part.getInputStream();
					
					byte[] storepic = new byte[is.available()];
					is.read(storepic);
					is.close();
					RestaurantPictureService restPicSvc = new RestaurantPictureService();
					RestaurantPictureVO restPicVO = restPicSvc.updateRestaurantPicture(storepicid, storepic);
					req.setAttribute("restPicVO", restPicVO);
					System.out.println("updated");
				}
			}
			String url = "/front-end/restaurantPicture/restaurantPictureEdit/listAllResPic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
	}
}
