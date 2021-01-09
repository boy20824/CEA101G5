package com.menu.model;

import java.io.FileInputStream;
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

import org.json.JSONArray;
import org.json.JSONObject;

public class MenuJNDIDAO implements Menu_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO MENU (MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS) VALUES (('M'||LPAD(SEQ_MENU_ID.NEXTVAL,6,'0')), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS FROM MENU order by MENU_ID";
	private static final String GET_ONE_STMT = "SELECT MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS FROM MENU where MENU_ID = ?";
	private static final String GET_ONE_STMT_BY_STORE_ID = "SELECT MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS FROM MENU where STORE_ID = ?";
	private static final String DELETE = "DELETE FROM MENU where MENU_ID = ?";//餐點被參照後不能刪除
	private static final String UPDATE = "UPDATE MENU SET MENU_NAME=?, MENU_DETAIL=? ,MENU_PIC= ?,MENU_CHAR= ?,MENU_STATUS= ?,MENU_PRICE= ?,MENU_SELL_STATUS= ? where MENU_ID = ?";
	private static final String UPDATE_MENUSTATUS = "UPDATE MENU SET MENU_STATUS= ? where MENU_ID = ?";
	private static final String GET_ONE_BY_MENU_CHAR = "SELECT * FROM MENU where MENU_CHAR = ? AND STORE_ID= ? ";
	
	
	
	@Override
	public void insert(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuVO.getStoreId());
			pstmt.setString(2, menuVO.getMenuName());
			pstmt.setString(3, menuVO.getMenuDetail());
			pstmt.setBytes(4, menuVO.getMenuPic());
			pstmt.setString(5, menuVO.getMenuChar());
			pstmt.setInt(6, menuVO.getMenuStatus());
			pstmt.setInt(7, menuVO.getMenuPrice());
			pstmt.setInt(8, menuVO.getMenuSellStatus());

			pstmt.executeUpdate();


		}catch (SQLException se) {
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
	public void updateSellStatus(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MENUSTATUS);

			pstmt.setInt(1, menuVO.getMenuStatus());
			pstmt.setString(2, menuVO.getMenuId());
			
			pstmt.executeUpdate();

		}catch (SQLException se) {
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
	public void update(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuVO.getMenuName());
			pstmt.setString(2, menuVO.getMenuDetail());
			pstmt.setBytes(3, menuVO.getMenuPic());
			pstmt.setString(4, menuVO.getMenuChar());
			pstmt.setInt(5, menuVO.getMenuStatus());
			pstmt.setInt(6, menuVO.getMenuPrice());
			pstmt.setInt(7, menuVO.getMenuSellStatus());
			pstmt.setString(8, menuVO.getMenuId());
			
			pstmt.executeUpdate();

		}catch (SQLException se) {
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
	public void delete(String menuId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, menuId);

			pstmt.executeUpdate();

		}catch (SQLException se) {
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
	public MenuVO findByPrimaryKey(String menuId) {

		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, menuId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenuId(rs.getString("MENU_ID"));
				menuVO.setStoreId(rs.getString("STORE_ID"));
				menuVO.setMenuName(rs.getString("MENU_NAME"));
				menuVO.setMenuDetail(rs.getString("MENU_DETAIL"));
				menuVO.setMenuPic(rs.getBytes("MENU_PIC"));
				menuVO.setMenuChar(rs.getString("MENU_CHAR"));
				menuVO.setMenuStatus(rs.getInt("MENU_STATUS"));
				menuVO.setMenuPrice(rs.getInt("MENU_PRICE"));
				menuVO.setMenuSellStatus(rs.getInt("MENU_SELL_STATUS"));
			}

		}catch (SQLException se) {
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
		return menuVO;
	}

	@Override
	public JSONArray findOneByMenuChar(String menuChar,String storeId) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		JSONArray menuVOJSONArra = null;
		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MENU_CHAR);

			pstmt.setString(1, menuChar);
			pstmt.setString(2, storeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenuId(rs.getString("MENU_ID"));
				menuVO.setStoreId(rs.getString("STORE_ID"));
				menuVO.setMenuName(rs.getString("MENU_NAME"));
				menuVO.setMenuDetail(rs.getString("MENU_DETAIL"));
//				menuVO.setMenuPic(rs.getBytes("MENU_PIC"));
				menuVO.setMenuChar(rs.getString("MENU_CHAR"));
				menuVO.setMenuStatus(rs.getInt("MENU_STATUS"));
				menuVO.setMenuPrice(rs.getInt("MENU_PRICE"));
				menuVO.setMenuSellStatus(rs.getInt("MENU_SELL_STATUS"));
				list.add(menuVO);
			}

		}catch (SQLException se) {
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
		menuVOJSONArra = new JSONArray(list);
		return menuVOJSONArra;
	}

	
	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// // foodOrderVO 也稱為 Domain objects
				menuVO = new MenuVO();
				menuVO.setMenuId(rs.getString("MENU_ID"));
				menuVO.setStoreId(rs.getString("STORE_ID"));
				menuVO.setMenuName(rs.getString("MENU_NAME"));
				menuVO.setMenuDetail(rs.getString("MENU_DETAIL"));
				menuVO.setMenuPic(rs.getBytes("MENU_PIC"));
				menuVO.setMenuChar(rs.getString("MENU_CHAR"));
				menuVO.setMenuStatus(rs.getInt("MENU_STATUS"));
				menuVO.setMenuPrice(rs.getInt("MENU_PRICE"));
				menuVO.setMenuSellStatus(rs.getInt("MENU_SELL_STATUS"));
				list.add(menuVO); // Store the row in the list
			}

		}catch (SQLException se) {
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
	@Override
	public List<MenuVO> getAllByStoreId(String storeId) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_STORE_ID );
			
			pstmt.setString(1, storeId);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// // foodOrderVO 也稱為 Domain objects
				menuVO = new MenuVO();
				menuVO.setMenuId(rs.getString("MENU_ID"));
				menuVO.setStoreId(rs.getString("STORE_ID"));
				menuVO.setMenuName(rs.getString("MENU_NAME"));
				menuVO.setMenuDetail(rs.getString("MENU_DETAIL"));
				menuVO.setMenuPic(rs.getBytes("MENU_PIC"));
				menuVO.setMenuChar(rs.getString("MENU_CHAR"));
				menuVO.setMenuStatus(rs.getInt("MENU_STATUS"));
				menuVO.setMenuPrice(rs.getInt("MENU_PRICE"));
				menuVO.setMenuSellStatus(rs.getInt("MENU_SELL_STATUS"));
				list.add(menuVO); // Store the row in the list
			}

		}catch (SQLException se) {
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
}
