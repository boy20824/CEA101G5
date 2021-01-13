package com.promotiondetail.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class PromotionDetailJDBCDAO implements PromotionDetailDAO_Interface{

	private static final String INSERT_STMT = "INSERT INTO PROMOTION_DETAIL (PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY from PROMOTION_DETAIL order by PRODUCT_ID";
	private static final String GET_ONE_STMT = "SELECT PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY from PROMOTION_DETAIL where PRODUCT_ID= ?";
	private static final String DELETE = "DELETE FROM PROMOTION_DETAIL where PROMO_ID = ? and PRODUCT_ID=?";
	private static final String UPDATE = "UPDATE PROMOTION_DETAIL set PRODUCT_PRICE=?,PRODUCT_PROMO_QTY=? where PROMO_ID = ? and PRODUCT_ID=?";

	@Override
	public void insert(PromotionDetailVO promotionDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
	public PromotionDetailVO getOne(String productId) {

		PromotionDetailVO promotionDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, productId);

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

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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

		 PromotionDetailJDBCDAO dao = new PromotionDetailJDBCDAO();

//		 insert
//		 PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		 promotionDetailVO.setPromoId(1);
//		 promotionDetailVO.setProductId("ENP0009");
//		 promotionDetailVO.setProductPrice(1199);
//		 promotionDetailVO.setProductPromoQty(100);
//		 dao.insert(promotionDetailVO);
//		 System.out.println("Statement Processed....");

//		update
//		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		promotionDetailVO.setProductPrice(1000);
//		promotionDetailVO.setProductPromoQty(99);
//		promotionDetailVO.setPromoId(1);
//		promotionDetailVO.setProductId("ENP0007");
//		dao.update(promotionDetailVO);

//		delete
		dao.delete(1, "ENP0001");
		System.out.println("Statement Processed");


//		getOne
//		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		promotionDetailVO  = dao.getOne("ENP0001");
//		System.out.println(promotionDetailVO.getPromoId());
//		System.out.println(promotionDetailVO.getProductId());
//		System.out.println(promotionDetailVO.getProductPrice());
//		System.out.println(promotionDetailVO.getProductPromoQty());
//		System.out.println("---------------------");

//		getAll
//		List<PromotionDetailVO> list = dao.getAll(1);
//		for (PromotionDetailVO promotionDetailVO : list) {
//			System.out.println(promotionDetailVO.getPromoId());
//			System.out.println(promotionDetailVO.getProductId());
//			System.out.println(promotionDetailVO.getProductPrice());
//			System.out.println(promotionDetailVO.getProductPromoQty());
//			System.out.println("---------------------");
//		}
		 
	}

}