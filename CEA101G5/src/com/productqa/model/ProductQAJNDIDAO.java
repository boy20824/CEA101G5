package com.productqa.model;

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

public class ProductQAJNDIDAO implements ProductQADAO_Interface {
	
	private static DataSource dataSource = null;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO PRODUCT_QA (PQA_ID, PRODUCT_ID, MEM_PHONE, PRODUCT_QUES, PRODUCT_REPLY) VALUES (SEQ_PQA_ID.NEXTVAL, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE PRODUCT_QA SET PRODUCT_ID = ?, MEM_PHONE = ?, PRODUCT_QUES = ?, PRODUCT_REPLY = ? WHERE PQA_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM PRODUCT_QA WHERE PQA_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM PRODUCT_QA ORDER BY PQA_ID";
	private static final String DELETE =
			"DELETE FROM PRODUCT_QA WHERE PQA_ID=?";
	private static final String GET_ALL_BYPRODUCTID_STMT = 
		"SELECT * FROM PRODUCT_QA WHERE PRODUCT_ID = ? ORDER BY PQA_ID DESC";
	private static final String GET_ALL_NULL=
			" SELECT * FROM PRODUCT_QA WHERE product_reply IS NULL";
	@Override
	public void insert(ProductQAVO productQAVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productQAVO.getProductId());
			pstmt.setString(2, productQAVO.getMemPhone());
			pstmt.setString(3, productQAVO.getProductQues());
			pstmt.setString(4, productQAVO.getProductReply());
			
			pstmt.executeUpdate();
			
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
	public void update(ProductQAVO productQAVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, productQAVO.getProductId());
			pstmt.setString(2, productQAVO.getMemPhone());
			pstmt.setString(3, productQAVO.getProductQues());
			pstmt.setString(4, productQAVO.getProductReply());
			pstmt.setInt(5, productQAVO.getPqaId());
			
			pstmt.executeUpdate();
			
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
	public void delete(Integer pqaId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, pqaId);
			
			pstmt.executeUpdate();
			
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
	public ProductQAVO getOne(Integer pqaId) {
		ProductQAVO productQAVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, pqaId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productQAVO = new ProductQAVO();
				productQAVO.setPqaId(rs.getInt("PQA_ID"));
				productQAVO.setProductId(rs.getString("PRODUCT_ID"));
				productQAVO.setMemPhone(rs.getString("MEM_PHONE"));
				productQAVO.setProductQues(rs.getString("PRODUCT_QUES"));
				productQAVO.setProductQuesTstamp(rs.getDate("PRODUCT_QUES_TSTAMP"));
				productQAVO.setProductReply(rs.getString("PRODUCT_REPLY"));
				productQAVO.setProductReplyTstamp(rs.getDate("PRODUCT_REPLY_TSTAMP"));
			}
			
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
		
		return productQAVO;
	}
	
	@Override
	public List<ProductQAVO> getAll() {
		List<ProductQAVO> list = new ArrayList<ProductQAVO>();
		ProductQAVO productQAVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productQAVO = new ProductQAVO();
				productQAVO.setPqaId(rs.getInt("PQA_ID"));
				productQAVO.setProductId(rs.getString("PRODUCT_ID"));
				productQAVO.setMemPhone(rs.getString("MEM_PHONE"));
				productQAVO.setProductQues(rs.getString("PRODUCT_QUES"));
				productQAVO.setProductQuesTstamp(rs.getDate("PRODUCT_QUES_TSTAMP"));
				productQAVO.setProductReply(rs.getNString("PRODUCT_REPLY"));
				productQAVO.setProductReplyTstamp(rs.getDate("PRODUCT_REPLY_TSTAMP"));
				list.add(productQAVO);
			}
			
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
	
	@Override
	public List<ProductQAVO> getQAByProductId(String productId) {
		List<ProductQAVO> list = new ArrayList<ProductQAVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BYPRODUCTID_STMT);
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductQAVO productQAVO = new ProductQAVO();
				productQAVO = new ProductQAVO();
				productQAVO.setPqaId(rs.getInt("PQA_ID"));
				productQAVO.setProductId(rs.getString("PRODUCT_ID"));
				productQAVO.setMemPhone(rs.getString("MEM_PHONE"));
				productQAVO.setProductQues(rs.getString("PRODUCT_QUES"));
				productQAVO.setProductQuesTstamp(rs.getDate("PRODUCT_QUES_TSTAMP"));
				productQAVO.setProductReply(rs.getString("PRODUCT_REPLY"));
				productQAVO.setProductReplyTstamp(rs.getDate("PRODUCT_REPLY_TSTAMP"));
				list.add(productQAVO);
			}
			
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
	
	public static void main(String[] args) {
		ProductQAJDBCDAO dao = new ProductQAJDBCDAO();
		
//		testing : insert()
		ProductQAVO productQAVO = new ProductQAVO();
		productQAVO.setProductId("ENP0007");
		productQAVO.setMemPhone("0921842854");
		productQAVO.setProductQues("´ú¸Õ°ÝÃD...");
		productQAVO.setProductReply("");
		dao.insert(productQAVO);
		System.out.println("Statement Processed...");

//		testing : update()
//		ProductQAVO productQAVO = new ProductQAVO();
//		productQAVO.setPqaId(6);
//		productQAVO.setProductId("ENP0010");
//		productQAVO.setMemPhone("0921842859");
//		productQAVO.setProductQues("´ú¸Õ°ÝÃD");
//		productQAVO.setProductReply("´ú¸Õ¦^µª");
//		dao.update(productQAVO);
//		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		Integer pqaId = new Integer(10);
//		ProductQAVO productQAVO = dao.getOne(pqaId);
//		System.out.println("PQA ID: " + productQAVO.getPqaId());
//		System.out.println("PRODUCT ID: " + productQAVO.getProductId());
//		System.out.println("MEM_PHONE: " + productQAVO.getMemPhone());
//		System.out.println("PRODUCT_QUES: " + productQAVO.getProductQues());
//		System.out.println("PRODUCT_QUES_TSTAMP: " + productQAVO.getProductQuesTstamp());
//		System.out.println("PRODUCT_REPLY: " + productQAVO.getProductReply());
//		System.out.println("PRODUCT_REPLY_TSTAMP: " + productQAVO.getProductReplyTstamp());
		
//		testing : getAll()
//		List<ProductQAVO> list = dao.getAll();
//		for (ProductQAVO productQAVO : list) {
//			System.out.println("PQA ID: " + productQAVO.getPqaId());
//			System.out.println("PRODUCT ID: " + productQAVO.getProductId());
//			System.out.println("MEM PHONE: " + productQAVO.getMemPhone());
//			System.out.println("PRODUCT_QUES: " + productQAVO.getProductQues());
//			System.out.println("PRODUCT_QUES TSTAMP: " + productQAVO.getProductQuesTstamp());
//			System.out.println("PRODUCT_REPLY: " + productQAVO.getProductReply());
//			System.out.println("PRODUCT_REPLY_TSTAMP: " + productQAVO.getProductReplyTstamp());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : getQAByProductId()
//		List<ProductQAVO> list = dao.getQAByProductId("ENP0001");
//		for (ProductQAVO productQAVO : list) {
//			System.out.println("PQA ID: " + productQAVO.getPqaId());
//			System.out.println("PRODUCT ID: " + productQAVO.getProductId());
//			System.out.println("MEM PHONE: " + productQAVO.getMemPhone());
//			System.out.println("PRODUCT_QUES: " + productQAVO.getProductQues());
//			System.out.println("PRODUCT_QUES TSTAMP: " + productQAVO.getProductQuesTstamp());
//			System.out.println("PRODUCT_REPLY: " + productQAVO.getProductReply());
//			System.out.println("PRODUCT_REPLY_TSTAMP: " + productQAVO.getProductReplyTstamp());
//			System.out.println("-----------------------------------");
//		}
		
	}

	@Override
	public List<ProductQAVO> getAllNull() {
		List<ProductQAVO> list = new ArrayList<ProductQAVO>();
		ProductQAVO productQAVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_NULL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productQAVO = new ProductQAVO();
				productQAVO.setPqaId(rs.getInt("PQA_ID"));
				productQAVO.setProductId(rs.getString("PRODUCT_ID"));
				productQAVO.setMemPhone(rs.getString("MEM_PHONE"));
				productQAVO.setProductQues(rs.getString("PRODUCT_QUES"));
				productQAVO.setProductQuesTstamp(rs.getDate("PRODUCT_QUES_TSTAMP"));
				productQAVO.setProductReply(rs.getNString("PRODUCT_REPLY"));
				productQAVO.setProductReplyTstamp(rs.getDate("PRODUCT_REPLY_TSTAMP"));
				list.add(productQAVO);
			}
			
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
}
