package com.queueperiod.model;


import java.sql.Timestamp;
import java.util.List;

public class QuePeriodService {

	private QuePeriodDAO_interface dao;

	public QuePeriodService() {
		dao = new QuePeriodDAO();
	}

	public QuePeriodVO addQuePeriod(Integer queueperiodid, String storeid, Integer queuest, Timestamp queuestarttime,
			Timestamp queueendtime, Integer queuenocurrent) {

		QuePeriodVO quePeriodVO = new QuePeriodVO();

		quePeriodVO.setQueueperiodid(queueperiodid);
		quePeriodVO.setStoreid(storeid);
		quePeriodVO.setQueuest(queuest);
		quePeriodVO.setQueuestarttime(queuestarttime);
		quePeriodVO.setQueueendtime(queueendtime);
		quePeriodVO.setQueuenocurrent(queuenocurrent);
		dao.insert(quePeriodVO);

		return quePeriodVO;

	}
	

	public QuePeriodVO updateQuePeriod(Integer queueperiodid, String storeid, Integer queuest, Timestamp queuestarttime,
			Timestamp queueendtime, Integer queuenocurrent) {

		QuePeriodVO quePeriodVO = new QuePeriodVO();

		quePeriodVO.setQueueperiodid(queueperiodid);
		quePeriodVO.setStoreid(storeid);
		quePeriodVO.setQueuest(queuest);
		quePeriodVO.setQueuestarttime(queuestarttime);
		quePeriodVO.setQueueendtime(queueendtime);
		quePeriodVO.setQueuenocurrent(queuenocurrent);
		dao.update(quePeriodVO);

		return quePeriodVO;

	}
	
	
	public void deleteQuePeriod(Integer queueperiodid, String storeid) {
		dao.delete(queueperiodid, storeid);
	}
	
	public QuePeriodVO getOneQuePeriod(Integer queueperiodid, String storeid) {
		return dao.findByPrimaryKey(queueperiodid, storeid);
	}
	
	public List<QuePeriodVO> getOneQuePeriod(String storeid) {
		return dao.findByStoreid(storeid);
	}
	
	public List<QuePeriodVO> getAll(){
		return dao.getAll();
	}
	
	
}
