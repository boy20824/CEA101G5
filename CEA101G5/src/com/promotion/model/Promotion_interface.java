package com.promotion.model;

import java.util.List;

public interface Promotion_interface {
	public void insert(PromotionVO promotionVO);
	public void update(PromotionVO promotionVO);
	public void delete(Integer promoId);
	public PromotionVO getOne(Integer promoId);
	public List<PromotionVO> getAll();
}
