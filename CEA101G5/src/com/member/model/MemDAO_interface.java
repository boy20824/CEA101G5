package com.member.model;

import java.util.*;

public interface MemDAO_interface {
		public void insert(MemVO memVO);
		public void update(MemVO memVO);
		public void updateMemRecharBymemPhone(MemVO memVO);
		public void updateMemLiceBymemPhone(MemVO memVO);
		public void delete(String memPhone);
		public MemVO findByPrimaryKey(String memPhone);
		public List<MemVO> getAll();
		public void updateMemConditionBymemPhone(MemVO memVO);
		public void easyInsert(MemVO memVO);

}
