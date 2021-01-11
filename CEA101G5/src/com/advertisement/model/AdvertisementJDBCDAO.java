package com.advertisement.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class AdvertisementJDBCDAO implements AdvertisementDAO_Interface {

	private static final String INSERT_STMT = 
		"INSERT INTO ADVERTISEMENT (AD_ID, PRODUCT_ID, AD_TITLE, AD_CONTENT_TXT, AD_CONTENT_IMG, AD_START_DATE, AD_END_DATE, AD_STATUS) VALUES (SEQ_AD_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE ADVERTISEMENT SET PRODUCT_ID = ?, AD_TITLE = ?, AD_CONTENT_TXT = ?, AD_CONTENT_IMG = ?, AD_START_DATE = ?, AD_END_DATE = ?, AD_STATUS = ? WHERE AD_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ADVERTISEMENT WHERE AD_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ADVERTISEMENT ORDER BY AD_ID";
	
	@Override
	public void insert(AdvertisementVO advertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, advertisementVO.getProductId());
			pstmt.setString(2, advertisementVO.getAdTitle());
			pstmt.setString(3, advertisementVO.getAdContentTxt());
			pstmt.setBytes(4, advertisementVO.getAdContentImg());
			pstmt.setDate(5, advertisementVO.getAdStartDate());
			pstmt.setDate(6, advertisementVO.getAdEndDate());
			pstmt.setInt(7, advertisementVO.getAdStatus());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(AdvertisementVO advertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, advertisementVO.getProductId());
			pstmt.setString(2, advertisementVO.getAdTitle());
			pstmt.setString(3, advertisementVO.getAdContentTxt());
			pstmt.setBytes(4, advertisementVO.getAdContentImg());
			pstmt.setDate(5, advertisementVO.getAdStartDate());
			pstmt.setDate(6,  advertisementVO.getAdEndDate());
			pstmt.setInt(7, advertisementVO.getAdStatus());
			pstmt.setInt(8, advertisementVO.getAdId());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer adId) {
	}

	@Override
	public AdvertisementVO getOne(Integer adId) {
		AdvertisementVO advertisementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, adId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				advertisementVO = new AdvertisementVO();
				advertisementVO.setAdId(rs.getInt("AD_ID"));
				advertisementVO.setProductId(rs.getString("PRODUCT_ID"));
				advertisementVO.setAdTitle(rs.getString("AD_TITLE"));
				advertisementVO.setAdContentTxt(rs.getString("AD_CONTENT_TXT"));
				advertisementVO.setAdContentImg(rs.getBytes("AD_CONTENT_IMG"));
				advertisementVO.setAdStartDate(rs.getDate("AD_START_DATE"));
				advertisementVO.setAdEndDate(rs.getDate("AD_END_DATE"));
				advertisementVO.setAdStatus(rs.getInt("AD_STATUS"));
				advertisementVO.setAdTs(rs.getDate("AD_TS"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
				rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return advertisementVO;
	}

	@Override
	public List<AdvertisementVO> getAll() {
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO advertisementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				advertisementVO = new AdvertisementVO();
				advertisementVO.setAdId(rs.getInt("AD_ID"));
				advertisementVO.setProductId(rs.getString("PRODUCT_ID"));
				advertisementVO.setAdTitle(rs.getString("AD_TITLE"));
				advertisementVO.setAdContentTxt(rs.getString("AD_CONTENT_TXT"));
				advertisementVO.setAdContentImg(rs.getBytes("AD_CONTENT_IMG"));
				advertisementVO.setAdStartDate(rs.getDate("AD_START_DATE"));
				advertisementVO.setAdEndDate(rs.getDate("AD_END_DATE"));
				advertisementVO.setAdStatus(rs.getInt("AD_STATUS"));
				advertisementVO.setAdTs(rs.getDate("AD_TS"));
				list.add(advertisementVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
				rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		AdvertisementJDBCDAO dao = new AdvertisementJDBCDAO();
		
//		testing : insert()
		AdvertisementVO advertisementVO = new AdvertisementVO();
		advertisementVO.setProductId("ENP0001");
		advertisementVO.setAdTitle("測試主題");
		advertisementVO.setAdContentTxt("測試內容");
		try {
			advertisementVO.setAdContentImg(getPictureByteArray("/Users/jordan/desktop/cat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		advertisementVO.setAdStartDate(java.sql.Date.valueOf("2020-12-1"));
		advertisementVO.setAdEndDate(java.sql.Date.valueOf("2021-1-31"));
		advertisementVO.setAdStatus(1);
		dao.insert(advertisementVO);
		System.out.println("Statement Processed...");
		
//		testing : update()
//		AdvertisementVO advertisementVO = new AdvertisementVO();
//		advertisementVO.setAdId(1);
//		advertisementVO.setProductId("ENP0010");
//		advertisementVO.setAdTitle("���ռs�i�D�DXX");
//		advertisementVO.setAdContentTxt("���ռs�i���eXX");
//		try {
//			advertisementVO.setAdContentImg(getPictureByteArray("/Users/jordan/desktop/cat.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		advertisementVO.setAdStartDate(java.sql.Date.valueOf("2020-12-1"));
//		advertisementVO.setAdEndDate(java.sql.Date.valueOf("2020-12-1"));
//		advertisementVO.setAdStatus(1);
//		dao.update(advertisementVO);
//		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		AdvertisementVO advertisementVO = dao.getOne(7);
//		System.out.println("AD_ID: " + advertisementVO.getAdId());
//		System.out.println("PRODUCT_ID: " + advertisementVO.getProductId());
//		System.out.println("AD_TITLE: " + advertisementVO.getAdTitle());
//		System.out.println("AD_CONTENT_TXT: " + advertisementVO.getAdContentTxt());
//		System.out.println("AD_CONTENT_IMG: " + advertisementVO.getAdContentImg());
//		System.out.println("AD_START_DATE: " + advertisementVO.getAdStartDate());
//		System.out.println("AD_END_DATE: " + advertisementVO.getAdEndDate());
//		System.out.println("AD_STATUS: " + advertisementVO.getAdStatus());
//		System.out.println("AD_TS: " + advertisementVO.getAdTs());
		
//		testing : getAll()
//		List<AdvertisementVO> list = dao.getAll();
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
