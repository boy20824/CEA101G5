package com.supcharmenudetail.model;

import java.util.List;

public interface SupcharMenuDetail_interface {
	public void insert(SupcharMenuDetailVO  supcharMenuDetailVO);
    public void update(SupcharMenuDetailVO supcharMenuDetailVO);
    public void delete(String foodOrderId,String menuId,String menuSupcharId);
    public SupcharMenuDetailVO findByPrimaryKey(String foodOrderId,String menuId,String menuSupcharId);
    public List<SupcharMenuDetailVO> getAll(String foodOrderId);
}
