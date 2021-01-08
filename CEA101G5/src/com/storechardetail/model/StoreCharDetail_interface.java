package com.storechardetail.model;

import java.util.List;

public interface StoreCharDetail_interface {
	public void insert(StoreCharDetailVO storeCharDetailVO);

	public void update(StoreCharDetailVO storeCharDetailVO);

	public StoreCharDetailVO findByPrimaryKey(String storeChar);
	
	public List<StoreCharDetailVO> getAll();
}
