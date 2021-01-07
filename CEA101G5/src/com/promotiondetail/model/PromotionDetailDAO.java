package com.promotiondetail.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.promotion.model.PromotionDAO;
import com.promotion.model.PromotionVO;

public class PromotionDetailDAO implements PromotionDetail_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PROMOTION_DETAIL (PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY from PROMOTION_DETAIL order by PROMO_ID";
	private static final String GET_ONE_STMT = "SELECT PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY from PROMOTION_DETAIL where PROMO_ID = ? and PRODUCT_ID=? ";
	private static final String DELETE = "DELETE FROM PROMOTION_DETAIL where PROMO_ID = ? and PRODUCT_ID=?";
	private static final String UPDATE = "UPDATE PROMOTION_DETAIL set PRODUCT_PRICE=?,PRODUCT_PROMO_QTY=? where PROMO_ID = ? and PRODUCT_ID=?";

	@Override
	public void insert(PromotionDetailVO promotionDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
		
			pstmt.setInt(1, promotionDetailVO.getPromoId());
			pstmt.setString(2, promotionDetailVO.getProductId());
			pstmt.setInt(3, promotionDetailVO.getProductPrice());
			pstmt.setInt(4, promotionDetailVO.getProductPromoQty());
			

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
	public void update(PromotionDetailVO promotionDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, promotionDetailVO.getProductPrice());
			pstmt.setInt(2, promotionDetailVO.getProductPromoQty());
			pstmt.setInt(3, promotionDetailVO.getPromoId());
			pstmt.setString(4,promotionDetailVO.getProductId());
		

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
	public void delete(Integer promoId,String productId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promoId);
			pstmt.setString(2,productId);

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
	public PromotionDetailVO getOne(Integer promoId,String productId) {

		PromotionDetailVO promotionDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promoId);
			pstmt.setString(2,productId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotionDetailVO = new PromotionDetailVO();
				promotionDetailVO.setPromoId(rs.getInt("PROMO_ID"));
				promotionDetailVO.setProductId(rs.getString("PRODUCT_ID"));
				promotionDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				promotionDetailVO.setProductPromoQty(rs.getInt("PRODUCT_PROMO_QTY"));
				
				
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
		return promotionDetailVO;
	}

	@Override
	public List<PromotionDetailVO> getAll(Integer promoId) {
		List<PromotionDetailVO> list = new ArrayList<PromotionDetailVO>();
		PromotionDetailVO promotionDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				promotionDetailVO = new PromotionDetailVO();
				promotionDetailVO.setPromoId(rs.getInt("PROMO_ID"));
				promotionDetailVO.setProductId(rs.getString("PRODUCT_ID"));
				promotionDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				promotionDetailVO.setProductPromoQty(rs.getInt("PRODUCT_PROMO_QTY"));
				list.add(promotionDetailVO); // Store the row in the list
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

		 PromotionDetailDAO dao = new PromotionDetailDAO();

//		 新增
//		 PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		 promotionDetailVO.setPromoId(1);
//		 promotionDetailVO.setProductId("ENP0007");
//		 promotionDetailVO.setProductPrice(500);
//		 promotionDetailVO.setProductPromoQty(10);
//		
//		 dao.insert(promotionDetailVO);


		// 修改
//		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		promotionDetailVO.setProductPrice(1000);
//		promotionDetailVO.setProductPromoQty(99);
//		promotionDetailVO.setPromoId(1);
//		promotionDetailVO.setProductId("ENP0007");
//		
//		dao.update(promotionDetailVO);

//
//		// 刪除
//		dao.delete(1,"ENP0007");

//
//		// 查詢單筆
//		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		
//		promotionDetailVO  = dao.getOne(1,"ENP0005");
////		
//		System.out.print(promotionDetailVO.getPromoId() + ",");
//		System.out.print(promotionDetailVO.getProductId() + ",");
//		System.out.print(promotionDetailVO.getProductPrice() + ",");
//		System.out.println(promotionDetailVO.getProductPromoQty() + ",");
//		System.out.println("---------------------");

		// 查詢全部
		
//		List<PromotionDetailVO> list = dao.getAll(1);
//		for (PromotionDetailVO promotionDetailVO : list) {
//			System.out.print(promotionDetailVO.getPromoId() + ",");
//			System.out.print(promotionDetailVO.getProductId() + ",");
//			System.out.print(promotionDetailVO.getProductPrice() + ",");
//			System.out.println(promotionDetailVO.getProductPromoQty() + ",");
//			System.out.println("---------------------");
//		}
		 
	}

}