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
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setOrderId(1);
//		orderDetailVO.setProductId("ENP0010");
//		orderDetailVO.setProductPrice(2600);
//		orderDetailVO.setQuantity(2);
//		orderDetailVO.setProductReview("豬腳麵線");
//		try {
//			orderDetailVO.setProductReviewPhoto(getPictureByteArray("/Users/jordan/desktop/123.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		orderDetailVO.setProductReviewStatus(1);
//		dao.update(orderDetailVO);
//		System.out.println("Statement Processed...");
		
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
		
		

		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		orderDetailVO.setOrderId(1);
		orderDetailVO.setProductId("ENP0010");
		orderDetailVO.setProductPrice(2600);
		orderDetailVO.setQuantity(2);
		orderDetailVO.setProductReview("價格很划算, 是件值得購買的商品, CP值高！！ 推薦給大家參考～");
		try {
			orderDetailVO.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0001\\2.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO.setProductReviewStatus(1);
		dao.update(orderDetailVO);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
		orderDetailVO2.setOrderId(1);
		orderDetailVO2.setProductId("ENP0008");
		orderDetailVO2.setProductPrice(1999);
		orderDetailVO2.setQuantity(2);
		orderDetailVO2.setProductReview("價格很划算, 是件值得購買的商品, CP值高！！ 推薦給大家參考～");
		try {
			orderDetailVO2.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0008\\1.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO2.setProductReviewStatus(1);
		dao.update(orderDetailVO2);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO3 = new OrderDetailVO();
		orderDetailVO3.setOrderId(2);
		orderDetailVO3.setProductId("ENP0001");
		orderDetailVO3.setProductPrice(299);
		orderDetailVO3.setQuantity(1);
		orderDetailVO3.setProductReview("出貨快速, 包裝保護的非常好！！");
		try {
			orderDetailVO3.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0001\\5.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO3.setProductReviewStatus(1);
		dao.update(orderDetailVO3);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO4 = new OrderDetailVO();
		orderDetailVO4.setOrderId(3);
		orderDetailVO4.setProductId("ENP0003");
		orderDetailVO4.setProductPrice(329);
		orderDetailVO4.setQuantity(1);
		orderDetailVO4.setProductReview("超讚的商品品質, 超讚的CP值！");
		try {
			orderDetailVO4.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0003\\3.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO4.setProductReviewStatus(1);
		dao.update(orderDetailVO3);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO5 = new OrderDetailVO();
		orderDetailVO5.setOrderId(3);
		orderDetailVO5.setProductId("ENP0004");
		orderDetailVO5.setProductPrice(199);
		orderDetailVO5.setQuantity(1);
		orderDetailVO5.setProductReview("超讚的商品品質, 超讚的CP值！");
		try {
			orderDetailVO4.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0004\\3.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO5.setProductReviewStatus(1);
		dao.update(orderDetailVO5);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO6 = new OrderDetailVO();
		orderDetailVO6.setOrderId(3);
		orderDetailVO6.setProductId("ENP0005");
		orderDetailVO6.setProductPrice(390);
		orderDetailVO6.setQuantity(1);
		orderDetailVO6.setProductReview("超讚的商品品質, 超讚的CP值！");
		try {
			orderDetailVO6.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0005\\3.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO6.setProductReviewStatus(1);
		dao.update(orderDetailVO6);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO7 = new OrderDetailVO();
		orderDetailVO7.setOrderId(4);
		orderDetailVO7.setProductId("ENP0002");
		orderDetailVO7.setProductPrice(249);
		orderDetailVO7.setQuantity(1);
		orderDetailVO7.setProductReview("便宜的價格, 以後一定會在回購！！");
		try {
			orderDetailVO7.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0002\\4.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO7.setProductReviewStatus(1);
		dao.update(orderDetailVO7);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO8 = new OrderDetailVO();
		orderDetailVO8.setOrderId(4);
		orderDetailVO8.setProductId("ENP0006");
		orderDetailVO8.setProductPrice(3500);
		orderDetailVO8.setQuantity(1);
		orderDetailVO8.setProductReview("便宜的價格, 以後一定會在回購！！");
		try {
			orderDetailVO8.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0006\\4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO8.setProductReviewStatus(1);
		dao.update(orderDetailVO8);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailVO9 = new OrderDetailVO();
		orderDetailVO9.setOrderId(4);
		orderDetailVO9.setProductId("ENP0007");
		orderDetailVO9.setProductPrice(490);
		orderDetailVO9.setQuantity(1);
		orderDetailVO9.setProductReview("便宜的價格, 以後一定會在回購！！");
		try {
			orderDetailVO9.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0007\\4.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailVO9.setProductReviewStatus(1);
		dao.update(orderDetailVO9);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV10 = new OrderDetailVO();
		orderDetailV10.setOrderId(4);
		orderDetailV10.setProductId("ENP0009");
		orderDetailV10.setProductPrice(1400);
		orderDetailV10.setQuantity(1);
		orderDetailV10.setProductReview("便宜的價格, 以後一定會在回購！！");
		try {
			orderDetailV10.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0009\\4.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV10.setProductReviewStatus(1);
		dao.update(orderDetailV10);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV11 = new OrderDetailVO();
		orderDetailV11.setOrderId(5);
		orderDetailV11.setProductId("ENP0001");
		orderDetailV11.setProductPrice(299);
		orderDetailV11.setQuantity(1);
		orderDetailV11.setProductReview("出貨速度超快, 很推薦！！");
		try {
			orderDetailV11.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0001\\5.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV11.setProductReviewStatus(1);
		dao.update(orderDetailV11);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV12 = new OrderDetailVO();
		orderDetailV12.setOrderId(5);
		orderDetailV12.setProductId("ENP0002");
		orderDetailV12.setProductPrice(249);
		orderDetailV12.setQuantity(1);
		orderDetailV12.setProductReview("出貨速度超快, 很推薦！！");
		try {
			orderDetailV12.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0002\\5.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV12.setProductReviewStatus(1);
		dao.update(orderDetailV12);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV13 = new OrderDetailVO();
		orderDetailV13.setOrderId(6);
		orderDetailV13.setProductId("ENP0003");
		orderDetailV13.setProductPrice(329);
		orderDetailV13.setQuantity(3);
		orderDetailV13.setProductReview("顏色好看, 實體比想像中大, 外觀看起來還不錯！");
		try {
			orderDetailV13.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0003\\6.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV13.setProductReviewStatus(1);
		dao.update(orderDetailV13);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV14 = new OrderDetailVO();
		orderDetailV14.setOrderId(7);
		orderDetailV14.setProductId("ENP0005");
		orderDetailV14.setProductPrice(390);
		orderDetailV14.setQuantity(2);
		orderDetailV14.setProductReview("便宜又有質感！");
		try {
			orderDetailV14.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0005\\7.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV14.setProductReviewStatus(1);
		dao.update(orderDetailV14);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV15 = new OrderDetailVO();
		orderDetailV15.setOrderId(7);
		orderDetailV15.setProductId("ENP0004");
		orderDetailV15.setProductPrice(199);
		orderDetailV15.setQuantity(2);
		orderDetailV15.setProductReview("便宜又有質感！");
		try {
			orderDetailV15.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0004\\7.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV15.setProductReviewStatus(1);
		dao.update(orderDetailV15);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV16 = new OrderDetailVO();
		orderDetailV16.setOrderId(8);
		orderDetailV16.setProductId("ENP0002");
		orderDetailV16.setProductPrice(249);
		orderDetailV16.setQuantity(2);
		orderDetailV16.setProductReview("出貨速度快, 包裝良好！！");
		try {
			orderDetailV16.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0002\\8.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV16.setProductReviewStatus(1);
		dao.update(orderDetailV16);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV17 = new OrderDetailVO();
		orderDetailV17.setOrderId(9);
		orderDetailV17.setProductId("ENP0003");
		orderDetailV17.setProductPrice(329);
		orderDetailV17.setQuantity(2);
		orderDetailV17.setProductReview("出貨速度快, 包裝良好！！");
		try {
			orderDetailV17.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0003\\9.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV17.setProductReviewStatus(1);
		dao.update(orderDetailV17);
		System.out.println("Statement Processed...");
		
		OrderDetailVO orderDetailV18 = new OrderDetailVO();
		orderDetailV18.setOrderId(10);
		orderDetailV18.setProductId("ENP0010");
		orderDetailV18.setProductPrice(2600);
		orderDetailV18.setQuantity(10);
		orderDetailV18.setProductReview("再次回購！！老婆太愛吃了 =_=！");
		try {
			orderDetailV18.setProductReviewPhoto(getPictureByteArray("C:\\Users\\CJ02032Desktop\\EatNAK_Shop_Dummy\\ENP0010\\10.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		orderDetailV18.setProductReviewStatus(1);
		dao.update(orderDetailV18);
		System.out.println("Statement Processed...");
		
		
		

		
		
		
		
	}

}
