package com.empauthcategory.model;
import java.util.*;

public interface EmpAuthCategoryDAO_interface {
	public void insert(EmpAuthCategoryVO eacVO);
	public void update(EmpAuthCategoryVO eacVO);
	public void delete(String auth_no);
	public EmpAuthCategoryVO findByPrimaryKey(String auth_no);
	public List<EmpAuthCategoryVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
	
}
