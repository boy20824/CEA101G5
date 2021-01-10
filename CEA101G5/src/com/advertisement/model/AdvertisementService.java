package com.advertisement.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class AdvertisementService {

	private AdvertisementDAO_Interface dao;
	
	public AdvertisementService() {
		dao = new AdvertisementJNDIDAO();
	}
	
	public AdvertisementVO addAd(String productId, String adTitle, String adContentTxt, byte[] adContentImg, Date adStartDate, Date adEndDate, Integer adStatus) {
		AdvertisementVO advertisementVO = new AdvertisementVO();
		
		advertisementVO.setProductId(productId);
		advertisementVO.setAdTitle(adTitle);
		advertisementVO.setAdContentTxt(adContentTxt);
		advertisementVO.setAdContentImg(adContentImg);
		advertisementVO.setAdStartDate(adStartDate);
		advertisementVO.setAdEndDate(adEndDate);
		advertisementVO.setAdStatus(adStatus);
		dao.insert(advertisementVO);
		
		return advertisementVO;
	}
	
	public AdvertisementVO updateAd(Integer adId, String productId, String adTitle, String adContentTxt, byte[] adContentImg, Date adStartDate, Date adEndDate, Integer adStatus) {
		AdvertisementVO advertisementVO = new AdvertisementVO();
		
		advertisementVO.setAdId(adId);
		advertisementVO.setProductId(productId);
		advertisementVO.setAdTitle(adTitle);
		advertisementVO.setAdContentTxt(adContentTxt);
		advertisementVO.setAdContentImg(adContentImg);
		advertisementVO.setAdStartDate(adStartDate);
		advertisementVO.setAdEndDate(adEndDate);
		advertisementVO.setAdStatus(adStatus);
		dao.update(advertisementVO);
		
		return advertisementVO;
	}
	
	public AdvertisementVO getAd(Integer adId) {
		return dao.getOne(adId);
	}
	
	public List<AdvertisementVO> getAllAd() {
		return dao.getAll();
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		AdvertisementService advertisementService = new AdvertisementService();
		
//		testing : addAd()
//		try {
//			byte[] productReviewPhoto = getPictureByteArray("/Users/jordan/desktop/cat.png");
//			advertisementService.addAd("ENP0010", "測試主題", "測試內容", productReviewPhoto, java.sql.Date.valueOf("2020-12-01"), java.sql.Date.valueOf("2021-1-31"), 1);
//			System.out.println("Statement Processed...");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
//		testing : updateAd()
//		try {
//			byte[] productReviewPhoto = getPictureByteArray("/Users/jordan/desktop/cat.png");
//			advertisementService.updateAd(1, "ENP0009", "���ռs�i�D�D", "���ռs�i���e", productReviewPhoto, java.sql.Date.valueOf("2020-12-01"), java.sql.Date.valueOf("2020-12-31"), 1);
//			System.out.println("Statement Processed...");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		testing : getAd()
//		AdvertisementVO advertisementVO = advertisementService.getAd(10);
//		System.out.println("AD_ID: " + advertisementVO.getAdId());
//		System.out.println("PRODUCT_ID: " + advertisementVO.getProductId());
//		System.out.println("AD_TITLE: " + advertisementVO.getAdTitle());
//		System.out.println("AD_CONTENT_TXT: " + advertisementVO.getAdContentTxt());
//		System.out.println("AD_CONTENT_IMG: " + advertisementVO.getAdContentImg());
//		System.out.println("AD_START_DATE: " + advertisementVO.getAdStartDate());
//		System.out.println("AD_END_DATE: " + advertisementVO.getAdEndDate());
//		System.out.println("AD_STATUS: " + advertisementVO.getAdStatus());
//		System.out.println("AD_TS: " + advertisementVO.getAdTs());
		
//		testing : getAllAd()
//		List<AdvertisementVO> list = advertisementService.getAllAd();
//		for (AdvertisementVO advertisementVO : list) {
//			System.out.println("AD_ID: " + advertisementVO.getAdId());
//			System.out.println("PRODUCT_ID: " + advertisementVO.getProductId());
//			System.out.println("AD_TITLE: " + advertisementVO.getAdTitle());
//			System.out.println("AD_CONTENT_TXT: " + advertisementVO.getAdContentTxt());
//			System.out.println("AD_CONTENT_IMG: " + advertisementVO.getAdContentImg());
//			System.out.println("AD_START_DATE: " + advertisementVO.getAdStartDate());
//			System.out.println("AD_END_DATE: " + advertisementVO.getAdEndDate());
//			System.out.println("AD_STATUS: " + advertisementVO.getAdStatus());
//			System.out.println("AD_TS: " + advertisementVO.getAdTs());
//			System.out.println("-----------------------------------");
//		}	

	}
}
