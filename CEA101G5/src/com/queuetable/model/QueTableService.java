package com.queuetable.model;

import java.util.List;

public class QueTableService {
	
	private QueTableDAO_interface dao;
	
	public QueTableService() {
		dao = new QueTableDAO();
	}
	
	public QueTableVO addQueTable(Integer queuetableid, String queuetabletype, String storeid, Integer queuetablettl, Integer queuetableusable, Integer queuetableocc) {
		QueTableVO quetableVO = new QueTableVO();
		quetableVO.setQueuetableid(queuetableid);
		quetableVO.setQueuetabletype(queuetabletype);
		quetableVO.setStoreid(storeid);
		quetableVO.setQueuetablettl(queuetablettl);
		quetableVO.setQueuetableusable(queuetableusable);
		quetableVO.setQueuetableocc(queuetableocc);
		dao.insert(quetableVO);
		
		return quetableVO;
	}

	public QueTableVO updateTable(Integer queuetableid, String queuetabletype, String storeid, Integer queuetablettl, Integer queuetableusable, Integer queuetableocc) {
		QueTableVO quetableVO = new QueTableVO();
		quetableVO.setQueuetableid(queuetableid);
		quetableVO.setQueuetabletype(queuetabletype);
		quetableVO.setStoreid(storeid);
		quetableVO.setQueuetablettl(queuetablettl);
		quetableVO.setQueuetableusable(queuetableusable);
		quetableVO.setQueuetableocc(queuetableocc);
		dao.update(quetableVO);
		
		return quetableVO;
	}
	
	public void deleteQueTable(Integer queuetableid, String storeid) {
		dao.delete(queuetableid, storeid);
	}
	
	public QueTableVO getOneQueTable(Integer queuetableid, String storeid) {
		return dao.findByPrimaryKey(queuetableid, storeid);
	}
	
	public List<QueTableVO> getStoreQueTable(String storeid) {
		return dao.findByStoreid(storeid);
	}
	
	public List<QueTableVO> getAll(){
		return dao.getAll();
	}
}
