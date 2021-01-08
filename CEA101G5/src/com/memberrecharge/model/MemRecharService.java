package com.memberrecharge.model;

import java.sql.Timestamp;
import java.util.List;

public class MemRecharService {
	private MemRecharDAO_interface dao;
	
	public MemRecharService() {
		dao = new MemRecharDAO();
	}
	
	public MemRecharVO addMemRechar(String rechargeId,String memPhone, Timestamp rechargeDate, Integer rechargeChange) {

		MemRecharVO memRecharVO = new MemRecharVO();
		
		memRecharVO.setRechargeId(rechargeId);
		memRecharVO.setMemPhone(memPhone);
		memRecharVO.setRechargeDate(rechargeDate);
		memRecharVO.setRechargeChange(rechargeChange);
		dao.insert(memRecharVO);

		return memRecharVO;
	}
	
	public List<MemRecharVO> getAll() {
		return dao.getAll();
	}

	public MemRecharVO getOneMemRechar(String rechargeId) {
		return dao.findByPrimaryKey(rechargeId);
	}
	
	public List<MemRecharVO> getMemRecharByMemPhone(String memPhone) {
		return dao.findByMemPhone(memPhone);
	}

	public void deleteMemRechar(String rechargeId) {
		dao.delete(rechargeId);
	}
	

}
