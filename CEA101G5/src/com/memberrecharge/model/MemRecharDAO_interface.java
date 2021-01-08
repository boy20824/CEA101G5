package com.memberrecharge.model;

import java.util.List;

public interface MemRecharDAO_interface {
	public void insert(MemRecharVO memRecharVO);
	public void delete(String rechargeId);
	public MemRecharVO findByPrimaryKey(String rechargeId);
	public List<MemRecharVO> findByMemPhone(String memPhone);
	public List<MemRecharVO> getAll();
}
