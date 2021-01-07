package com.restaurantcmt.model;

import java.util.List;

public class RestaurantCmtService {

	private RestaurantCmt_interface dao;

	public RestaurantCmtService() {
		
		dao = new RestaurantcmtJNDIDAO();
	}
	public RestaurantCmtVO addRestaurantCmt(String storeId,String memPhone,String storeCmtContent,Integer storeRating,Integer storeCmtStatus) {
		
		RestaurantCmtVO restaurantCmtVO = new RestaurantCmtVO();
		
		restaurantCmtVO.setStoreId(storeId);
		restaurantCmtVO.setMemPhone(memPhone);
		restaurantCmtVO.setStoreCmtContent(storeCmtContent);
		restaurantCmtVO.setStoreRating(storeRating);
		restaurantCmtVO.setStoreCmtStatus(storeCmtStatus);
		dao.insert(restaurantCmtVO);
		
		return restaurantCmtVO;
	}
	public RestaurantCmtVO updateRestaurantCmt(String storeCmtId,String storeId,String memPhone,String storeCmtContent,Integer storeRating,Integer storeCmtStatus) {
		
		RestaurantCmtVO restaurantCmtVO = new RestaurantCmtVO();
		
		restaurantCmtVO.setStoreCmtContent(storeCmtContent);
		restaurantCmtVO.setStoreRating(storeRating);
		restaurantCmtVO.setStoreCmtStatus(storeCmtStatus);
		restaurantCmtVO.setStoreCmtId(storeCmtId);
		dao.update(restaurantCmtVO);
		
		return restaurantCmtVO;
	}
	public void deleteRestaurantCmt(String storeCmtId) {
		dao.delete(storeCmtId);
	}
	public RestaurantCmtVO getOne(String storeId,String memPhone) {
		return dao.findByPrimaryKey(storeId,memPhone);
	}
	public List<RestaurantCmtVO> getAll(String storeId) {
		return dao.getAll(storeId);
	}
}
