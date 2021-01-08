package com.queueline.model;

import java.util.List;

import com.queueline.model.QueLineVO;

public interface QueLineDAO_interface {
	public void insert(QueLineVO quelineVO);
	public void update(QueLineVO quelineVO);
	public void delete(Integer quelineno, String storeid,Integer quetableid);
	public QueLineVO findByPrimaryKey(Integer quelineno, String storeid, Integer quetableid);
	public List<QueLineVO> findByStoreid(String storeid);
	public List<QueLineVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<QuenoVO> getAll(Map<String, String[]> map);
//	void delete(Integer quelineno, String storeid);

}
