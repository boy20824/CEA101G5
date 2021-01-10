package com.queueno.model;

import java.sql.Timestamp;
import java.util.List;

public class QueNoService {

	private QueNoDAO_interface dao;

	public QueNoService() {
		dao = new QueNoDAO();
	}
	
	
	public QueNoVO addQueNo(Integer queuenum, String memphone, Integer party, Timestamp queuenotime, String storeid,
			Integer queueperiodid, Integer queuelineno, Integer queuetableid) {

		QueNoVO queNoVO = new QueNoVO();

		queNoVO.setQueuenum(queuenum);
		queNoVO.setMemphone(memphone);
		queNoVO.setParty(party);
		queNoVO.setQueuenotime(queuenotime);
		queNoVO.setStoreid(storeid);
		queNoVO.setQueueperiodid(queueperiodid);
		queNoVO.setQueuelineno(queuelineno);
		queNoVO.setQueuetableid(queuetableid);
		dao.insert(queNoVO);

		return queNoVO;
	}

	public QueNoVO updateQueNo(Integer queuenum, String memphone, Integer party, Timestamp queuenotime, String storeid,
			Integer queueperiodid, Integer queuelineno, Integer queuetableid) {

		QueNoVO queNoVO = new QueNoVO();

		queNoVO.setQueuenum(queuenum);
		queNoVO.setMemphone(memphone);
		queNoVO.setParty(party);
		queNoVO.setQueuenotime(queuenotime);
		queNoVO.setStoreid(storeid);
		queNoVO.setQueueperiodid(queueperiodid);
		queNoVO.setQueuelineno(queuelineno);
		queNoVO.setQueuetableid(queuetableid);
		dao.update(queNoVO);

		return queNoVO;
	}
	
	public void deleteQueNo(String memphone) {
		dao.delete(memphone);	
	}
	
	public List<QueNoVO> getQueNoByStoreIdAndTableId(String storeid, Integer queuetableid){
		return dao.findByStoreidAndTableid(storeid, queuetableid);
	}
	public QueNoVO getQueNoByPhoneAndStore(String memphone, String storeid) {
		return dao.findByPhoneAndStore(memphone, storeid);
	}
	public QueNoVO getQueNoByPhone(String memphone) {
		return dao.findByPhone(memphone);
	}
	
	public QueNoVO getOneQueNo(Integer queuenum, String memphone) {
		return dao.findByPrimaryKey(queuenum, memphone);
	}
	
	public List<QueNoVO> getQueNoByStoreId(String storeid){
		return dao.findByStoreid(storeid);
	}
	
	public List<QueNoVO> getAll() {
		return dao.getAll();
	}

}