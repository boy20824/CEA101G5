package com.productphoto.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ProductPhotoService {

	private ProductPhotoDAO_Interface dao;
	
	public ProductPhotoService() {
		dao = new ProductPhotoJNDIDAO();
	}
	
	public ProductPhotoVO addProductPhoto(String productId, byte[] productPhoto) {
		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
		
		productPhotoVO.setProductId(productId);
		productPhotoVO.setProductPhoto(productPhoto);
		dao.insert(productPhotoVO);
		
		return productPhotoVO;
	}
	
	public ProductPhotoVO updateProductPhoto(Integer productPhotoId, String productId, byte[] productPhoto) {
		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
		
		productPhotoVO.setProductPhotoId(productPhotoId);
		productPhotoVO.setProductId(productId);
		productPhotoVO.setProductPhoto(productPhoto);
		dao.update(productPhotoVO);
		
		return productPhotoVO;
	}
	
	public ProductPhotoVO getOneProductPhoto(Integer productPhotoId, String productId) {
		return dao.getOne(productPhotoId, productId);
	}
	
	public List<ProductPhotoVO> getAllProductPhotoById(String productId) {
		return dao.getAll(productId);
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		ProductPhotoService productPhotoService = new ProductPhotoService();
		
//		testing : addProductPhoto()
//		try {
//			byte[] productPhoto = getPictureByteArray("/Users/jordan/desktop/cat.png");
//			productPhotoService.addProductPhoto("ENP0004", productPhoto);
//			System.out.println("Statement Processed...");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		testing : updateProductPhoto()
		try {
			byte[] productPhoto = getPictureByteArray
				("/Users/jordan/desktop/1.jpg");
			productPhotoService.updateProductPhoto(10, "ENP007", productPhoto);
			System.out.println("Statement Processed...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		testing : getOneProductPhoto()
//		ProductPhotoVO productPhotoVO = new ProductPhotoVO();
//		productPhotoVO = productPhotoService.getOneProductPhoto(10, "ENP0007");
//		System.out.println("PRODUCT_PHOTO_ID: " + productPhotoVO.getProductPhotoId());
//		System.out.println("PRODUCT_ID: " + productPhotoVO.getProductId());
//		System.out.println("PRODUCT_PHOTO: " + productPhotoVO.getProductPhoto());
		
		
//		testing : getAllProductPhoto()
//		List<ProductPhotoVO> list = productPhotoService.getAllProductPhotoById("ENP0007");
//		for (ProductPhotoVO productPhotoVO : list) {
//			System.out.println("PRODUCT_PHOTO_ID: " + productPhotoVO.getProductPhotoId());
//			System.out.println("PRODUCT_ID: " + productPhotoVO.getProductId());
//			System.out.println("PRODUCT_PHOTO: " + productPhotoVO.getProductPhoto());
//			System.out.println("-----------------------------------");
//		}
		
	}
}
