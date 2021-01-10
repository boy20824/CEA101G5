package com.queuetable.model;

import java.util.List;

import com.queueperiod.model.QuePeriodVO;

public interface QueTableDAO_interface {
	public void insert(QueTableVO quetableVO);
	public void update(QueTableVO quetableVO);
	public void delete(Integer quetableid, String storeid);
	public QueTableVO findByPrimaryKey(Integer quetableid, String storeid);
	public List<QueTableVO> findByStoreid(String storeid);
	public List<QueTableVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<QuenoVO> getAll(Map<String, String[]> map);
//	void delete(Integer queperiodid, String storeid);
}
