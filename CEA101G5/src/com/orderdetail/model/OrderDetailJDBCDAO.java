package com.orderdetail.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductVO;

import util.Util;

public class OrderDetailJDBCDAO implements OrderDetailDAO_Interface {
	
	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, PRODUCT_PRICE, QUANTITY, PRODUCT_REVIEW, PRODUCT_REVIEW_PHOTO, PRODUCT_REVIEW_STATUS)\n" + 
		"VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE ORDER_DETAIL SET PRODUCT_PRICE = ?, QUANTITY = ?, PRODUCT_REVIEW = ?, PRODUCT_REVIEW_PHOTO = ?, PRODUCT_REVIEW_STATUS = ? WHERE ORDER_ID = ? AND PRODUCT_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ORDER_DETAIL WHERE ORDER_ID = ? AND PRODUCT_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ORDER_DETAIL WHERE ORDER_ID = ? ORDER BY PRODUCT_ID";
	
	private static final String GET_ALL_REVIEW_BYPRODUCTID_STMT = 
		"SELECT * FROM ORDER_DETAIL WHERE PRODUCT_ID = ? ORDER BY ORDER_ID DESC";
	private static final String INSERT_STMT_BYORDER = 
		"INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, PRODUCT_PRICE, QUANTITY, PRODUCT_REVIEW_STATUS) VALUES (?, ?, ?, ?, ?)";
	
	private static final String REVIEW_BY_ID = 
		"SELECT ORDER_ID, PRODUCT_ID, PRODUCT_REVIEW, PRODUCT_REVIEW_TS, PRODUCT_REVIEW_STATUS FROM ORDER_DETAIL WHERE PRODUCT_ID = ? ORDER BY PRODUCT_REVIEW_TS DESC";
	private static final String GET_ALL_REVIEW = 
		"SELECT ORDER_ID, PRODUCT_ID, PRODUCT_REVIEW, PRODUCT_REVIEW_TS, PRODUCT_REVIEW_STATUS FROM ORDER_DETAIL ORDER BY PRODUCT_REVIEW_TS DESC";
	private static final String UPDATE_REVIEW = 
		"UPDATE ORDER_DETAIL SET PRODUCT_REVIEW_STATUS = ? WHERE ORDER_ID = ? AND PRODUCT_ID = ?";

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, orderDetailVO.getOrderId());
			pstmt.setString(2, orderDetailVO.getProductId());
			pstmt.setInt(3, orderDetailVO.getProductPrice());
			pstmt.setInt(4,  orderDetailVO.getQuantity());
			pstmt.setString(5, orderDetailVO.getProductReview());
			pstmt.setBytes(6, orderDetailVO.getProductReviewPhoto());
			pstmt.setInt(7, orderDetailVO.getProductReviewStatus());
			
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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, orderDetailVO.getProductPrice());
			pstmt.setInt(2,  orderDetailVO.getQuantity());
			pstmt.setString(3, orderDetailVO.getProductReview());
			pstmt.setBytes(4, orderDetailVO.getProductReviewPhoto());
			pstmt.setInt(5, orderDetailVO.getProductReviewStatus());
			pstmt.setInt(6, orderDetailVO.getOrderId());
			pstmt.setString(7, orderDetailVO.getProductId());
			
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
	public void delete(Integer orderId) {
	}
	
	@Override
	public OrderDetailVO getOne(Integer orderId, String productId) {
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, orderId);
			pstmt.setString(2, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setProductId(rs.getString("PRODUCT_ID"));
				orderDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				orderDetailVO.setQuantity(rs.getInt("QUANTITY"));
				orderDetailVO.setProductReview(rs.getString("PRODUCT_REVIEW"));
				orderDetailVO.setProductReviewPhoto(rs.getBytes("PRODUCT_REVIEW_PHOTO"));
				orderDetailVO.setProductReviewTS(rs.getDate("PRODUCT_REVIEW_TS"));
				orderDetailVO.setProductReviewStatus(rs.getInt("PRODUCT_REVIEW_STATUS"));
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
		
		return orderDetailVO;
	}

	@Override
	public List<OrderDetailVO> getAll(Integer orderId) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setProductId(rs.getString("PRODUCT_ID"));
				orderDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				orderDetailVO.setQuantity(rs.getInt("QUANTITY"));
				orderDetailVO.setProductReview(rs.getString("PRODUCT_REVIEW"));
				orderDetailVO.setProductReviewPhoto(rs.getBytes("PRODUCT_REVIEW_PHOTO"));
				orderDetailVO.setProductReviewTS(rs.getDate("PRODUCT_REVIEW_TS"));
				orderDetailVO.setProductReviewStatus(rs.getInt("PRODUCT_REVIEW_STATUS"));
				list.add(orderDetailVO);
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
	
	@Override
	public List<OrderDetailVO> getReviewByProductId(String productId) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_REVIEW_BYPRODUCTID_STMT);
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setProductId(rs.getString("PRODUCT_ID"));
				orderDetailVO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				orderDetailVO.setQuantity(rs.getInt("QUANTITY"));
				orderDetailVO.setProductReview(rs.getString("PRODUCT_REVIEW"));
				orderDetailVO.setProductReviewPhoto(rs.getBytes("PRODUCT_REVIEW_PHOTO"));
				orderDetailVO.setProductReviewTS(rs.getDate("PRODUCT_REVIEW_TS"));
				orderDetailVO.setProductReviewStatus(rs.getInt("PRODUCT_REVIEW_STATUS"));
				list.add(orderDetailVO);
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
	
	@Override
	public void insert(ProductVO productVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT_BYORDER);
			
			pstmt.setInt(1, productVO.getOrderId());
			pstmt.setString(2, productVO.getProductId());
			pstmt.setInt(3, productVO.getProductPrice());
			pstmt.setInt(4, productVO.getProductQty());
			pstmt.setInt(5, 1); // Set PRODUCT_REVIEW_STATUS = 1 (SHOW)
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updateReview(Integer productReviewStatus,Integer orderId, String productId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_REVIEW);
			
			pstmt.setInt(1, productReviewStatus);
			pstmt.setInt(2, orderId);
			pstmt.setString(3, productId);
			
			
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
	public List<OrderDetailVO> getAllReview() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO odVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_REVIEW);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				odVO = new OrderDetailVO();
				odVO.setOrderId(rs.getInt("ORDER_ID"));
				odVO.setProductId(rs.getString("PRODUCT_ID"));
				odVO.setProductReview(rs.getString("PRODUCT_REVIEW"));
				odVO.setProductReviewTS(rs.getTimestamp("PRODUCT_REVIEW_TS"));
				odVO.setProductReviewStatus(rs.getInt("PRODUCT_REVIEW_STATUS"));
				list.add(odVO);
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
	
	@Override
	public List<OrderDetailVO> getReviewById(String productId) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(REVIEW_BY_ID);
			pstmt.setString(1, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderId(rs.getInt("ORDER_ID"));
				orderDetailVO.setProductId(rs.getString("PRODUCT_ID"));
				orderDetailVO.setProductReview(rs.getString("PRODUCT_REVIEW"));
				orderDetailVO.setProductReviewTS(rs.getDate("PRODUCT_REVIEW_TS"));
				orderDetailVO.setProductReviewStatus(rs.getInt("PRODUCT_REVIEW_STATUS"));
				list.add(orderDetailVO);
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
		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
		
//		testing : insert()
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setOrderId(1);
//		orderDetailVO.setProductId("ENP0002");
//		orderDetailVO.setProductPrice(299);
//		orderDetailVO.setQuantity(1);
//		orderDetailVO.setProductReview("���յ���");
//		try {
//			orderDetailVO.setProductReviewPhoto(getPictureByteArray("/Users/jordan/desktop/cat.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		orderDetailVO.setProductReviewStatus(1);
//		dao.insert(orderDetailVO);
//		System.out.println("Statement Processed...");
		
//		testing : update()
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		orderDetailVO.setOrderId(1);
		orderDetailVO.setProductId("ENP0010");
		orderDetailVO.setProductPrice(2600);
		orderDetailVO.setQuantity(2);
		orderDetailVO.setProductReview("豬腳麵線");
		try {
			orderDetailVO.setProductReviewPhoto(getPictureByteArray("/Users/jordan/desktop/123.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO.setProductReviewStatus(1);
		dao.update(orderDetailVO);
		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		OrderDetailVO orderDetailVO = dao.getOne(4, "ENP0006");
//		System.out.println("ORDER_ID: " + orderDetailVO.getOrderId() );
//		System.out.println("PRODUCT_ID: " + orderDetailVO.getProductId() );
//		System.out.println("PRODUCT_PRICE: " + orderDetailVO.getProductPrice());
//		System.out.println("QUANTITY: " + orderDetailVO.getQuantity());
//		System.out.println("PRODUCT_REVIEW: " + orderDetailVO.getProductReview());
//		System.out.println("PRODUCT_REVIEW_PHOTO: " + orderDetailVO.getProductReviewPhoto());
//		System.out.println("PRODUCT_REVIEW_TS: " + orderDetailVO.getProductReviewTS());
//		System.out.println("PRODUCT_REVIEW_STATUS: " +  orderDetailVO.getProductReviewStatus());
		
//		testing : getAll()
//		List<OrderDetailVO> list = dao.getAll(4);
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println("ORDER_ID: " + orderDetailVO.getOrderId());
//			System.out.println("PRODUCT_ID: " + orderDetailVO.getProductId() );
//			System.out.println("PRODUCT_PRICE: " + orderDetailVO.getProductPrice());
//			System.out.println("QUANTITY: " + orderDetailVO.getQuantity());
//			System.out.println("PRODUCT_REVIEW: " + orderDetailVO.getProductReview());
//			System.out.println("PRODUCT_REVIEW_PHOTO: " + orderDetailVO.getProductReviewPhoto());
//			System.out.println("PRODUCT_REVIEW_TS: " + orderDetailVO.getProductReviewTS());
//			System.out.println("PRODUCT_REVIEW_STATUS: " +  orderDetailVO.getProductReviewStatus());
//			System.out.println("-----------------------------------");
//		}	
		
//		testing : getReviewByProductId()
//		List<OrderDetailVO> list = dao.getReviewByProductId("ENP0003");
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println("ORDER_ID: " + orderDetailVO.getOrderId());
//			System.out.println("PRODUCT_ID: " + orderDetailVO.getProductId() );
//			System.out.println("PRODUCT_PRICE: " + orderDetailVO.getProductPrice());
//			System.out.println("QUANTITY: " + orderDetailVO.getQuantity());
//			System.out.println("PRODUCT_REVIEW: " + orderDetailVO.getProductReview());
//			System.out.println("PRODUCT_REVIEW_PHOTO: " + orderDetailVO.getProductReviewPhoto());
//			System.out.println("PRODUCT_REVIEW_TS: " + orderDetailVO.getProductReviewTS());
//			System.out.println("PRODUCT_REVIEW_STATUS: " +  orderDetailVO.getProductReviewStatus());
//			System.out.println("-----------------------------------");
//		}	
	}

}
