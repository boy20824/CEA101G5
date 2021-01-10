package com.acceptreserve.model;

import java.sql.Date;
import java.util.*;

import com.reservesituation.model.ReserveSituationVO;

public interface AcceptReserveDAO_interface {
    public void insert(AcceptReserveVO arVO);
    public void update(AcceptReserveVO arVO);
    public void delete(String storeId,Integer periodId);
    public AcceptReserveVO findByPrimaryKey(String storeId,Integer periodId);
    public List<AcceptReserveVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    public List<AcceptReserveVO> search(String storeId);
    public List<AcceptReserveVO> schedule2(Integer periodStatus);

}
