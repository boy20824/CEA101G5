package com.restaurant.model;

import java.sql.Timestamp;
import java.util.List;


public class RestaurantService {

	private Restaurant_interface dao;

	public RestaurantService() {
		dao = new RestaurantDAO();
	}

	public RestaurantVO addRestaurant(String storeId, String memPhone, String storeChar, String storeInfo,
			String storeName, String storePhone, String storeAddress, Integer storeStatus, Integer storeFinalReservDate,
			Integer storeOrderCondition, Integer storeReservCondition, Integer storeQueueCondition, Integer storeOrderWaitTime,
			Timestamp storeOpenTime, Timestamp storeCloseTime, Timestamp storeStartOrderDate, Timestamp storeEndOrderDate,
			Integer acceptGroups, Integer numOfGroups, Integer storePeoplepTotal, Integer storeRatingTotal) {
		
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
		restaurantVO.setStorePeopleTotal(storePeoplepTotal);
		restaurantVO.setStoreRatingTotal(storeRatingTotal);
		dao.insert(restaurantVO);
		

		return restaurantVO;
	}
	public RestaurantVO updateRestaurant(String storeId, String memPhone, String storeChar, String storeInfo,
			String storeName, String storePhone, String storeAddress, Integer storeStatus, Integer storeFinalReservDate,
			Integer storeOrderCondition, Integer storeReservCondition, Integer storeQueueCondition, Integer storeOrderWaitTime,
			Timestamp storeOpenTime, Timestamp storeCloseTime, Timestamp storeStartOrderDate, Timestamp storeEndOrderDate,
			Integer acceptGroups, Integer numOfGroups, Integer storePeoplepTotal, Integer storeRatingTotal) {
		
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
		restaurantVO.setStorePeopleTotal(storePeoplepTotal);
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

}
