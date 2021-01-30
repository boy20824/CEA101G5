package com.promotiondetail.model;

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

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernate.util.HibernateUtil;
import util.Util;

public class PromotionDetailJNDIDAO implements PromotionDetailDAO_Interface {
	
	@Override
	public void insert(PromotionDetailVO promotionDetailVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(promotionDetailVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
		
	}
	
	@Override
	public void update(PromotionDetailVO promotionDetailVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(promotionDetailVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
		
	}
	
	@Override
	public void delete(Integer promoId,String productId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			PromotionDetailVO promotionDetailVO = session.get(PromotionDetailVO.class, new PromotionDetailVO(promoId, productId));
			session.delete(promotionDetailVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}

	}
	 
	@Override
	public PromotionDetailVO getOne(String productId) {
		// Method Not Used In Actual Operation //
		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
		return promotionDetailVO;
	}
	
	@Override
	public List<PromotionDetailVO> getAll(Integer promoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<PromotionDetailVO> list = null;
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("from PromotionDetailVO where promoId = ?0 order by productId");
			query.setParameter(0, promoId);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
		
		return list;
		
	}
	
//	private static DataSource dataSource = null;
//	static {
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CEA101G5");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = "INSERT INTO PROMOTION_DETAIL (PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY) VALUES (?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY from PROMOTION_DETAIL order by PRODUCT_ID";
//	private static final String GET_ONE_STMT = "SELECT PROMO_ID,PRODUCT_ID,PRODUCT_PRICE,PRODUCT_PROMO_QTY from PROMOTION_DETAIL where PRODUCT_ID= ?";
//	private static final String DELETE = "DELETE FROM PROMOTION_DETAIL where PROMO_ID = ? and PRODUCT_ID=?";
//	private static final String UPDATE = "UPDATE PROMOTION_DETAIL set PRODUCT_PRICE=?,PRODUCT_PROMO_QTY=? where PROMO_ID = ? and PRODUCT_ID=?";
	
	
//	@Override
//	public void insert(PromotionDetailVO promotionDetailVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//			
//		
//			pstmt.setInt(1, promotionDetailVO.getPromoId());
//			pstmt.setString(2, promotionDetailVO.getProductId());
//			pstmt.setInt(3, promotionDetailVO.getProductPrice());
//			pstmt.setInt(4, promotionDetailVO.getProductPromoQty());
//			
//
//			pstmt.executeUpdate();
//
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

//	@Override
//	public void update(PromotionDetailVO promotionDetailVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, promotionDetailVO.getProductPrice());
//			pstmt.setInt(2, promotionDetailVO.getProductPromoQty());
//			pstmt.setInt(3, promotionDetailVO.getPromoId());
//			pstmt.setString(4,promotionDetailVO.getProductId());
//		
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

//	@Override
//	public void delete(Integer promoId,String productId) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, promoId);
//			pstmt.setString(2,productId);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

//	@Override
//	public PromotionDetailVO getOne(String productId) {
//
//		PromotionDetailVO promotionDetailVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, productId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				promotionDetailVO = new PromotionDetailVO();
//				promotionDetailVO.setPromoId(rs.getInt("PROMO_ID"));
//				promotionDetailVO.setProductId(rs.getString("PRODUCT_ID"));
//				promotionDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
//				promotionDetailVO.setProductPromoQty(rs.getInt("PRODUCT_PROMO_QTY"));
//				
//				
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return promotionDetailVO;
//	}

//	@Override
//	public List<PromotionDetailVO> getAll(Integer promoId) {
//		List<PromotionDetailVO> list = new ArrayList<PromotionDetailVO>();
//		PromotionDetailVO promotionDetailVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = dataSource.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				promotionDetailVO = new PromotionDetailVO();
//				promotionDetailVO.setPromoId(rs.getInt("PROMO_ID"));
//				promotionDetailVO.setProductId(rs.getString("PRODUCT_ID"));
//				promotionDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
//				promotionDetailVO.setProductPromoQty(rs.getInt("PRODUCT_PROMO_QTY"));
//				list.add(promotionDetailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	public static void main(String[] args) throws IOException {

		 PromotionDetailJNDIDAO dao = new PromotionDetailJNDIDAO();

//		 insert
//		 PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		 promotionDetailVO.setPromoId(1);
//		 promotionDetailVO.setProductId("ENP0010");
//		 promotionDetailVO.setProductPrice(2500);
//		 promotionDetailVO.setProductPromoQty(100);
//		 dao.insert(promotionDetailVO);

//		update
//		PromotionDetailVO promotionDetailVO = new PromotionDetailVO();
//		promotionDetailVO.setProductPrice(1000);
//		promotionDetailVO.setProductPromoQty(99);
//		promotionDetailVO.setPromoId(1);
//		promotionDetailVO.setProductId("ENP0007");
//		dao.update(promotionDetailVO);

//		delete
//		dao.delete(6,"ENP0007");


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