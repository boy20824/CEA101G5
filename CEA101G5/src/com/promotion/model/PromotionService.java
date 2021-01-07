package com.promotion.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class PromotionService {

	private Promotion_interface dao;

	public PromotionService() {
		dao = new PromotionDAO();
	}

	public PromotionVO addPromotion(String promoName, byte[] promoImg, Date promoStartDate,
			Date promoEndDate, Integer promoStatus) {

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
	
	public static void main(String[] args)throws IOException {
		PromotionService promotionService =new PromotionService();
		
//		testing : add
//		byte[] pic = getPictureByteArray("testpic/1.jpg");
//		promotionService.addPromotion("掃把大特價", pic,java.sql.Date.valueOf("2020-12-11"), java.sql.Date.valueOf("2020-12-21"), 1);
//		System.out.println("Statement Processed...");
		
//		testing : update
//		byte[] pic = getPictureByteArray("testpic/2.jpg");
//		promotionService.updatePromotion(3, "家具大特價", pic, java.sql.Date.valueOf("2030-12-11"), java.sql.Date.valueOf("2030-12-21"), 0);
//		System.out.println("Statement Processed...");
		
//		testing : getPromotionById()
//		PromotionVO promotionVO = promotionService.getPromotionById(3);
//		System.out.print(promotionVO.getPromoId()+",");
//		System.out.print(promotionVO.getPromoName()+",");
////		System.out.println(promotionVO.getPromoImg());
//		System.out.print(promotionVO.getPromoStartDate()+",");
//		System.out.print(promotionVO.getPromoEndDate()+",");
//		System.out.println(promotionVO.getPromoStatus());
		
//		testing : getAllPromotion();
		List<PromotionVO> list = promotionService.getAllPromotion();
		for (PromotionVO promotionVO : list) {
			System.out.print(promotionVO.getPromoId()+",");
			System.out.print(promotionVO.getPromoName()+",");
//			System.out.println(promotionVO.getPromoImg());
			System.out.print(promotionVO.getPromoStartDate()+",");
			System.out.print(promotionVO.getPromoEndDate()+",");
			System.out.println(promotionVO.getPromoStatus());
			System.out.println("-----------------------------------");
		}
		
		
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];//資料流源頭(來源檔案)的大小 byte陣列會依照檔案大小產生
		fis.read(buffer);//fis會依照陣列大小讀取並放入buffer這個byte[]裡面
		fis.close();
		return buffer;
	}
}
