package com.reserveorder.model;

import java.util.*;

public interface ReserveOrderDAO_interface {
    public void insert(ReserveOrderVO roVO);
    public void update(ReserveOrderVO roVO);
    public void schedule3(java.sql.Date reserveTime);
    public void delete(String reservId);
    public List<ReserveOrderVO> findByPrimaryKey(String memPhone);
    public List<ReserveOrderVO> forc(String memPhone,Integer reserveStatus);
    public List<ReserveOrderVO> forcold(String memPhone,Integer reserveStatus);
    public List<ReserveOrderVO> fors(String storeId,Integer reserveStatus);
    public List<ReserveOrderVO> forsold(String storeId,Integer reserveStatus);
    public List<ReserveOrderVO> getAll();
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
