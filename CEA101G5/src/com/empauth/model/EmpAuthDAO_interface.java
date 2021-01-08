package com.empauth.model;
import java.util.*;

public interface EmpAuthDAO_interface {
	
	public void insert(EmpAuthVO empaVO);
	public void update(EmpAuthVO empaVO);
	public void delete(String emp_id,String auth_no);
	public EmpAuthVO findByPrimaryKey(String emp_id);
	public List<EmpAuthVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpAuthVO> getAll(Map<String, String[]> map); 
}

