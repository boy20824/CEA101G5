package com.member.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO easyAddMem(String memPhone, String memPwd, String memName) {
		MemVO memVO = new MemVO();
		memVO.setMemPhone(memPhone);
		memVO.setMemPwd(memPwd);
		memVO.setMemName(memName);
		dao.easyInsert(memVO);
		return memVO;
	}

	public MemVO addMem(String memPhone, String memPwd, String memName, String memAdderss, Integer memSex,
			String memEmail, String memIdentity, Date memBirth, String memNick, Integer memLice , byte[] memPhoto,String memCardNumber,String memCardHolder,String memCardExpirationDate,String memCardCCV) {

		MemVO memVO = new MemVO();

		memVO.setMemPhone(memPhone);
		memVO.setMemPwd(memPwd);
		memVO.setMemName(memName);
		memVO.setMemAddress(memAdderss);
		memVO.setMemSex(memSex);
		memVO.setMemEmail(memEmail);
		memVO.setMemIdentity(memIdentity);
		memVO.setMemBirth(memBirth);
		memVO.setMemNick(memNick);
		memVO.setMemLice(memLice);
		memVO.setMemPhoto(memPhoto);
		memVO.setMemCardNumber(memCardNumber);
		memVO.setMemCardHolder(memCardHolder);
		memVO.setMemCardExpirationDate(memCardExpirationDate);
		memVO.setMemCardCCV(memCardCCV);
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(String memPhone,String memPwd, String memName, String memAdderss,Integer memSex, String memEmail,String memIdentity,Date memBirth, String memNick,
			Integer memLice, Integer memCondition, String memAuth, Integer memTotalRechar, byte[] memPhoto,String memCardNumber,String memCardHolder,String memCardExpirationDate,String memCardCCV) {

		MemVO memVO = new MemVO();
		
		memVO.setMemPhone(memPhone);
		memVO.setMemPwd(memPwd);
		memVO.setMemName(memName);
		memVO.setMemAddress(memAdderss);
		memVO.setMemSex(memSex);
		memVO.setMemEmail(memEmail);
		memVO.setMemIdentity(memIdentity);
		memVO.setMemBirth(memBirth);
		memVO.setMemNick(memNick);
		memVO.setMemLice(memLice);
		memVO.setMemCondition(memCondition);
		memVO.setMemAuth(memAuth);
		memVO.setMemTotalRechar(memTotalRechar);
		memVO.setMemPhoto(memPhoto);
		memVO.setMemCardNumber(memCardNumber);
		memVO.setMemCardHolder(memCardHolder);
		memVO.setMemCardExpirationDate(memCardExpirationDate);
		memVO.setMemCardCCV(memCardCCV);
		dao.update(memVO);
		
		return memVO;
	}
	
	public MemVO updateMemLice(String memPhone,Integer memLice,Integer memCondition) {

		MemVO memVO = new MemVO();
		
		memVO.setMemPhone(memPhone);
		memVO.setMemLice(memLice);
		memVO.setMemCondition(memCondition);		
		dao.updateMemLiceBymemPhone(memVO);
		
		return memVO;
	}
	
	public MemVO updateMemCondition(String memPhone,Integer memCondition) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMemPhone(memPhone);
		memVO.setMemCondition(memCondition);		
		dao.updateMemConditionBymemPhone(memVO);
		
		return memVO;
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}

	public MemVO getOneMem(String memPhone) {
		return dao.findByPrimaryKey(memPhone);
	}

	public void deleteMem(String memPhone) {
		dao.delete(memPhone);
	}
	
}
