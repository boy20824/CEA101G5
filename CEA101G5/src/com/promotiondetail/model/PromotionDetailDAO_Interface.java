package com.promotiondetail.model;

import java.util.List;

public interface PromotionDetailDAO_Interface {
	public void insert(PromotionDetailVO promotionDetailVO);
	public void update(PromotionDetailVO promotionDetailVO);
	public void delete(Integer promoId,String productId);
	public PromotionDetailVO getOne(String productId);
	public List<PromotionDetailVO> getAll(Integer promoId);
}
