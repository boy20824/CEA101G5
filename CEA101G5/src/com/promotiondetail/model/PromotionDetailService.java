package com.promotiondetail.model;

import java.util.List;

import com.promotion.model.PromotionVO;

public class PromotionDetailService {

	private PromotionDetail_interface dao;
	
	public PromotionDetailService () {
		dao = new PromotionDetailDAO();
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
	
	public PromotionDetailVO getPromotionDetailById(Integer promoId,String productId) {
		return dao.getOne(promoId, productId);
	}
	
	public List<PromotionDetailVO> getAllPromotionDetail(Integer promoId){
		return dao.getAll(promoId);
	}
	public static void main(String[] args) {
		
		PromotionDetailService promotionDetailService = new PromotionDetailService();
		
//		testing : add
//		promotionDetailService.addPromotionDetail(1, "ENP0007", 999, 99);
//		System.out.println("Statement Processed...");
		
//		testing : update
//		promotionDetailService.updatePromotionDetail(1,"ENP0007", 666, 66);
//		System.out.println("Statement Processed...");
		
//		testing : getPromotionDetailService()
//		PromotionDetailVO promotionDetailVO = promotionDetailService.getPromotionDetailById(1, "ENP0005");
//		System.out.print(promotionDetailVO.getPromoId()+",");
//		System.out.print(promotionDetailVO.getProductId()+",");
//		System.out.print(promotionDetailVO.getProductPrice()+",");
//		System.out.println(promotionDetailVO.getProductPromoQty());

		
//		testing : getAllPromotion();
		List<PromotionDetailVO> list = promotionDetailService.getAllPromotionDetail(1);
		for (PromotionDetailVO promotionDetailVO : list) {
			System.out.print(promotionDetailVO.getPromoId()+",");
			System.out.print(promotionDetailVO.getProductId()+",");
			System.out.print(promotionDetailVO.getProductPrice()+",");
			System.out.println(promotionDetailVO.getProductPromoQty());
			System.out.println("-----------------------------------");
		}	
		
	}
}
