package com.promotion.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class PromotionService {

	private PromotionDAO_Interface dao;

	public PromotionService() {
		dao = new PromotionJNDIDAO();
	}

	public PromotionVO addPromotion(String promoName, byte[] promoImg, Date promoStartDate, Date promoEndDate, Integer promoStatus) {

		PromotionVO promotionVO = new PromotionVO();

		promotionVO.setPromoName(promoName);
		promotionVO.setPromoImg(promoImg);
		promotionVO.setPromoStartDate(promoStartDate);
		promotionVO.setPromoEndDate(promoEndDate);
		promotionVO.setPromoStatus(promoStatus);
		dao.insert(promotionVO);
		
		return promotionVO;
	}
	
	public PromotionVO updatePromotion(Integer promoId, String promoName, byte[] promoImg, Date promoStartDate,
			Date promoEndDate, Integer promoStatus) {
		
		PromotionVO promotionVO = new PromotionVO();
		
		promotionVO.setPromoName(promoName);
		promotionVO.setPromoImg(promoImg);
		promotionVO.setPromoStartDate(promoStartDate);
		promotionVO.setPromoEndDate(promoEndDate);
		promotionVO.setPromoStatus(promoStatus);
		promotionVO.setPromoId(promoId);
		dao.update(promotionVO);
		
		return promotionVO;
	}
	
	public void deletePromotion(Integer promoId) {
		dao.delete(promoId);
	}
	public PromotionVO getPromotionById(Integer promoId) {
		return dao.getOne(promoId);
	}
	public List<PromotionVO> getAllPromotion(){
		return dao.getAll();
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args)throws IOException {
		PromotionService promotionService =new PromotionService();
		
//		testing : add
//		byte[] pic = getPictureByteArray("/Users/jordan/desktop/cat.png");
//		promotionService.addPromotion("促銷活動名稱SERVICE", pic, java.sql.Date.valueOf("2020-12-11"), java.sql.Date.valueOf("2020-12-21"), 1);
//		System.out.println("Statement Processed...");
		
//		testing : update
//		byte[] pic = getPictureByteArray("/Users/jordan/desktop/cat.png");
//		promotionService.updatePromotion(1, "促銷活動名稱SERVICE", pic, java.sql.Date.valueOf("2030-12-11"), java.sql.Date.valueOf("2030-12-21"), 1);
//		System.out.println("Statement Processed...");
		
//		testing : getPromotionById()
//		PromotionVO promotionVO = promotionService.getPromotionById(7);
//		System.out.println(promotionVO.getPromoId());
//		System.out.println(promotionVO.getPromoName());
//		System.out.println(promotionVO.getPromoImg());
//		System.out.println(promotionVO.getPromoStartDate());
//		System.out.println(promotionVO.getPromoEndDate());
//		System.out.println(promotionVO.getPromoStatus());
		
//		testing : getAllPromotion();
//		List<PromotionVO> list = promotionService.getAllPromotion();
//		for (PromotionVO promotionVO : list) {
//			System.out.println(promotionVO.getPromoId());
//			System.out.println(promotionVO.getPromoName());
//			System.out.println(promotionVO.getPromoImg());
//			System.out.println(promotionVO.getPromoStartDate());
//			System.out.println(promotionVO.getPromoEndDate());
//			System.out.println(promotionVO.getPromoStatus());
//			System.out.println("-----------------------------------");
//		}	
	}
}
