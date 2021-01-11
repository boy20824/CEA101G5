package com.queueperiod.model;

import java.util.*;

public interface QuePeriodDAO_interface {
	
	public void insert(QuePeriodVO queperiodVO);
	public void update(QuePeriodVO queperiodVO);
	public void delete(Integer queperiodid, String storeid);
	public QuePeriodVO findByPrimaryKey(Integer queperiodid, String storeid);
	public List<QuePeriodVO> findByStoreid(String storeid);
	public List<QuePeriodVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<QuenoVO> getAll(Map<String, String[]> map); 
//	void delete(Integer queperiodid, String storeid);
}
