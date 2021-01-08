package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;


public class MemPhotoReader extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			String memPhone = req.getParameter("memPhone");
			MemService memSvc = new MemService();
			byte[] baos = memSvc.getOneMem(memPhone).getMemPhoto();
			out.write(baos);
			
		} catch (Exception e) {
			System.out.println("沒有圖片!!!");
		}finally {
			out.close();
		}
	}

}
