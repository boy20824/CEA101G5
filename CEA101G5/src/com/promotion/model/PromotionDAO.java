package com.promotion.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.restaurantcmt.model.RestaurantCmtDAO;
import com.restaurantcmt.model.RestaurantCmtVO;

public class PromotionDAO implements Promotion_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PROMOTION (PROMO_ID,PROMO_NAME,PROMO_IMG,PROMO_START_DATE,PROMO_END_DATE,PROMO_STATUS) VALUES (SEQ_PROMO_ID.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PROMO_ID,PROMO_NAME,PROMO_IMG,PROMO_START_DATE,PROMO_END_DATE,PROMO_STATUS from PROMOTION order by PROMO_ID";
	private static final String GET_ONE_STMT = "SELECT PROMO_ID,PROMO_NAME,PROMO_IMG,PROMO_START_DATE,PROMO_END_DATE,PROMO_STATUS from PROMOTION where PROMO_ID = ?";
	private static final String DELETE = "DELETE FROM PROMOTION where PROMO_ID = ?";
	private static final String UPDATE = "UPDATE PROMOTION set PROMO_NAME=?,PROMO_IMG=? ,PROMO_START_DATE=?,PROMO_END_DATE=?,PROMO_STATUS=? where PROMO_ID = ?";

	@Override
	public void insert(PromotionVO promotionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
		
			pstmt.setString(1, promotionVO.getPromoName());
			pstmt.setBytes(2, promotionVO.getPromoImg());
			pstmt.setDate(3, promotionVO.getPromoStartDate());
			pstmt.setDate(4, promotionVO.getPromoEndDate());
			pstmt.setInt(5, promotionVO.getPromoStatus());

			pstmt.executeUpdate();


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(PromotionVO promotionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotionVO.getPromoName());
			pstmt.setBytes(2, promotionVO.getPromoImg());
			pstmt.setDate(3, promotionVO.getPromoStartDate());
			pstmt.setDate(4,promotionVO.getPromoEndDate());
			pstmt.setInt(5,promotionVO.getPromoStatus());
			pstmt.setInt(6,promotionVO.getPromoId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer promoId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public PromotionVO getOne(Integer promoId) {

		PromotionVO promotionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promoId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotionVO = new PromotionVO();
				promotionVO.setPromoId(rs.getInt("PROMO_ID"));
				promotionVO.setPromoName(rs.getString("PROMO_NAME"));
				promotionVO.setPromoImg(rs.getBytes("PROMO_IMG"));
				promotionVO.setPromoStartDate(rs.getDate("PROMO_START_DATE"));
				promotionVO.setPromoEndDate(rs.getDate("PROMO_END_DATE"));
				promotionVO.setPromoStatus(rs.getInt("PROMO_STATUS"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return promotionVO;
	}

	@Override
	public List<PromotionVO> getAll() {
		List<PromotionVO> list = new ArrayList<PromotionVO>();
		PromotionVO promotionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				promotionVO = new PromotionVO();
				promotionVO.setPromoId(rs.getInt("PROMO_ID"));
				promotionVO.setPromoName(rs.getString("PROMO_NAME"));
				promotionVO.setPromoImg(rs.getBytes("PROMO_IMG"));
				promotionVO.setPromoStartDate(rs.getDate("PROMO_START_DATE"));
				promotionVO.setPromoEndDate(rs.getDate("PROMO_END_DATE"));
				promotionVO.setPromoStatus(rs.getInt("PROMO_STATUS"));
				list.add(promotionVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {

		PromotionDAO dao = new PromotionDAO();

//		 新增
//		 PromotionVO promotionVO = new PromotionVO();
//		 promotionVO.setPromoName("洗碗精大特賣");
//		 byte[] pic = getPictureByteArray("testpic/1.jpg");
//		 promotionVO.setPromoImg(pic);
//		 promotionVO.setPromoStartDate(java.sql.Date.valueOf("2020-12-11"));
//		 promotionVO.setPromoEndDate(java.sql.Date.valueOf("2020-12-12"));
//		 promotionVO.setPromoStatus(1);
//		 dao.insert(promotionVO);


		// 修改
//		PromotionVO promotionVO = new PromotionVO();
//		promotionVO.setPromoName("真D難吃大特價");
//		byte[] pic = getPictureByteArray("testpic/2.jpg");
//		promotionVO.setPromoImg(pic);
//		promotionVO.setPromoStartDate(java.sql.Date.valueOf("2030-12-11"));
//		promotionVO.setPromoEndDate(java.sql.Date.valueOf("2030-12-11"));		
//		promotionVO.setPromoStatus(0);		
//		promotionVO.setPromoId(2);		
//		dao.update(promotionVO);

//
//		// 刪除
//		dao.delete(2);

//
//		// 查詢單筆
//		PromotionVO promotionVO = new PromotionVO();
//		
//		promotionVO  = dao.getOne(1);
////		
//		System.out.print(promotionVO.getPromoId() + ",");
//		System.out.print(promotionVO.getPromoName() + ",");
////		System.out.print(promotionVO.getPromoImg() + ",");
//		System.out.print(promotionVO.getPromoStartDate() + ",");
//		System.out.print(promotionVO.getPromoEndDate()+ ",");
//		System.out.println(promotionVO.getPromoStatus() + ",");
//		System.out.println("---------------------");
	
		// 查詢全部
		
		List<PromotionVO> list = dao.getAll();
		for (PromotionVO promotionVO : list) {
			System.out.print(promotionVO.getPromoId() + ",");
			System.out.print(promotionVO.getPromoName() + ",");
//			System.out.print(promotionVO.getPromoImg() + ",");
			System.out.print(promotionVO.getPromoStartDate() + ",");
			System.out.print(promotionVO.getPromoEndDate()+ ",");
			System.out.println(promotionVO.getPromoStatus() + ",");
			System.out.println("---------------------");
		}
		 
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];//資料流源頭(來源檔案)的大小 byte陣列會依照檔案大小產生
		fis.read(buffer);//fis會依照陣列大小讀取並放入buffer這個byte[]裡面
		fis.close();
		return buffer;
	}
}