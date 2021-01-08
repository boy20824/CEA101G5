package com.queueline.model;

import java.sql.Timestamp;
import java.util.List;

public class QueLineService {

	private QueLineDAO_interface dao;
	
	public QueLineService() {
		dao = new QueLineDAO();
	}
	
	
	public QueLineVO addQueLine(Integer queuelineno, Integer queuenocall, String storeid, Integer queuetableid){
	
		QueLineVO queLineVO = new QueLineVO();
		
		queLineVO.setQueuelineno(queuelineno);
		queLineVO.setQueuenocall(queuenocall);
		queLineVO.setStoreid(storeid);
		queLineVO.setQueuetableid(queuetableid);
		dao.insert(queLineVO);
		
		return queLineVO;
	}
	
	public QueLineVO updateQueLine(Integer queuelineno, Integer queuenocall, String storeid, Integer queuetableid) {
QueLineVO queLineVO = new QueLineVO();
		
		queLineVO.setQueuelineno(queuelineno);
		queLineVO.setQueuenocall(queuenocall);
		queLineVO.setStoreid(storeid);
		queLineVO.setQueuetableid(queuetableid);
		dao.update(queLineVO);
		
		return queLineVO;
		
	}
	
	public void deleteQueLine(Integer quelineno, String storeid, Integer quetableid) {
		
		dao.delete(quelineno, storeid, quetableid);
	}
	
	public QueLineVO getQueNoCall(Integer quelineno, String storeid, Integer quetableid) {
		return dao.findByPrimaryKey(quelineno, storeid, quetableid);
	}
	
	public List<QueLineVO> getStoreQueNo(String storeid){
		return dao.findByStoreid(storeid);
		
	}
	
	public List<QueLineVO> getAll() {
		return dao.getAll();
	}
}
