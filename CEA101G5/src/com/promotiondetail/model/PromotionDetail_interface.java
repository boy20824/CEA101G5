package com.promotiondetail.model;

import java.util.List;

import com.promotion.model.PromotionVO;

public interface PromotionDetail_interface {
	public void insert(PromotionDetailVO promotionDetailVO);
	public void update(PromotionDetailVO promotionDetailVO);
	public void delete(Integer promoId,String productId);
	public PromotionDetailVO getOne(Integer promoId,String productId);
	public List<PromotionDetailVO> getAll(Integer promoId);
}
