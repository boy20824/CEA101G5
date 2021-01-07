package com.storechardetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menu.model.MenuVO;

public class StoreCharDetailDAO implements StoreCharDetail_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO store_char_detail (store_char, store_char_name) VALUES ('SCHAR' || LPAD(SEQ_STORE_CHAR.nextval,6,'0'), ?) ";
//	private static final String UPDATE = "UPDATE store_char_detail SET store_char_name=? where store_char=?";
	private static final String GET_ONE_STMT = "SELECT * FROM store_char_detail where store_char=?";
	private static final String GET_ALL_STMT = "SELECT * FROM store_char_detail ";
	// 新增
	@Override
	public void insert(StoreCharDetailVO storeCharDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, storeCharDetailVO.getStoreCharName());

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

	// 修改 不可修改
//	@Override
	public void update(StoreCharDetailVO storeCharDetailVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, storechardetailVO.getStorecharname());
//			pstmt.setString(2, storechardetailVO.getStorechar());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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
	}
	@Override
	public StoreCharDetailVO findByPrimaryKey(String storeChar) {
		StoreCharDetailVO storeCharDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeChar);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeCharDetailVO = new StoreCharDetailVO();
				storeCharDetailVO.setStoreChar(rs.getString("STORE_CHAR"));
				storeCharDetailVO.setStoreCharName(rs.getString("STORE_CHAR_NAME"));
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
		return storeCharDetailVO;
	}
	
	public List<StoreCharDetailVO> getAll(){
		List<StoreCharDetailVO> list = new ArrayList<StoreCharDetailVO>();
		StoreCharDetailVO storeCharDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeCharDetailVO = new StoreCharDetailVO();
				storeCharDetailVO = new StoreCharDetailVO();
				storeCharDetailVO.setStoreChar(rs.getString("STORE_CHAR"));
				storeCharDetailVO.setStoreCharName(rs.getString("STORE_CHAR_NAME"));
				list.add(storeCharDetailVO); // Store the row in the list
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


	public static void main(String[] args) {
		StoreCharDetailDAO dao = new StoreCharDetailDAO();

		// 新增
//		StoreCharDetailVO storecdVO1 = new StoreCharDetailVO();
//		storecdVO1.setStoreCharName("印度料理");
//		dao.insert(storecdVO1);

		// 修改 不得修改
//		StoreCharDetailVO storecdVO2 = new StoreCharDetailVO();
//		storecdVO2.setStorechar("SCHAR000010");
//		storecdVO2.setStorecharname("麻辣辣辣");
//		dao.update(storecdVO2);

		
//		查單筆
//		StoreCharDetailVO storeCharDetailVO = new StoreCharDetailVO();
//		storeCharDetailVO  = dao.findByPrimaryKey("SCHAR000005");
//		
//		System.out.print(storeCharDetailVO.getStoreChar() + ",");
//		System.out.print(storeCharDetailVO.getStoreCharName() );


		// 查詢全部
		List<StoreCharDetailVO> list = dao.getAll();
		for (StoreCharDetailVO storeCharDetailVO : list) {
		System.out.print(storeCharDetailVO.getStoreChar() + ",");
		System.out.println(storeCharDetailVO.getStoreCharName() );
		System.out.println("---------------------");
		}
	}
}
