package com.restaurant.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.restaurantpicture.model.RestaurantPictureVO;


public class RestaurantService {

	private Restaurant_interface dao;

	public RestaurantService() {
		dao = new RestaurantDAO();
	}

	public RestaurantVO addRestaurant(String storeId, String memPhone, String storeChar, String storeInfo,
			String storeName, String storePhone, String storeAddress, Integer storeStatus, Integer storeFinalReservDate,
			Integer storeOrderCondition, Integer storeReservCondition, Integer storeQueueCondition, Integer storeOrderWaitTime,
			Timestamp storeOpenTime, Timestamp storeCloseTime, Timestamp storeStartOrderDate, Timestamp storeEndOrderDate,
			Integer acceptGroups, Integer numOfGroups, Integer storePeopleTotal, Integer storeRatingTotal) {
		
		RestaurantVO restaurantVO = new RestaurantVO();
		
		restaurantVO.setStoreId(storeId);
		restaurantVO.setMemPhone(memPhone);
		restaurantVO.setStoreChar(storeChar);
		restaurantVO.setStoreInfo(storeInfo);
		restaurantVO.setStoreName(storeName);
		restaurantVO.setStorePhone(storePhone);
		restaurantVO.setStoreAddress(storeAddress);
		restaurantVO.setStoreStatus(storeStatus);
		restaurantVO.setStoreFinalReservDate(storeFinalReservDate);
		restaurantVO.setStoreOrderCondition(storeOrderCondition);
		restaurantVO.setStoreReservCondition(storeReservCondition);
		restaurantVO.setStoreQueueCondition(storeQueueCondition);
		restaurantVO.setStoreOrderWaitTime(storeOrderWaitTime);
		restaurantVO.setStoreOpenTime(storeOpenTime);
		restaurantVO.setStoreCloseTime(storeCloseTime);
		restaurantVO.setStoreStartOrderDate(storeStartOrderDate);
		restaurantVO.setStoreEndOrderDate(storeEndOrderDate);
		restaurantVO.setAcceptGroups(acceptGroups);
		restaurantVO.setNumOfGroups(numOfGroups);
		restaurantVO.setStorePeopleTotal(storePeopleTotal);
		restaurantVO.setStoreRatingTotal(storeRatingTotal);
		dao.insert(restaurantVO);
		

		return restaurantVO;
	}
	
	public RestaurantVO easyAddRestaurant(String memPhone, String storeChar, String storeInfo,
			String storeName, String storePhone, String storeAddress, Integer storeStatus,Timestamp storeOpenTime, Timestamp storeCloseTime) {
		
		RestaurantVO restaurantVO = new RestaurantVO();
		
		restaurantVO.setMemPhone(memPhone);
		restaurantVO.setStoreChar(storeChar);
		restaurantVO.setStoreInfo(storeInfo);
		restaurantVO.setStoreName(storeName);
		restaurantVO.setStorePhone(storePhone);
		restaurantVO.setStoreAddress(storeAddress);
		restaurantVO.setStoreStatus(storeStatus);
		restaurantVO.setStoreOpenTime(storeOpenTime);
		restaurantVO.setStoreCloseTime(storeCloseTime);
		dao.easyinsert(restaurantVO);
		
		
		return restaurantVO;
	}
	public RestaurantVO updateRestaurant(String storeId, String memPhone, String storeChar, String storeInfo,
			String storeName, String storePhone, String storeAddress, Integer storeStatus, Integer storeFinalReservDate,
			Integer storeOrderCondition, Integer storeReservCondition, Integer storeQueueCondition, Integer storeOrderWaitTime,
			Timestamp storeOpenTime, Timestamp storeCloseTime, Timestamp storeStartOrderDate, Timestamp storeEndOrderDate,
			Integer acceptGroups, Integer numOfGroups, Integer storePeopleTotal, Integer storeRatingTotal) {
		
		RestaurantVO restaurantVO = new RestaurantVO();
		
		restaurantVO.setStoreId(storeId);
		restaurantVO.setMemPhone(memPhone);
		restaurantVO.setStoreChar(storeChar);
		restaurantVO.setStoreInfo(storeInfo);
		restaurantVO.setStoreName(storeName);
		restaurantVO.setStorePhone(storePhone);
		restaurantVO.setStoreAddress(storeAddress);
		restaurantVO.setStoreStatus(storeStatus);
		restaurantVO.setStoreFinalReservDate(storeFinalReservDate);
		restaurantVO.setStoreOrderCondition(storeOrderCondition);
		restaurantVO.setStoreReservCondition(storeReservCondition);
		restaurantVO.setStoreQueueCondition(storeQueueCondition);
		restaurantVO.setStoreOrderWaitTime(storeOrderWaitTime);
		restaurantVO.setStoreOpenTime(storeOpenTime);
		restaurantVO.setStoreCloseTime(storeCloseTime);
		restaurantVO.setStoreStartOrderDate(storeStartOrderDate);
		restaurantVO.setStoreEndOrderDate(storeEndOrderDate);
		restaurantVO.setAcceptGroups(acceptGroups);
		restaurantVO.setNumOfGroups(numOfGroups);
		restaurantVO.setStorePeopleTotal(storePeopleTotal);
		restaurantVO.setStoreRatingTotal(storeRatingTotal);
		dao.update(restaurantVO);

		return restaurantVO;
	}
	public RestaurantVO getOneRestaurant(String storeId) {
		return dao.findByPrimaryKey(storeId);
	}
	public List<RestaurantVO> getAll() {
		return dao.getAll();
	}
	public List<RestaurantVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public void deleteRestaurant(String storeId) {
		dao.delete(storeId);
	}
	public void easyAddRestaurantWithPic(RestaurantVO restaurantVO,RestaurantPictureVO restaurantPictureVO) {
		dao.easyInsertWithPics(restaurantVO,restaurantPictureVO);
	}

}
