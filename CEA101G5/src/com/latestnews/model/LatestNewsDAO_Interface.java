package com.latestnews.model;

import java.util.List;

public interface LatestNewsDAO_Interface {
	public void insert(LatestNewsVO latestNewsVO);
	public void update(LatestNewsVO latestNewsVO);
	public void delete(Integer newsId);
	public LatestNewsVO getOne(Integer newsId);
	public List<LatestNewsVO> getAll();
}
