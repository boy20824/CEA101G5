package com.reservesituation.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class ReserveSituationService {
	
	private ReserveSituationDAO_interface dao;
	
	public ReserveSituationService() {
		dao = new ReserveSituationDAO();
	}
	public ReserveSituationVO addReserveSituation(Date reserveSituationDate, String storeId, Integer periodId,
			Integer acceptGroups, Integer reservedGroups) {

		ReserveSituationVO rsVO = new ReserveSituationVO();
		
		rsVO.setPeriodId(periodId);
		rsVO.setStoreId(storeId);
		rsVO.setReserveSituationDate(reserveSituationDate);
		rsVO.setAcceptGroups(acceptGroups);
		rsVO.setReservedGroups(reservedGroups);
		
		dao.insert(rsVO);

		return rsVO;
	}

	public ReserveSituationVO updateReserveSituation(Date reserveSituationDate, String storeId, Integer periodId,
			Integer acceptGroups, Integer reservedGroups) {

		ReserveSituationVO rsVO = new ReserveSituationVO();

		rsVO.setPeriodId(periodId);
		rsVO.setStoreId(storeId);
		rsVO.setReserveSituationDate(reserveSituationDate);
		rsVO.setAcceptGroups(acceptGroups);
		rsVO.setReservedGroups(reservedGroups);
		dao.update(rsVO);

		return rsVO;
	}

	public void deleteReserveSituation(Date reserveSituationDate,String storeId,Integer periodId) {
		dao.delete(reserveSituationDate,storeId, periodId);
	}

	public ReserveSituationVO getOneReserveSituation(Date reserveSituationDate,String storeId,Integer periodId) {
		return dao.findByPrimaryKey(reserveSituationDate,storeId, periodId);
	}

	public List<ReserveSituationVO> getAll() {
		return dao.getAll();
	}
	
	public List<ReserveSituationVO> getSearch(Date reserveSituationDate,String storeId){
		return dao.search(reserveSituationDate, storeId);
	}
	public List<ReserveSituationVO> fors(String storeId){
		return dao.fors(storeId);
	}
	public void deleteByDate(Date reserveSituationDate) {
		dao.deleteByDate(reserveSituationDate);
	}
	public List<ReserveSituationVO> schedule2(String storeId, Integer periodId){
		return dao.schedule2(storeId,periodId);
	}
	

}
