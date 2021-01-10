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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class PromotionJNDIDAO implements PromotionDAO_Interface {
	
	private static DataSource dataSource = null;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
		
			pstmt.setString(1, promotionVO.getPromoName());
			pstmt.setBytes(2, promotionVO.getPromoImg());
			pstmt.setDate(3, promotionVO.getPromoStartDate());
			pstmt.setDate(4, promotionVO.getPromoEndDate());
			pstmt.setInt(5, promotionVO.getPromoStatus());

			pstmt.executeUpdate();


			// Handle any driver errors
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

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotionVO.getPromoName());
			pstmt.setBytes(2, promotionVO.getPromoImg());
			pstmt.setDate(3, promotionVO.getPromoStartDate());
			pstmt.setDate(4,promotionVO.getPromoEndDate());
			pstmt.setInt(5,promotionVO.getPromoStatus());
			pstmt.setInt(6,promotionVO.getPromoId());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoId);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = dataSource.getConnection();
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

			con = dataSource.getConnection();
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

		PromotionJNDIDAO dao = new PromotionJNDIDAO();
		
//		Insert
//		PromotionVO promotionVO = new PromotionVO();
//		promotionVO.setPromoName("靽瘣餃��迂");
//		byte[] pic = getPictureByteArray("/Users/jordan/desktop/cat.png");
//		promotionVO.setPromoImg(pic);
//		promotionVO.setPromoStartDate(java.sql.Date.valueOf("2020-12-11"));
//		promotionVO.setPromoEndDate(java.sql.Date.valueOf("2020-12-12"));
//		promotionVO.setPromoStatus(1);
//		dao.insert(promotionVO);


//		update
//		PromotionVO promotionVO = new PromotionVO();
//		promotionVO.setPromoName("靽瘣餃��迂");
//		byte[] pic = getPictureByteArray("/Users/jordan/desktop/cat.png");
//		promotionVO.setPromoImg(pic);
//		promotionVO.setPromoStartDate(java.sql.Date.valueOf("2030-12-11"));
//		promotionVO.setPromoEndDate(java.sql.Date.valueOf("2030-12-11"));		
//		promotionVO.setPromoStatus(1);		
//		promotionVO.setPromoId(1);
//		dao.update(promotionVO);

//
//		delete
//		dao.delete(3);


//		getOne
		PromotionVO promotionVO = new PromotionVO();
		promotionVO  = dao.getOne(1);
		System.out.println(promotionVO.getPromoId());
		System.out.println(promotionVO.getPromoName());
		System.out.println(promotionVO.getPromoImg());
		System.out.println(promotionVO.getPromoStartDate());
		System.out.println(promotionVO.getPromoEndDate());
		System.out.println(promotionVO.getPromoStatus());
		System.out.println("---------------------");
	
//		getAll
//		List<PromotionVO> list = dao.getAll();
//		for (PromotionVO promotionVO : list) {
//			System.out.println(promotionVO.getPromoId());
//			System.out.println(promotionVO.getPromoName());
//			System.out.println(promotionVO.getPromoImg());
//			System.out.println(promotionVO.getPromoStartDate());
//			System.out.println(promotionVO.getPromoEndDate());
//			System.out.println(promotionVO.getPromoStatus());
//			System.out.println("---------------------");
//		}
		 
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}