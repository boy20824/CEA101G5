package com.reservesituation.model;

import java.sql.Date;
import java.util.List;
public interface ReserveSituationDAO_interface {
	public void insert(ReserveSituationVO rsVO);
    public void update(ReserveSituationVO rsVO);
    public void delete(Date reserveSituationDate, String storeId, Integer periodId);
    public ReserveSituationVO findByPrimaryKey(Date reserveSituationDate, String storeId, Integer periodId);
    public List<ReserveSituationVO> getAll();
    public List<ReserveSituationVO> search(Date reserveSituationDate, String storeId);
    public List<ReserveSituationVO> fors(String storeId);
    public void deleteByDate(Date reserveSituationDate);
    public List<ReserveSituationVO> schedule2(String storeId, Integer periodId);
}
