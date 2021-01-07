package com.restaurantcmt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodorder.model.FoodOrderDAO;
import com.foodorder.model.FoodOrderVO;

public class RestaurantCmtDAO implements RestaurantCmt_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO RESTAURANT_CMT (STORE_CMT_ID,STORE_ID,MEM_PHONE,STORE_CMT_CONTENT,STORE_RATING,STORE_CMT_STATUS) VALUES (('SCMT'||LPAD(SEQ_STORE_CMT_ID.NEXTVAL,6,'0')), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT STORE_CMT_ID,STORE_ID,MEM_PHONE,STORE_CMT_CONTENT,STORE_RATING,STORE_CMT_STATUS from RESTAURANT_CMT where MEM_PHONE = ? order by STORE_CMT_ID";
	private static final String GET_ONE_STMT = "SELECT STORE_CMT_ID,STORE_ID,MEM_PHONE,STORE_CMT_CONTENT,STORE_RATING,STORE_CMT_STATUS from RESTAURANT_CMT where STORE_CMT_ID = ?";
	private static final String DELETE = "DELETE FROM RESTAURANT_CMT where STORE_CMT_ID = ?";
	private static final String UPDATE = "UPDATE RESTAURANT_CMT set STORE_CMT_CONTENT=?, STORE_RATING=? ,STORE_CMT_STATUS=? where STORE_CMT_ID = ?";

	@Override
	public void insert(RestaurantCmtVO restaurantCmtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, restaurantCmtVO.getStoreId());
			pstmt.setString(2, restaurantCmtVO.getMemPhone());
			pstmt.setString(3, restaurantCmtVO.getStoreCmtContent());
			pstmt.setInt(4, restaurantCmtVO.getStoreRating());
			pstmt.setInt(5, restaurantCmtVO.getStoreCmtStatus());

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
	public void update(RestaurantCmtVO restaurantCmtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurantCmtVO.getStoreCmtContent());
			pstmt.setInt(2, restaurantCmtVO.getStoreRating());
			pstmt.setInt(3, restaurantCmtVO.getStoreCmtStatus());
			pstmt.setString(4, restaurantCmtVO.getStoreCmtId());

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
	public void delete(String storeCmtId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, storeCmtId);

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
	public RestaurantCmtVO findByPrimaryKey(String storeId,String memPhone) {

		RestaurantCmtVO restaurantCmtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantCmtVO = new RestaurantCmtVO();
				restaurantCmtVO.setStoreCmtId(rs.getString("STORE_CMT_ID"));
				restaurantCmtVO.setStoreId(rs.getString("STORE_ID"));
				restaurantCmtVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantCmtVO.setStoreCmtContent(rs.getString("STORE_CMT_CONTENT"));
				restaurantCmtVO.setStoreRating(rs.getInt("STORE_RATING"));
				restaurantCmtVO.setStoreCmtStatus(rs.getInt("STORE_CMT_STATUS"));
				
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
		return restaurantCmtVO;
	}

	@Override
	public List<RestaurantCmtVO> getAll(String memPhone) {
		List<RestaurantCmtVO> list = new ArrayList<RestaurantCmtVO>();
		RestaurantCmtVO restaurantCmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1,memPhone);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurantCmtVO = new RestaurantCmtVO();
				restaurantCmtVO.setStoreCmtId(rs.getString("STORE_CMT_ID"));
				restaurantCmtVO.setStoreId(rs.getString("STORE_ID"));
				restaurantCmtVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantCmtVO.setStoreCmtContent(rs.getString("STORE_CMT_CONTENT"));
				restaurantCmtVO.setStoreRating(rs.getInt("STORE_RATING"));
				restaurantCmtVO.setStoreCmtStatus(rs.getInt("STORE_CMT_STATUS"));
				list.add(restaurantCmtVO); // Store the row in the list
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

		RestaurantCmtDAO dao = new RestaurantCmtDAO();

//		 新增
		 RestaurantCmtVO restaurantCmtVO = new RestaurantCmtVO();
		 restaurantCmtVO.setStoreId("S000002");
		 restaurantCmtVO.setMemPhone("0921842858");
		 restaurantCmtVO.setStoreCmtContent("垃圾餐餐廳");
		 restaurantCmtVO.setStoreRating(1);
		 restaurantCmtVO.setStoreCmtStatus(0);
		 dao.insert(restaurantCmtVO);


		// 修改
//		RestaurantCmtVO restaurantCmtVO = new RestaurantCmtVO();
//		restaurantCmtVO.setStoreCmtContent("真D難吃");
//		restaurantCmtVO.setStoreRating(0);
//		restaurantCmtVO.setStoreCmtStatus(1);
//		restaurantCmtVO.setStoreCmtId("SCMT000021");		
//		dao.update(restaurantCmtVO);

//
//		// 刪除
//		dao.delete("SCMT000021");

//
//		// 查詢單筆
//		RestaurantCmtVO restaurantCmtVO = new RestaurantCmtVO();
		
//		restaurantCmtVO  = dao.findByPrimaryKey("SCMT000022");
//		
//		System.out.print(restaurantCmtVO.getStoreCmtId() + ",");
//		System.out.print(restaurantCmtVO.getStoreId() + ",");
//		System.out.print(restaurantCmtVO.getMemPhone() + ",");
//		System.out.print(restaurantCmtVO.getStoreCmtContent() + ",");
//		System.out.print(restaurantCmtVO.getStoreRating() + ",");
//		System.out.println(restaurantCmtVO.getStoreCmtStatus() + ",");
//		System.out.println("---------------------");
	
		// 查詢全部
		
//		List<RestaurantCmtVO> list = dao.getAll("0921842858");
//		for (RestaurantCmtVO restaurantCmtVO : list) {
//			System.out.print(restaurantCmtVO.getStoreCmtId() + ",");
//			System.out.print(restaurantCmtVO.getStoreId() + ",");
//			System.out.print(restaurantCmtVO.getMemPhone() + ",");
//			System.out.print(restaurantCmtVO.getStoreCmtContent() + ",");
//			System.out.print(restaurantCmtVO.getStoreRating() + ",");
//			System.out.println(restaurantCmtVO.getStoreCmtStatus() + ",");
//			System.out.println();
//		}
	}
}
