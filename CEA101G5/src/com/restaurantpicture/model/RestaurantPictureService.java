package com.restaurantpicture.model;

import java.util.List;

public class RestaurantPictureService {

	private RestaurantPicture_interface dao;

	public RestaurantPictureService() {
		dao = new RestaurantPictureDAO();
	}

	public RestaurantPictureVO addRestaurantPicture(String storeId, byte[] storePicture) {

		RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();

		restaurantPictureVO.setStorePicture(storePicture);
		restaurantPictureVO.setStoreId(storeId);

		dao.insert(restaurantPictureVO);

		return restaurantPictureVO;
	}

	public List<RestaurantPictureVO> addRestaurantPictures(List<RestaurantPictureVO> list) {

		
			return dao.insert(list);
		
	}

	public RestaurantPictureVO updateRestaurantPicture(String storePictureId, byte[] storePicture) {

		RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();

		restaurantPictureVO.setStorePictureId(storePictureId);
		restaurantPictureVO.setStorePicture(storePicture);

		dao.update(restaurantPictureVO);

		return restaurantPictureVO;
	}

	public void deleteRestaurantPicture(String storePictureId) {
		dao.delete(storePictureId);
	}

	public RestaurantPictureVO getOneRestaurantPicture(String storePictureId) {
		return dao.findByPrimaryKey(storePictureId);
	}

	public List<RestaurantPictureVO> getStorePicByStoreId(String storeId) {
		return dao.findByStoreId(storeId);
	}
	
	public RestaurantPictureVO getOneStorePicByStoreId(String storeId) {
		return dao.findOneByStoreId(storeId);
	}
	

	public List<RestaurantPictureVO> getAll() {
		return dao.getAll();
	}

}
