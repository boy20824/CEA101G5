package com.restaurantpicture.model;

import java.util.List;

import com.menu.model.MenuVO;

public class RestaurantPictureService {

	private RestaurantPicture_interface dao;

	public RestaurantPictureService() {
		dao = new RestaurantPictureDAO();
	}

	public RestaurantPictureVO addRestaurantPicture(String storePictureId,String storeId,byte[] storePicture) {

		RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();

		restaurantPictureVO.setStorePictureId(storePictureId);
		restaurantPictureVO.setStorePicture(storePicture);
		restaurantPictureVO.setStoreId(storeId);
		
		dao.insert(restaurantPictureVO);

		return restaurantPictureVO;
	}

	public RestaurantPictureVO updateRestaurantPicture(String storePictureId,String storeId,byte[] storePicture) {

		RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();

		restaurantPictureVO.setStorePictureId(storePictureId);
		restaurantPictureVO.setStorePicture(storePicture);
		restaurantPictureVO.setStoreId(storeId);
		
		dao.insert(restaurantPictureVO);

		return restaurantPictureVO;
	}

	public void deleteRestaurantPicture(String storePictureId) {
		dao.delete(storePictureId);
	}

	public RestaurantPictureVO getOneRestaurantPicture(String storePictureId) {
		return dao.findByPrimaryKey(storePictureId);
	}

	public List<RestaurantPictureVO> getAll() {
		return dao.getAll();
	}

}
