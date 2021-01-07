package com.restaurantpicture.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.menu.model.MenuVO;

public class RestaurantPictureDAO implements RestaurantPicture_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TSAI";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO restaurant_picture (store_picture_id, store_id, store_picture) values ('SP' ||LPAD(SEQ_STORE_PICTURE_ID.NEXTVAL,6,'0'),?,?)";
	private static final String UPDATE = "UPDATE restaurant_picture set store_picture=? where store_picture_id=?";
	private static final String GET_ALL_STMT = "SELECT store_picture_id,store_id,store_picture FROM restaurant_picture order by store_picture_id";
	private static final String GET_ONE_STMT = "SELECT store_picture_id,store_id,store_picture FROM restaurant_picture where store_picture_id=?";
	private static final String DELETE = "DELETE FROM restaurant_picture where store_picture_id = ?";

	// 新增
	@Override
	public void insert(RestaurantPictureVO restaurantPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restaurantPictureVO.getStoreId());
			pstmt.setBytes(2, restaurantPictureVO.getStorePicture());
			
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

	//修改
	@Override
	public void update(RestaurantPictureVO restaurantPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

		
			pstmt.setString(2, restaurantPictureVO.getStorePictureId());

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
	public RestaurantPictureVO findByPrimaryKey(String storePictureId) {
		RestaurantPictureVO restaurantPictureVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storePictureId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantPictureVO = new RestaurantPictureVO();
				restaurantPictureVO.setStorePictureId(rs.getString("STORE_PICTURE_ID"));
				restaurantPictureVO.setStorePicture(rs.getBytes("STORE_PICTURE"));
				restaurantPictureVO.setStoreId(rs.getString("STORE_ID"));
			
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
		return restaurantPictureVO;
	}
	
	@Override
	public List<RestaurantPictureVO> getAll() {
		List<RestaurantPictureVO> list = new ArrayList<RestaurantPictureVO>();
		RestaurantPictureVO restaurantPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantPictureVO = new RestaurantPictureVO();
				restaurantPictureVO.setStorePictureId(rs.getString("STORE_PICTURE_ID"));
				restaurantPictureVO.setStorePicture(rs.getBytes("STORE_PICTURE"));
				restaurantPictureVO.setStoreId(rs.getString("STORE_ID"));
				list.add(restaurantPictureVO); // Store the row in the list
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
	public void delete(String storePictureId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, storePictureId);

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


	
	
	public static void main(String[] args)  throws IOException{
		RestaurantPictureDAO dao = new RestaurantPictureDAO();

//		 新增
//		 RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();
//		 
//		 restaurantPictureVO.setStoreId("S000001");
//		 
//		 byte[] pic = getPictureByteArray("testpic/2.jpg");
//		 restaurantPictureVO.setStorePicture(pic);
//		 dao.insert(restaurantPictureVO);


		 // 修改  不行修改只能刪除



		// 刪除
//		dao.delete("SP000001");
	
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];//資料流源頭(來源檔案)的大小 byte陣列會依照檔案大小產生
		fis.read(buffer);//fis會依照陣列大小讀取並放入buffer這個byte[]裡面
		fis.close();
		return buffer;
	}
}
