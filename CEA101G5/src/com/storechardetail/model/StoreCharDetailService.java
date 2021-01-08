package com.storechardetail.model;

import java.util.List;

public class StoreCharDetailService {
	
	private StoreCharDetail_interface dao;
	
	public StoreCharDetailService() {
		
		dao = new StoreCharDetailDAO();
	}
	public StoreCharDetailVO addStoreCharDetail(String storeChar,String storeCharName) {
		
		StoreCharDetailVO storeCharDetailVO = new StoreCharDetailVO();
		
		storeCharDetailVO.setStoreChar(storeCharName);
		storeCharDetailVO.setStoreCharName(storeCharName);
		dao.insert(storeCharDetailVO);

		return storeCharDetailVO;
	}


public StoreCharDetailVO updateStoreCharDetail(String storeChar,String storeCharName) {
	
	StoreCharDetailVO storeCharDetailVO = new StoreCharDetailVO();
	
	storeCharDetailVO.setStoreChar(storeCharName);
	storeCharDetailVO.setStoreCharName(storeCharName);
	dao.insert(storeCharDetailVO);
	
	return storeCharDetailVO;
}

	public StoreCharDetailVO getOneStoreCharDetail(String storeChar) {
		return dao.findByPrimaryKey(storeChar);
	}
	public List<StoreCharDetailVO> getAll() {
		return dao.getAll();
	}

}