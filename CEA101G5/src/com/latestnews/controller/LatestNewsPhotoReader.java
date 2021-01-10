package com.latestnews.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.latestnews.model.*;

public class LatestNewsPhotoReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LatestNewsPhotoReader() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		Integer newsId = Integer.parseInt(req.getParameter("newsId"));
		LatestNewsService latestNewsService = new LatestNewsService();
		LatestNewsVO latestNewsVO = latestNewsService.getNews(newsId);
		byte[] photoByteArr = latestNewsVO.getNewsContentImg();
		out.write(photoByteArr);
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
