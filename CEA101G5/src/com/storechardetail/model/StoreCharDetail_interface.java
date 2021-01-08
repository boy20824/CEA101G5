package com.storechardetail.model;

import java.util.List;

public interface StoreCharDetail_interface {
	public void insert(StoreCharDetailVO storeCharDetailVO);

	public StoreCharDetailVO findByPrimaryKey(String storeChar);
	
	public List<StoreCharDetailVO> getAll();
	
	public void delete(String storeChar);
	
}
