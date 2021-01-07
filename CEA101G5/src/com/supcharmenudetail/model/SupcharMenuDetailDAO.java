package com.supcharmenudetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodorderdetail.model.FoodOrderDetailDAO;
import com.foodorderdetail.model.FoodOrderDetailVO;

public class SupcharMenuDetailDAO implements SupcharMenuDetail_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO SUPCHAR_MENU_DETAIL (FOODORDER_ID,MENU_ID,MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT FOODORDER_ID,MENU_ID,MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID FROM SUPCHAR_MENU_DETAIL where FOODORDER_ID= ? order by MENU_ID";
	private static final String GET_ONE_STMT = 
		"SELECT FOODORDER_ID,MENU_ID,MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID FROM SUPCHAR_MENU_DETAIL where FOODORDER_ID = ? and MENU_ID = ? and MENU_SUPCHAR_ID = ? ";
	private static final String DELETE = 
		"DELETE FROM SUPCHAR_MENU_DETAIL where FOODORDER_ID = ? and MENU_ID = ? and MENU_SUPCHAR_ID = ?";
//	private static final String UPDATE =   不能修改
//		"UPDATE FOODORDER_DETAIL set FOODORDER_ID=?, MENU_ID=? where  FOODORDER_ID = ?";

	@Override
	public void insert(SupcharMenuDetailVO supcharMenuDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, supcharMenuDetailVO.getFoodOrderId());
			pstmt.setString(2, supcharMenuDetailVO.getMenuId());
			pstmt.setString(3, supcharMenuDetailVO.getMenuSupcharDetailId());
			pstmt.setString(4, supcharMenuDetailVO.getMenuSupcharId());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	//不得修改
	public void update(SupcharMenuDetailVO supcharMenuDetailVO) { 

//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, orderDetailVO.getMenuNum());
//			pstmt.setInt(2, orderDetailVO.getMenuPrice());
//			pstmt.setString(3, orderDetailVO.getMenuId());
//			pstmt.setString(4, orderDetailVO.getFoodOrderId());
//			
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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

	}

	@Override
	public void delete(String foodOrderId,String menuId,String menuSupcharId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, foodOrderId);
			pstmt.setString(2,menuId);
			pstmt.setString(3,menuSupcharId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public SupcharMenuDetailVO findByPrimaryKey(String foodOrderId,String menuId,String menuSupcharId) {

		SupcharMenuDetailVO supcharMenuDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, foodOrderId);
			pstmt.setString(2,menuId);
			pstmt.setString(3,menuSupcharId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				supcharMenuDetailVO = new SupcharMenuDetailVO();
				supcharMenuDetailVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
				supcharMenuDetailVO.setMenuId(rs.getString("MENU_ID"));
				supcharMenuDetailVO.setMenuSupcharDetailId(rs.getString("MENU_SUPCHAR_DETAIL_ID"));
				supcharMenuDetailVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return supcharMenuDetailVO;
	}

	@Override
	public List<SupcharMenuDetailVO> getAll(String  foodOrderId) {
		List<SupcharMenuDetailVO> list = new ArrayList<SupcharMenuDetailVO>();
		SupcharMenuDetailVO supcharMenuDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1, foodOrderId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				supcharMenuDetailVO = new SupcharMenuDetailVO();
				supcharMenuDetailVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
				supcharMenuDetailVO.setMenuId(rs.getString("MENU_ID"));
				supcharMenuDetailVO.setMenuSupcharDetailId(rs.getString("MENU_SUPCHAR_DETAIL_ID"));
				supcharMenuDetailVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				list.add(supcharMenuDetailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		SupcharMenuDetailDAO dao = new SupcharMenuDetailDAO();
		SupcharMenuDetailVO supcharMenuDetailVO = new SupcharMenuDetailVO();

//		 新增
		
//		supcharMenuDetailVO.setFoodOrderId("OD000003");
//		supcharMenuDetailVO.setMenuId("M000006");
//		supcharMenuDetailVO.setMenuSupcharDetailId("MSD000002");
//		supcharMenuDetailVO.setMenuSupcharId("MS000002");
//		dao.insert(supcharMenuDetailVO);
		
		


		// 修改
		// 不得修改
//		orderDetailVO.setMenuNum(996);;
//		orderDetailVO.setMenuPrice(9999);
//		orderDetailVO.setMenuId("M000001");
//		orderDetailVO.setFoodOrderId("OD000007");
//		dao.update(orderDetailVO);

//
//		// 刪除
		
//		dao.delete("OD000002","M000006","MS000001");

	

//
//		// 查詢單筆
//		supcharMenuDetailVO = dao.findByPrimaryKey("OD000003","M000006","MS000002");
//		
//		System.out.print(supcharMenuDetailVO.getFoodOrderId() + ",");
//		System.out.print(supcharMenuDetailVO.getMenuId() + ",");
//		System.out.print(supcharMenuDetailVO.getMenuSupcharDetailId() + ",");
//		System.out.println(supcharMenuDetailVO.getMenuSupcharId() + ",");
//		System.out.println("---------------------");

//		// 查詢全部
//		List<SupcharMenuDetailVO> list = dao.getAll("OD000003");
//		for (SupcharMenuDetailVO supcharMenuDetailVO : list) {
//		System.out.print(supcharMenuDetailVO.getFoodOrderId() + ",");
//		System.out.print(supcharMenuDetailVO.getMenuId() + ",");
//		System.out.print(supcharMenuDetailVO.getMenuSupcharDetailId() + ",");
//		System.out.println(supcharMenuDetailVO.getMenuSupcharId() + ",");
//		System.out.println("---------------------");
//		}
	
	}
	
}
