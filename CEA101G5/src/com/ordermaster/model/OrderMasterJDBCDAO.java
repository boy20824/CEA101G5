package com.ordermaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.orderdetail.model.*;
import com.product.model.ProductVO;

import util.Util;

public class OrderMasterJDBCDAO implements OrderMasterDAO_Interface {
	
	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_MASTER (ORDER_ID, MEM_PHONE, RECIPIENT_NAME, RECIPIENT_MOB_NUMBER, RECIPIENT_TEL_NUMBER, RECIPIENT_EMAIL, BUSINESS_NUMBER, DELIVERY_METHOD, DELIVERY_ADDRESS, ORDER_MEMO, INVOICE_PRICE, ORDER_STATUS)\n" + 
		"VALUES (SEQ_ORDER_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE ORDER_MASTER SET MEM_PHONE = ?, RECIPIENT_NAME = ?, RECIPIENT_MOB_NUMBER = ?, RECIPIENT_TEL_NUMBER = ?, RECIPIENT_EMAIL = ?, BUSINESS_NUMBER = ?, DELIVERY_METHOD = ?, DELIVERY_ADDRESS = ?, ORDER_MEMO = ?, INVOICE_PRICE = ?, ORDER_STATUS = ? WHERE ORDER_ID = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ORDER_MASTER WHERE ORDER_ID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ORDER_MASTER ORDER BY ORDER_ID";
	
	private static final String UPDATEOM = 
		"UPDATE ORDER_MASTER SET ORDER_STATUS = ? WHERE ORDER_ID = ?";
	private static final String GET_ALL_BYMEMPHONE = 
		"SELECT * FROM ORDER_MASTER WHERE MEM_PHONE = ? ORDER BY ORDER_ID DESC";

	@Override
	public void insert(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderMasterVO.getMemPhone());
			pstmt.setString(2, orderMasterVO.getRecipientName());
			pstmt.setString(3, orderMasterVO.getRecipientMobNumber());
			pstmt.setString(4, orderMasterVO.getRecipientTelNumber());
			pstmt.setString(5, orderMasterVO.getRecipientEmail());
			pstmt.setString(6, orderMasterVO.getBusinessNumber());
			pstmt.setInt(7, orderMasterVO.getDeliveryMethod());
			pstmt.setString(8, orderMasterVO.getDeliveryAddress());
			pstmt.setString(9, orderMasterVO.getOrderMemo());
			pstmt.setString(10, orderMasterVO.getInvoicePrice());
			pstmt.setInt(11, orderMasterVO.getOrderStatus());
			
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
	public void update(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, orderMasterVO.getMemPhone());
			pstmt.setString(2, orderMasterVO.getRecipientName());
			pstmt.setString(3, orderMasterVO.getRecipientMobNumber());
			pstmt.setString(4, orderMasterVO.getRecipientTelNumber());
			pstmt.setString(5, orderMasterVO.getRecipientEmail());
			pstmt.setString(6, orderMasterVO.getBusinessNumber());
			pstmt.setInt(7, orderMasterVO.getDeliveryMethod());
			pstmt.setString(8, orderMasterVO.getDeliveryAddress());
			pstmt.setString(9, orderMasterVO.getOrderMemo());
			pstmt.setString(10, orderMasterVO.getInvoicePrice());
			pstmt.setInt(11, orderMasterVO.getOrderStatus());
			pstmt.setInt(12, orderMasterVO.getOrderId());
			
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
	public OrderMasterVO getOne(Integer orderId) {
		OrderMasterVO orderMasterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderId(rs.getInt("ORDER_ID"));
				orderMasterVO.setOrderDate(rs.getDate("ORDER_DATE"));
				orderMasterVO.setMemPhone(rs.getString("MEM_PHONE"));
				orderMasterVO.setRecipientName(rs.getString("RECIPIENT_NAME"));
				orderMasterVO.setRecipientMobNumber(rs.getString("RECIPIENT_MOB_NUMBER"));
				orderMasterVO.setRecipientTelNumber(rs.getString("RECIPIENT_TEL_NUMBER"));
				orderMasterVO.setRecipientEmail(rs.getString("RECIPIENT_EMAIL"));
				orderMasterVO.setBusinessNumber(rs.getString("BUSINESS_NUMBER"));
				orderMasterVO.setDeliveryMethod(rs.getInt("DELIVERY_METHOD"));
				orderMasterVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				orderMasterVO.setOrderMemo(rs.getNString("ORDER_MEMO"));
				orderMasterVO.setInvoicePrice(rs.getString("INVOICE_PRICE"));
				orderMasterVO.setInvoicePaidDate(rs.getDate("INVOICE_PAID_DATE"));
				orderMasterVO.setDeliveryTime(rs.getDate("DELIVERY_TIME"));
				orderMasterVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
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
		
		return orderMasterVO;
	}

	@Override
	public List<OrderMasterVO> getAll() {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderId(rs.getInt("ORDER_ID"));
				orderMasterVO.setOrderDate(rs.getDate("ORDER_DATE"));
				orderMasterVO.setMemPhone(rs.getString("MEM_PHONE"));
				orderMasterVO.setRecipientName(rs.getString("RECIPIENT_NAME"));
				orderMasterVO.setRecipientMobNumber(rs.getString("RECIPIENT_MOB_NUMBER"));
				orderMasterVO.setRecipientTelNumber(rs.getString("RECIPIENT_TEL_NUMBER"));
				orderMasterVO.setRecipientEmail(rs.getString("RECIPIENT_EMAIL"));
				orderMasterVO.setBusinessNumber(rs.getString("BUSINESS_NUMBER"));
				orderMasterVO.setDeliveryMethod(rs.getInt("DELIVERY_METHOD"));
				orderMasterVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				orderMasterVO.setOrderMemo(rs.getNString("ORDER_MEMO"));
				orderMasterVO.setInvoicePrice(rs.getString("INVOICE_PRICE"));
				orderMasterVO.setInvoicePaidDate(rs.getDate("INVOICE_PAID_DATE"));
				orderMasterVO.setDeliveryTime(rs.getDate("DELIVERY_TIME"));
				orderMasterVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				list.add(orderMasterVO);
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
	public void insertWithOrderDetail(OrderMasterVO orderMasterVO, List<ProductVO> list) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			
			con.setAutoCommit(false);
			
			String cols[] = {"ORDER_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, orderMasterVO.getMemPhone());
			pstmt.setString(2, orderMasterVO.getRecipientName());
			pstmt.setString(3, orderMasterVO.getRecipientMobNumber());
			pstmt.setString(4, orderMasterVO.getRecipientTelNumber());
			pstmt.setString(5, orderMasterVO.getRecipientEmail());
			pstmt.setString(6, orderMasterVO.getBusinessNumber());
			pstmt.setInt(7, orderMasterVO.getDeliveryMethod());
			pstmt.setString(8, orderMasterVO.getDeliveryAddress());
			pstmt.setString(9, orderMasterVO.getOrderMemo());
			pstmt.setString(10, orderMasterVO.getInvoicePrice());
			pstmt.setInt(11, orderMasterVO.getOrderStatus());
			pstmt.executeUpdate();
			
			Integer nextOrderId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextOrderId = rs.getInt(1);
				System.out.println("Success: Auto-Generated Key(ORDER_ID: " + nextOrderId  + ")");
			} else {
				System.out.println("Failure: Auto-Generated Key(ORDER_ID)");
			}
			rs.close();
			
			OrderDetailJDBCDAO orderDetailJDBCDAO = new OrderDetailJDBCDAO();
			for (ProductVO productVO : list) {
				productVO.setOrderId(nextOrderId);
				orderDetailJDBCDAO.insert(productVO, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException exception) {
					e.printStackTrace();
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
	public void updateOM(Integer orderStatus,Integer orderId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATEOM);
			
			pstmt.setInt(1, orderStatus);
			pstmt.setInt(2, orderId);
			
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
	
	public List<OrderMasterVO> getAllByMemPhone(String memPhone) {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BYMEMPHONE);
			pstmt.setString(1, memPhone);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderId(rs.getInt("ORDER_ID"));
				orderMasterVO.setOrderDate(rs.getDate("ORDER_DATE"));
				orderMasterVO.setMemPhone(rs.getString("MEM_PHONE"));
				orderMasterVO.setRecipientName(rs.getString("RECIPIENT_NAME"));
				orderMasterVO.setRecipientMobNumber(rs.getString("RECIPIENT_MOB_NUMBER"));
				orderMasterVO.setRecipientTelNumber(rs.getString("RECIPIENT_TEL_NUMBER"));
				orderMasterVO.setRecipientEmail(rs.getString("RECIPIENT_EMAIL"));
				orderMasterVO.setBusinessNumber(rs.getString("BUSINESS_NUMBER"));
				orderMasterVO.setDeliveryMethod(rs.getInt("DELIVERY_METHOD"));
				orderMasterVO.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
				orderMasterVO.setOrderMemo(rs.getNString("ORDER_MEMO"));
				orderMasterVO.setInvoicePrice(rs.getString("INVOICE_PRICE"));
				orderMasterVO.setInvoicePaidDate(rs.getDate("INVOICE_PAID_DATE"));
				orderMasterVO.setDeliveryTime(rs.getDate("DELIVERY_TIME"));
				orderMasterVO.setOrderStatus(rs.getInt("ORDER_STATUS"));
				list.add(orderMasterVO);
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
	
	public static void main(String[] args) {
		OrderMasterJDBCDAO dao = new OrderMasterJDBCDAO();
		
//		testing : insert()
//		OrderMasterVO orderMasterVO = new OrderMasterVO();
//		orderMasterVO.setMemPhone("0921842850");
//		orderMasterVO.setRecipientName("���իȤ�W��");
//		orderMasterVO.setRecipientMobNumber("0988-8888888");
//		orderMasterVO.setRecipientTelNumber("04-88888888");
//		orderMasterVO.setRecipientEmail("test@testmail.com");
//		orderMasterVO.setBusinessNumber("88888888");
//		orderMasterVO.setDeliveryMethod(0);
//		orderMasterVO.setDeliveryAddress("���զ���a�}");
//		orderMasterVO.setOrderMemo("�H�U�`�N�ƶ�");
//		orderMasterVO.setInvoicePrice("299");
//		orderMasterVO.setOrderStatus(3);
//		dao.insert(orderMasterVO);
//		System.out.println("Statement Processed...");
		
//		testing : update()
//		OrderMasterVO orderMasterVO = new OrderMasterVO();
//		orderMasterVO.setOrderId(3);
//		orderMasterVO.setMemPhone("0921842850");
//		orderMasterVO.setRecipientName("���իȤ�W��");
//		orderMasterVO.setRecipientMobNumber("0988-8888888");
//		orderMasterVO.setRecipientTelNumber("04-88888888");
//		orderMasterVO.setRecipientEmail("test@testmail.com");
//		orderMasterVO.setBusinessNumber("88888888");
//		orderMasterVO.setDeliveryMethod(0);
//		orderMasterVO.setDeliveryAddress("���զ���a�}");
//		orderMasterVO.setOrderMemo("�H�U�`�N�ƶ�");
//		orderMasterVO.setInvoicePrice("299");
//		orderMasterVO.setOrderStatus(3);
//		dao.update(orderMasterVO);
//		System.out.println("Statement Processed...");
		
//		testing : getOne()
//		Integer orderId = new Integer(1);
//		OrderMasterVO orderMasterVO = dao.getOne(orderId);
//		System.out.println("ORDER_ID: " + orderMasterVO.getOrderId());
//		System.out.println("ORDER_DATE: " + orderMasterVO.getOrderDate());
//		System.out.println("MEM_PHONE: " + orderMasterVO.getMemPhone());
//		System.out.println("RECIPIENT_NAME: " + orderMasterVO.getRecipientName());
//		System.out.println("RECIPIENT_MOB_NUMBER: " + orderMasterVO.getRecipientMobNumber());
//		System.out.println("RECIPIENT_TEL_NUMBER: " + orderMasterVO.getRecipientTelNumber());
//		System.out.println("RECIPIENT_EMAIL: " + orderMasterVO.getRecipientEmail());
//		System.out.println("BUSINESS_NUMBER: " + orderMasterVO.getBusinessNumber());
//		System.out.println("DELIVERY_METHOD: " + orderMasterVO.getDeliveryMethod());
//		System.out.println("DELIVERY_ADDRESS: " + orderMasterVO.getDeliveryAddress());
//		System.out.println("ORDER_MEMO: " + orderMasterVO.getOrderMemo());
//		System.out.println("INVOICE_PRICE: " + orderMasterVO.getInvoicePrice());
//		System.out.println("INVOICE_PAID_DATE: " + orderMasterVO.getInvoicePaidDate());
//		System.out.println("DELIVERY_TIME: " + orderMasterVO.getDeliveryTime());
//		System.out.println("ORDER_STATUS: " + orderMasterVO.getOrderStatus());
		
//		testing : getAll()
//		List<OrderMasterVO> list = dao.getAll();
//		for (OrderMasterVO orderMasterVO : list) {
//			System.out.println("ORDER_ID: " + orderMasterVO.getOrderId());
//			System.out.println("ORDER_DATE: " + orderMasterVO.getOrderDate());
//			System.out.println("MEM_PHONE: " + orderMasterVO.getMemPhone());
//			System.out.println("RECIPIENT_NAME: " + orderMasterVO.getRecipientName());
//			System.out.println("RECIPIENT_MOB_NUMBER: " + orderMasterVO.getRecipientMobNumber());
//			System.out.println("RECIPIENT_TEL_NUMBER: " + orderMasterVO.getRecipientTelNumber());
//			System.out.println("RECIPIENT_EMAIL: " + orderMasterVO.getRecipientEmail());
//			System.out.println("BUSINESS_NUMBER: " + orderMasterVO.getBusinessNumber());
//			System.out.println("DELIVERY_METHOD: " + orderMasterVO.getDeliveryMethod());
//			System.out.println("DELIVERY_ADDRESS: " + orderMasterVO.getDeliveryAddress());
//			System.out.println("ORDER_MEMO: " + orderMasterVO.getOrderMemo());
//			System.out.println("INVOICE_PRICE: " + orderMasterVO.getInvoicePrice());
//			System.out.println("INVOICE_PAID_DATE: " + orderMasterVO.getInvoicePaidDate());
//			System.out.println("DELIVERY_TIME: " + orderMasterVO.getDeliveryTime());
//			System.out.println("ORDER_STATUS: " + orderMasterVO.getOrderStatus());
//			System.out.println("-----------------------------------");
//		}
		
//		testing : insertWithOrderDetail()
//		OrderMasterVO orderMasterVO = new OrderMasterVO();
//		orderMasterVO.setMemPhone("0921842850");
//		orderMasterVO.setRecipientName("���զW��");
//		orderMasterVO.setRecipientMobNumber("0988888888");
//		orderMasterVO.setRecipientTelNumber("0488888888");
//		orderMasterVO.setRecipientEmail("test@gmail.com");
//		orderMasterVO.setBusinessNumber("88888888");
//		orderMasterVO.setDeliveryMethod(0);
//		orderMasterVO.setDeliveryAddress("�x�����_�ٰ�");
//		orderMasterVO.setOrderMemo("�кɧ֥X�f");
//		orderMasterVO.setInvoicePrice("19999");
//		orderMasterVO.setOrderStatus(1);
//		
//		ProductVO productVO = new ProductVO();
//		productVO.setProductId("ENP0001");
//		productVO.setProductName("���ղ��~");
//		productVO.setProductDescription("���ղ��~�ԭz");
//		productVO.setProductMSRP(9999);
//		productVO.setProductPrice(9999);
//		productVO.setProductQtySold(9999);
//		productVO.setProductQty(10);
//		productVO.setCategoryId(1);
//		productVO.setProductStatus(1);
//		
//		List<ProductVO> buyList = new Vector<ProductVO>();
//		buyList.add(productVO);
//		
//		dao.insertWithOrderDetail(orderMasterVO, buyList);
//		System.out.println("Statement Processed...");
		
//		testing : getAllByMemPhone()
		List<OrderMasterVO> list = dao.getAllByMemPhone("0917780721");
		for (OrderMasterVO orderMasterVO : list) {
			System.out.println("ORDER_ID: " + orderMasterVO.getOrderId());
			System.out.println("ORDER_DATE: " + orderMasterVO.getOrderDate());
			System.out.println("MEM_PHONE: " + orderMasterVO.getMemPhone());
			System.out.println("RECIPIENT_NAME: " + orderMasterVO.getRecipientName());
			System.out.println("RECIPIENT_MOB_NUMBER: " + orderMasterVO.getRecipientMobNumber());
			System.out.println("RECIPIENT_TEL_NUMBER: " + orderMasterVO.getRecipientTelNumber());
			System.out.println("RECIPIENT_EMAIL: " + orderMasterVO.getRecipientEmail());
			System.out.println("BUSINESS_NUMBER: " + orderMasterVO.getBusinessNumber());
			System.out.println("DELIVERY_METHOD: " + orderMasterVO.getDeliveryMethod());
			System.out.println("DELIVERY_ADDRESS: " + orderMasterVO.getDeliveryAddress());
			System.out.println("ORDER_MEMO: " + orderMasterVO.getOrderMemo());
			System.out.println("INVOICE_PRICE: " + orderMasterVO.getInvoicePrice());
			System.out.println("INVOICE_PAID_DATE: " + orderMasterVO.getInvoicePaidDate());
			System.out.println("DELIVERY_TIME: " + orderMasterVO.getDeliveryTime());
			System.out.println("ORDER_STATUS: " + orderMasterVO.getOrderStatus());
			System.out.println("-----------------------------------");
		}
		
	}

}
