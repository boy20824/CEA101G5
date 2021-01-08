package com.queueno.model;

import java.util.*;

public interface QueNoDAO_interface {
	
	public void insert(QueNoVO quenoVO);

	public void update(QueNoVO quenoVO);

	public void delete(String memphone);

	public QueNoVO findByPrimaryKey(Integer queuenum, String memphone);

	public List<QueNoVO> findByStoreid(String storeid);

	public List<QueNoVO> getAll();
	
	public List<QueNoVO> findByStoreidAndTableid(String storeid, Integer queuetableid);

	public QueNoVO findByPhone(String memphone);
	
	public QueNoVO findByPhoneAndStore(String memphone ,String storeid);

	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//	public List<QueNoVO> getAll(Map<String, String[]> map);
}
