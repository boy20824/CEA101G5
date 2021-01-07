package com.supcharmenudetail.model;

import java.util.List;

public class SupcharMenuDetailService {

	private SupcharMenuDetail_interface dao;

	public SupcharMenuDetailService() {
		dao = new SupcharMenuDetailJNDIDAO();
	}

	public SupcharMenuDetailVO addSupcharMenuDetail(String foodOrderId, String menuId, String menuSupcharDetailId,
			String menuSupcharId) {

		SupcharMenuDetailVO supcharMenuDetailVO = new SupcharMenuDetailVO();

		supcharMenuDetailVO.setFoodOrderId(foodOrderId);
		supcharMenuDetailVO.setMenuId(menuId);
		supcharMenuDetailVO.setMenuSupcharDetailId(menuSupcharDetailId);
		supcharMenuDetailVO.setMenuSupcharId(menuSupcharId);
		dao.insert(supcharMenuDetailVO);

		return supcharMenuDetailVO;
	}

	// 不得修改
//	public SupcharMenuDetailVO updateSupcharMenuDetail(String foodOrderId, String menuId, String menuSupcharDetailId,
//			String menuSupcharId) {
//
//		SupcharMenuDetailVO supcharMenuDetailVO = new SupcharMenuDetailVO();
//
//		
//		
//		return supcharMenuDetailVO;
//	}

	public void deletSupcharMenuDetail(String foodOrderId, String menuId, String menuSupcharId) {
		dao.delete(foodOrderId, menuId, menuSupcharId);
	}

	public SupcharMenuDetailVO getOneSupcharMenuDetail(String foodOrderId, String menuId, String menuSupcharId) {
		return dao.findByPrimaryKey(foodOrderId, menuId, menuSupcharId);
	}

	public List<SupcharMenuDetailVO> getAll(String foodOrderId) {
		return dao.getAll(foodOrderId);
	}

}
