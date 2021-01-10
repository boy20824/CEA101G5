package com.promotiondetail.model;

import java.util.List;

public class PromotionDetailService {

	private PromotionDetailDAO_Interface dao;
	
	public PromotionDetailService () {
		dao = new PromotionDetailJNDIDAO();
	}
	
	public PromotionDetailVO addPromotionDetail(Integer promoId,String productId,Integer productPrice,Integer productPromoQty) {
		
		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
		
		promotionDetailVO.setPromoId(promoId);
		promotionDetailVO.setProductId(productId);
		promotionDetailVO.setProductPrice(productPrice);
		promotionDetailVO.setProductPromoQty(productPromoQty);
		dao.insert(promotionDetailVO);
		
		return promotionDetailVO;
	}
	
	public PromotionDetailVO updatePromotionDetail(Integer promoId,String productId,Integer productPrice,Integer productPromoQty) {
		
		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
		
		promotionDetailVO.setProductPrice(productPrice);
		promotionDetailVO.setProductPromoQty(productPromoQty);
		promotionDetailVO.setProductId(productId);
		promotionDetailVO.setPromoId(promoId);
		dao.update(promotionDetailVO);
		
		return promotionDetailVO;
	}
	
	public void deletePromotionDetail(Integer promoId,String productId) {
		dao.delete(promoId, productId);
	}
	
	public PromotionDetailVO getPromotionDetailByProductId(String productId) {
		return dao.getOne(productId);
	}
	
	public List<PromotionDetailVO> getAllPromotionDetail(Integer promoId){
		return dao.getAll(promoId);
	}
	public static void main(String[] args) {
		
		PromotionDetailService promotionDetailService = new PromotionDetailService();
		
//		testing : add
//		promotionDetailService.addPromotionDetail(4, "ENP0002", 111, 222);
//		System.out.println("Statement Processed...");
		
//		testing : update
//		promotionDetailService.updatePromotionDetail(4,"ENP0002", 555, 666);
//		System.out.println("Statement Processed...");
		
//		testing : getPromotionDetailByProductId()
//		PromotionDetailVO promotionDetailVO = promotionDetailService.getPromotionDetailByProductId("ENP0005");
//		System.out.println(promotionDetailVO.getPromoId());
//		System.out.println(promotionDetailVO.getProductId());
//		System.out.println(promotionDetailVO.getProductPrice());
//		System.out.println(promotionDetailVO.getProductPromoQty());

		
//		testing : getAllPromotion();
		List<PromotionDetailVO> list = promotionDetailService.getAllPromotionDetail(1);
		for (PromotionDetailVO promotionDetailVO : list) {
			System.out.println(promotionDetailVO.getPromoId());
			System.out.println(promotionDetailVO.getProductId());
			System.out.println(promotionDetailVO.getProductPrice());
			System.out.println(promotionDetailVO.getProductPromoQty());
			System.out.println("-----------------------------------");
		}	
		
	}
}
