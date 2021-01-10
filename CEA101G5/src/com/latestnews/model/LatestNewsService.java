package com.latestnews.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class LatestNewsService {
	
	private LatestNewsDAO_Interface dao;
	
	public LatestNewsService() {
		dao = new LatestNewsJNDIDAO();
	}
	
	public LatestNewsVO addNews(String newsTitle, String newsContentTxt, byte[] newsContentImg, Integer newsStatus) {
		LatestNewsVO latestNewsVO = new LatestNewsVO();
		
		latestNewsVO.setNewsTitle(newsTitle);
		latestNewsVO.setNewsContentTxt(newsContentTxt);
		latestNewsVO.setNewsContentImg(newsContentImg);
		latestNewsVO.setNewsStatus(newsStatus);
		dao.insert(latestNewsVO);
		
		return latestNewsVO;
	}
	
	public LatestNewsVO updateNews(Integer newsId, String newsTitle, String newsContentTxt, byte[] newsContentImg, Integer newsStatus) {
		LatestNewsVO latestNewsVO = new LatestNewsVO();
		
		latestNewsVO.setNewsId(newsId);
		latestNewsVO.setNewsTitle(newsTitle);
		latestNewsVO.setNewsContentTxt(newsContentTxt);
		latestNewsVO.setNewsContentImg(newsContentImg);
		latestNewsVO.setNewsStatus(newsStatus);
		dao.update(latestNewsVO);
		
		return latestNewsVO;
	}
	
	public LatestNewsVO getNews(Integer newsId) {
		return dao.getOne(newsId);
	}
	
	public List<LatestNewsVO> getAllNews() {
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
		LatestNewsService latestNewsService = new LatestNewsService();
		
//		testing : addNews()
		try {
			byte[] newsContentImg = getPictureByteArray("/Users/jordan/desktop/700430_HEAD.jpg");
			latestNewsService.addNews("���լ��ʥD�D", "���լ��ʤ��e", newsContentImg, 1);
			System.out.println("Statement Processed...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		testing : updateNews()
//		try {
//			byte[] newsContentImg = getPictureByteArray("/Users/jordan/desktop/boxing-day-offer-banner.jpg");
//			latestNewsService.updateNews(5, "���լ��ʥD�D", "���լ��ʤ��e", newsContentImg, 1);
//			System.out.println("Statement Processed...");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		testing : getNews()
//		LatestNewsVO latestNewsVO = latestNewsService.getNews(1);
//		System.out.println("NEWS_ID: " + latestNewsVO.getNewsId());
//		System.out.println("NEWS_TITLE: " + latestNewsVO.getNewsTitle());
//		System.out.println("NEWS_CONTENT_TXT: " + latestNewsVO.getNewsContentTxt());
//		System.out.println("NEWS_CONTENT_IMG: " + latestNewsVO.getNewsContentImg());
//		System.out.println("NEWS_STATUS: " + latestNewsVO.getNewsStatus());
//		System.out.println("NEWS_TS: " + latestNewsVO.getNewsTS());		
		
//		testing : getAllNews()
//		List<LatestNewsVO> list = latestNewsService.getAllNews();
//		for (LatestNewsVO latestNewsVO : list) {
//			System.out.println("NEWS_ID: " + latestNewsVO.getNewsId());
//			System.out.println("NEWS_TITLE: " + latestNewsVO.getNewsTitle());
//			System.out.println("NEWS_CONTENT_TXT: " + latestNewsVO.getNewsContentTxt());
//			System.out.println("NEWS_CONTENT_IMG: " + latestNewsVO.getNewsContentImg());
//			System.out.println("NEWS_STATUS: " + latestNewsVO.getNewsStatus());
//			System.out.println("NEWS_TS: " + latestNewsVO.getNewsTS());
//			System.out.println("-----------------------------------");
//		}
		
	}
}
