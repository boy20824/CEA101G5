package com.acceptreserve.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.reservesituation.model.ReserveSituationVO;


public class AcceptReserveService {
	
	private AcceptReserveDAO_interface dao;
	
	public AcceptReserveService() {
		dao = new AcceptReserveDAO();
	}
	public AcceptReserveVO addAcceptReserve(Integer periodId,String storeId,
			java.sql.Timestamp startTime,java.sql.Timestamp endTime,Integer periodStatus) {

		AcceptReserveVO arVO = new AcceptReserveVO();
		
		arVO.setPeriodId(periodId);
		arVO.setStoreId(storeId);
		arVO.setStartTime(startTime);
		arVO.setEndTime(endTime);
		arVO.setPeriodStatus(periodStatus);
		
		dao.insert(arVO);

		return arVO;
	}

	public AcceptReserveVO updateAcceptReserve(Integer periodId,String storeId,
			java.sql.Timestamp startTime,java.sql.Timestamp endTime,Integer periodStatus) {

		AcceptReserveVO arVO = new AcceptReserveVO();

		arVO.setPeriodId(periodId);
		arVO.setStoreId(storeId);
		arVO.setStartTime(startTime);
		arVO.setEndTime(endTime);
		arVO.setPeriodStatus(periodStatus);
		dao.update(arVO);

		return arVO;
	}

//	public void deleteEmp(Integer empno) {
//		dao.delete(empno);
//	}

	public AcceptReserveVO getOneAcceptReserve(String storeId,Integer periodId) {
		return dao.findByPrimaryKey(storeId, periodId);
	}

	public List<AcceptReserveVO> getAll() {
		return dao.getAll();
	}
	
	public List<AcceptReserveVO> getSearch(String storeId){
		return dao.search(storeId);
	};
	public List<AcceptReserveVO> getSchedule2(Integer periodStatus){
		return dao.schedule2(periodStatus);
	};

}
