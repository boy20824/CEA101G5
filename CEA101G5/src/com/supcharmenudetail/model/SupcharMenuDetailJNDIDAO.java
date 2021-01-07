package com.supcharmenudetail.model;

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

public class SupcharMenuDetailJNDIDAO implements SupcharMenuDetail_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();  //初始化註冊 javax.naming.InitialContext;
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestENAK"); //javax.naming.InitialContext 尋找連線池
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO SUPCHAR_MENU_DETAIL (FOODORDER_ID,MENU_ID,MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID) VALUES (?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT FOODORDER_ID,MENU_ID,MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID FROM SUPCHAR_MENU_DETAIL where FOODORDER_ID= ? order by MENU_ID";
		private static final String GET_ONE_STMT = 
			"SELECT FOODORDER_ID,MENU_ID,MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID FROM SUPCHAR_MENU_DETAIL where FOODORDER_ID = ? and MENU_ID = ? and MENU_SUPCHAR_ID = ? ";
		private static final String DELETE = 
			"DELETE FROM SUPCHAR_MENU_DETAIL where FOODORDER_ID = ? and MENU_ID = ? and MENU_SUPCHAR_ID = ?";
//		private static final String UPDATE =   不能修改
//			"UPDATE FOODORDER_DETAIL set FOODORDER_ID=?, MENU_ID=? where  FOODORDER_ID = ?";

		
		@Override
		public void insert(SupcharMenuDetailVO supcharMenuDetailVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, supcharMenuDetailVO.getFoodOrderId());
				pstmt.setString(2, supcharMenuDetailVO.getMenuId());
				pstmt.setString(3, supcharMenuDetailVO.getMenuSupcharDetailId());
				pstmt.setString(4, supcharMenuDetailVO.getMenuSupcharId());
			

				pstmt.executeUpdate();

			}catch (SQLException se) {
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

//			Connection con = null;
//			PreparedStatement pstmt = null;
	//
//			try {
	//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);
	//
//				pstmt.setInt(1, orderDetailVO.getMenuNum());
//				pstmt.setInt(2, orderDetailVO.getMenuPrice());
//				pstmt.setString(3, orderDetailVO.getMenuId());
//				pstmt.setString(4, orderDetailVO.getFoodOrderId());
//				
//				pstmt.executeUpdate();
			
//			}catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}

		}

		@Override
		public void delete(String foodOrderId,String menuId,String menuSupcharId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, foodOrderId);
				pstmt.setString(2,menuId);
				pstmt.setString(3,menuSupcharId);

				pstmt.executeUpdate();

			}catch (SQLException se) {
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

				con = ds.getConnection();
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
				
			}catch (SQLException se) {
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

				con = ds.getConnection();
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
			}catch (SQLException se) {
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
	
}
