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

import org.json.JSONArray;

import com.foodorder.model.FoodOrderDAO;
import com.foodorder.model.FoodOrderVO;

public class MenuDAO implements Menu_interface {
	//驅動程式宣告為實體變數
	String driver="oracle.jdbc.driver.OracleDriver";
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String userid ="TSAI";
	String passwd ="123456";
	
	private static final String INSERT_STMT = "INSERT INTO MENU (MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS) VALUES (('M'||LPAD(SEQ_MENU_ID.NEXTVAL,6,'0')), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS FROM MENU order by MENU_ID";
	private static final String GET_ONE_STMT = "SELECT MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS FROM MENU where MENU_ID = ?";
	private static final String DELETE = "DELETE FROM MENU where MENU_ID = ?";//餐點被參照後不能刪除
	private static final String UPDATE = "UPDATE MENU SET MENU_NAME=?, MENU_DETAIL=? ,MENU_PIC= ?,MENU_CHAR= ?,MENU_STATUS= ?,MENU_PRICE= ?,MENU_SELL_STATUS= ? where MENU_ID = ?";
	private static final String GET_ONE_STMT_BY_STORE_ID = "SELECT MENU_ID,STORE_ID,MENU_NAME,MENU_DETAIL,MENU_PIC,MENU_CHAR,MENU_STATUS,MENU_PRICE,MENU_SELL_STATUS FROM MENU where STORE_ID = ?";
	private static final String GET_ONE_BY_MENU_CHAR = "SELECT * FROM MENU where MENU_CHAR = ?";
	@Override
	public void insert(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void updateSellStatus(MenuVO menuVO) {}
	
	
	
	@Override
	public void update(MenuVO menuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String menuId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, menuId);

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
	public JSONArray findOneByMenuChar(String menuChar,String storeId) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		JSONArray menuVOJSONArra = null;
		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public MenuVO findByPrimaryKey(String menuId) {

		MenuVO menuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return menuVO;
	}

	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	@Override
	public List<MenuVO> getAllByStoreId(String storeId) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) throws IOException {

		MenuDAO dao = new MenuDAO();

//		 新增
//	 	 MenuVO menuVO = new MenuVO();
//	 	 menuVO.setStoreId("S000001");
//	 	 menuVO.setMenuName("可達鴨烤鴨");
//	 	 menuVO.setMenuDetail("鴨子來囉 呱呱呱");
//	 	 byte[] pic = getPictureByteArray("testpic/2.jpg");
//	 	 menuVO.setMenuPic(pic);
//	 	 menuVO.setMenuChar("主餐類");
//	 	 menuVO.setMenuStatus(0);
//	 	 menuVO.setMenuPrice(9999);
//	 	 menuVO.setMenuSellStatus(1);
//		 dao.insert(menuVO);


		// 修改
//		 MenuVO menuVO = new MenuVO();
//		 menuVO.setMenuId("M000011");
//	 	 menuVO.setMenuName("哥達鴨烤鴨");
//	 	 menuVO.setMenuDetail("歌德鴨子來囉 呱呱呱");
//	 	 byte[] pic = getPictureByteArray("testpic/2.jpg");
//	 	 menuVO.setMenuPic(pic);
//	 	 menuVO.setMenuChar("主餐類");
//	 	 menuVO.setMenuStatus(0);
//	 	 menuVO.setMenuPrice(9999);
//	 	 menuVO.setMenuSellStatus(1);
		
//		dao.update(menuVO);

//
//		// 刪除
//		dao.delete("M000021");
	
//
		// 查詢單筆
		JSONArray menuVOJSONArray=new JSONArray();
//		
		menuVOJSONArray  = dao.findOneByMenuChar("主餐","S000001");
		System.out.println(menuVOJSONArray);
		
		
//		System.out.print(menuVO.getMenuId() + ",");
//		System.out.print(menuVO.getStoreId() + ",");
//		System.out.print(menuVO.getMenuName() + ",");
//		System.out.print(menuVO.getMenuDetail() + ",");
////	System.out.print(menuVO.getMenuPic() + ",");		
//		System.out.print(menuVO.getMenuChar() + ",");
//		System.out.print(menuVO.getMenuStatus() + ",");
//		System.out.print(menuVO.getMenuPrice()+",");
//		System.out.println(menuVO.getMenuSellStatus());
//		System.out.println("---------------------");

////		// 查詢全部
//		List<MenuVO> list = dao.getAll();
//		for (MenuVO menuVO : list) {
//		System.out.print(menuVO.getMenuId() + ",");
//		System.out.print(menuVO.getStoreId() + ",");
//		System.out.print(menuVO.getMenuName() + ",");
//		System.out.print(menuVO.getMenuDetail() + ",");
////		System.out.print(menuVO.getMenuPic() + ",");		
//		System.out.print(menuVO.getMenuChar() + ",");
//		System.out.print(menuVO.getMenuStatus() + ",");
//		System.out.print(menuVO.getMenuPrice()+",");
//		System.out.println(menuVO.getMenuSellStatus());
//		System.out.println("---------------------");
//		System.out.println();
//		}
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];//資料流源頭(來源檔案)的大小 byte陣列會依照檔案大小產生
		fis.read(buffer);//fis會依照陣列大小讀取並放入buffer這個byte[]裡面
		fis.close();
		return buffer;
	}
}
