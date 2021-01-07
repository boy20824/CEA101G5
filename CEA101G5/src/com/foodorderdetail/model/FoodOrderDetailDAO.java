package com.foodorderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodorder.model.FoodOrderDAO;

public class FoodOrderDetailDAO implements FoodOrderDetail_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO FOODORDER_DETAIL (MENU_ID,FOODORDER_ID,MENU_NUM,MENU_PRICE) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT MENU_ID,FOODORDER_ID,MENU_NUM,MENU_PRICE FROM FOODORDER_DETAIL where FOODORDER_ID= ? order by FOODORDER_ID";
	private static final String GET_ONE_STMT = 
		"SELECT MENU_ID,FOODORDER_ID,MENU_NUM,MENU_PRICE FROM FOODORDER_DETAIL where MENU_ID = ? and FOODORDER_ID = ?";
	private static final String DELETE = 
		"DELETE FROM FOODORDER_DETAIL where MENU_ID = ? and FOODORDER_ID = ?";
	private static final String UPDATE = 
		"UPDATE FOODORDER_DETAIL set MENU_NUM=?, MENU_PRICE=? where  MENU_ID = ? and FOODORDER_ID = ?";

	@Override
	public void insert(FoodOrderDetailVO foodOrderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, foodOrderDetailVO.getMenuId());
			pstmt.setString(2, foodOrderDetailVO.getFoodOrderId());
			pstmt.setInt(3, foodOrderDetailVO.getMenuNum());
			pstmt.setInt(4, foodOrderDetailVO.getMenuPrice());
		

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
	public void insert2 (FoodOrderDetailVO foodOrderDetailVO , Connection con) {
		
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setString(1, foodOrderDetailVO.getMenuId());
			pstmt.setString(2, foodOrderDetailVO.getFoodOrderId());
			pstmt.setInt(3, foodOrderDetailVO.getMenuNum());
			pstmt.setInt(4, foodOrderDetailVO.getMenuPrice());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}
	
	
	
	
	@Override
	public void update(FoodOrderDetailVO foodOrderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, foodOrderDetailVO.getMenuNum());
			pstmt.setInt(2, foodOrderDetailVO.getMenuPrice());
			pstmt.setString(3, foodOrderDetailVO.getMenuId());
			pstmt.setString(4, foodOrderDetailVO.getFoodOrderId());
			
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
	public void delete(String menuId,String foodOrderId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, menuId);
			pstmt.setString(2,foodOrderId);

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
	public FoodOrderDetailVO findByPrimaryKey(String menuId,String foodOrderId) {

		FoodOrderDetailVO foodOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, menuId);
			pstmt.setString(2,foodOrderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// orderDetailVO 也稱為 Domain objects
				foodOrderDetailVO = new FoodOrderDetailVO();
				foodOrderDetailVO.setMenuId(rs.getString("MENU_ID"));
				foodOrderDetailVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
				foodOrderDetailVO.setMenuNum(rs.getInt("MENU_NUM"));
				foodOrderDetailVO.setMenuPrice(rs.getInt("MENU_PRICE"));
				
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
		return foodOrderDetailVO;
	}

	@Override
	public List<FoodOrderDetailVO> getAll(String  foodOrderId) {
		List<FoodOrderDetailVO> list = new ArrayList<FoodOrderDetailVO>();
		FoodOrderDetailVO foodOrderDetailVO = null;

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
				foodOrderDetailVO = new FoodOrderDetailVO();
				foodOrderDetailVO.setMenuId(rs.getString("MENU_ID"));
				foodOrderDetailVO.setFoodOrderId(rs.getString("FOODORDER_ID"));
				foodOrderDetailVO.setMenuNum(rs.getInt("MENU_NUM"));
				foodOrderDetailVO.setMenuPrice(rs.getInt("MENU_PRICE"));
				list.add(foodOrderDetailVO); // Store the row in the list
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

		FoodOrderDetailDAO dao = new FoodOrderDetailDAO();
		FoodOrderDetailVO foodOrderDetailVO = new FoodOrderDetailVO();
//
//		// 新增
//		
//		
		foodOrderDetailVO.setMenuId("M000001");
		foodOrderDetailVO.setFoodOrderId("OD000007");
		foodOrderDetailVO.setMenuNum(2);
		foodOrderDetailVO.setMenuPrice(2000);
		dao.insert(foodOrderDetailVO);


		// 修改

//		foodOrderDetailVO.setMenuNum(996);;
//		foodOrderDetailVO.setMenuPrice(9999);
//		foodOrderDetailVO.setMenuId("M000007");
//		foodOrderDetailVO.setFoodOrderId("OD000006");
//		dao.update(foodOrderDetailVO);

//
//		// 刪除
		
//		dao.delete("M000001","OD000007");
	

//
//		// 查詢單筆
//		foodOrderDetailVO = dao.findByPrimaryKey("M000007","OD000006");
//		
//		System.out.print(foodOrderDetailVO.getMenuId() + ",");
//		System.out.print(foodOrderDetailVO.getFoodOrderId() + ",");
//		System.out.print(foodOrderDetailVO.getMenuNum() + ",");
//		System.out.println(foodOrderDetailVO.getMenuPrice() + ",");
//		System.out.println("---------------------");

		// 查詢全部
//		List<FoodOrderDetailVO> list = dao.getAll("OD000001");
//		for (FoodOrderDetailVO foodOrderDetailVO : list) {
//		System.out.print(foodOrderDetailVO.getMenuId() + ",");
//		System.out.print(foodOrderDetailVO.getFoodOrderId() + ",");
//		System.out.print(foodOrderDetailVO.getMenuNum() + ",");
//		System.out.println(foodOrderDetailVO.getMenuPrice() + ",");
//		System.out.println("---------------------");
//		}
	
	}
	
}
