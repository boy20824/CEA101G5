package com.productphoto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productphoto.model.*;

public class ProductPhotoReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductPhotoReader() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		String productId = req.getParameter("productId");
		
		ProductPhotoService productPhotoService = new ProductPhotoService();
		List<ProductPhotoVO> productPhotoVOList = productPhotoService.getAllProductPhotoById(productId);
		for (ProductPhotoVO productPhotoVO : productPhotoVOList) {
			byte[] photoByteArr = productPhotoVO.getProductPhoto();
			out.write(photoByteArr);
			out.flush();
			out.close();
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
