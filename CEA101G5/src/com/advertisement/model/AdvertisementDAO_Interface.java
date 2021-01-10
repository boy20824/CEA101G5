package com.advertisement.model;

import java.util.List;

public interface AdvertisementDAO_Interface {
	public void insert(AdvertisementVO advertisementVO);
	public void update(AdvertisementVO advertisementVO);
	public void delete(Integer adId);
	public AdvertisementVO getOne(Integer adId);
	public List<AdvertisementVO> getAll();
}
