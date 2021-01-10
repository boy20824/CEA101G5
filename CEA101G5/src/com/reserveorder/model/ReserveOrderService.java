package com.reserveorder.model;
import java.sql.Timestamp;
//02多處使用Service代替DAO, 降低相依性(facade設計模式), 有種領班、門面的概念
import java.util.List;

public class ReserveOrderService {

	private ReserveOrderDAO_interface dao;
//	private DeptDAO_interface dao2;   可以呼叫其他部門的?
//	private DeptService deptSVC;	    也可以直接叫別隻Service
	public ReserveOrderService() {
		dao = new ReserveOrderDAO();
		//dao2 = new DeptDAO();
		//deptSVC = new DeptService();
	}

	public ReserveOrderVO addReserveOrder(String storeId, String memPhone,
			Integer periodId,java.sql.Date reserveTime, Integer reserveAdult,
			Integer reserveChild,Integer reserveStatus,String reserveNote) {

		ReserveOrderVO roVO = new ReserveOrderVO();

		roVO.setStoreId(storeId);
		roVO.setMemPhone(memPhone);
		roVO.setPeriodId(periodId);
		roVO.setReserveTime(reserveTime);
		roVO.setReserveAdult(reserveAdult);
		roVO.setReserveChild(reserveChild);
		roVO.setReserveStatus(reserveStatus);
		roVO.setReserveNote(reserveNote);
		dao.insert(roVO);

		return roVO;
	}

	public ReserveOrderVO updateReserveOrder(String reserveId,String storeId, String memPhone,
			Integer periodId,java.sql.Date reserveTime, Integer reserveAdult,
			Integer reserveChild,Integer reserveStatus,String reserveNote,Timestamp reserveCreateTime) {

		ReserveOrderVO roVO = new ReserveOrderVO();

		roVO.setStoreId(storeId);
		roVO.setMemPhone(memPhone);
		roVO.setPeriodId(periodId);
		roVO.setReserveTime(reserveTime);
		roVO.setReserveAdult(reserveAdult);
		roVO.setReserveChild(reserveChild);
		roVO.setReserveStatus(reserveStatus);
		roVO.setReserveNote(reserveNote);
		roVO.setReserveId(reserveId);
		roVO.setReserveCreateTime(reserveCreateTime);
		dao.update(roVO);

		return roVO;
	}

//	public void deleteEmp(Integer empno) {
//		dao.delete(empno);
//	}

	public List<ReserveOrderVO> getOneReserveOrder(String memPhone) {
		return dao.findByPrimaryKey(memPhone);
	}
	public List<ReserveOrderVO> getForc(String memPhone,Integer reserveStatus) {
		return dao.forc(memPhone,reserveStatus);
	}
	public List<ReserveOrderVO> getForcold(String memPhone,Integer reserveStatus) {
		return dao.forcold(memPhone,reserveStatus);
	}
	public List<ReserveOrderVO> getFors(String storeId,Integer reserveStatus) {
		return dao.fors(storeId,reserveStatus);
	}
	public List<ReserveOrderVO> getForsold(String storeId,Integer reserveStatus) {
		return dao.forsold(storeId,reserveStatus);
	}

	public List<ReserveOrderVO> getAll() {
		return dao.getAll();
	}
	
	public void schedule3(java.sql.Date reserveTime) {
		dao.schedule3(reserveTime);
		
	}
}
