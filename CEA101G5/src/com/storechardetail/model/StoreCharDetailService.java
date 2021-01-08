package com.storechardetail.model;

import java.util.List;

public class StoreCharDetailService {
	
	private StoreCharDetail_interface dao;
	
	public StoreCharDetailService() {
		
		dao = new StoreCharDetailDAO();
	}
	public StoreCharDetailVO addStoreCharDetail(String storeCharName) {
		
		StoreCharDetailVO storeCharDetailVO = new StoreCharDetailVO();
		
		storeCharDetailVO.setStoreCharName(storeCharName);
		dao.insert(storeCharDetailVO);

		return storeCharDetailVO;
	}
	
	public void deleteStoreCharDetail(String storeChar) {
		
		StoreCharDetailVO storeCharDetailVO = new StoreCharDetailVO();
		
		storeCharDetailVO.setStoreCharName(storeChar);
		dao.delete(storeChar);
		
	}

	public StoreCharDetailVO getOneStoreCharDetail(String storeChar) {
		return dao.findByPrimaryKey(storeChar);
	}
	public List<StoreCharDetailVO> getAll() {
		return dao.getAll();
	}

}