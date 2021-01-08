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


public class RestaurantPictureDAO implements RestaurantPicture_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO restaurant_picture (store_picture_id, store_id, store_picture) values ('SP' ||LPAD(SEQ_STORE_PICTURE_ID.NEXTVAL,6,'0'),?,?)";
	private static final String UPDATE = "UPDATE restaurant_picture set store_picture=? where store_picture_id=?";
	private static final String GET_ALL_STMT = "SELECT store_picture_id,store_id,store_picture FROM restaurant_picture order by store_picture_id";
	private static final String GET_PIC_STMT = "SELECT store_picture_id,store_id,store_picture FROM restaurant_picture where store_picture_id=?";
	private static final String GET_PIC_BYSTORE_STMT = "SELECT store_picture_id,store_id,store_picture FROM restaurant_picture where AND store_id=?";
	private static final String DELETE = "DELETE FROM restaurant_picture where store_picture_id = ?";

	@Override
	public List<RestaurantPictureVO> insert(List<RestaurantPictureVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			for(RestaurantPictureVO restaurantPictureVO : list) {
			
			pstmt.setString(1, restaurantPictureVO.getStoreId());
			pstmt.setBytes(2, restaurantPictureVO.getStorePicture());
			
			pstmt.executeUpdate();
			}

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
		return list;
	}
	
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

			pstmt.setBytes(1, restaurantPictureVO.getStorePicture());
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
			pstmt = con.prepareStatement(GET_PIC_STMT);

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
	public List<RestaurantPictureVO> findByStoreId(String storeId) {
		List<RestaurantPictureVO> list = new ArrayList<RestaurantPictureVO>();
		RestaurantPictureVO restaurantPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PIC_BYSTORE_STMT);
			
			pstmt.setString(1, storeId);
			
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
//		 restaurantPictureVO.setStoreId("S000001");
//		 byte[] pic = getPictureByteArray("C:\\CEA101_G5\\images/p3.jpg");
//		 restaurantPictureVO.setStorePicture(pic);
//		 RestaurantPictureVO restaurantPictureVO1 = new RestaurantPictureVO();
//		 restaurantPictureVO1.setStoreId("S000001");
//		 byte[] pic1 = getPictureByteArray("C:\\CEA101_G5\\images/p4.jpg");
//		 restaurantPictureVO1.setStorePicture(pic1);
//		 RestaurantPictureVO restaurantPictureVO2 = new RestaurantPictureVO();
//		 restaurantPictureVO2.setStoreId("S000001");
//		 byte[] pic2 = getPictureByteArray("C:\\CEA101_G5\\images/p7.jpg");
//		 restaurantPictureVO2.setStorePicture(pic2);
//		 
//		 List list = new ArrayList();
//		 list.add(restaurantPictureVO);
//		 list.add(restaurantPictureVO1);
//		 list.add(restaurantPictureVO2);
//		 System.out.println(list);
//		 
//		 
//		 dao.insert(list);


		// 修改  
		
		RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();
		 
		 restaurantPictureVO.setStorePictureId("SP000161");
		 
		 byte[] pic = getPictureByteArray("C:\\CEA101_G5\\images/p2.jpg");
		 restaurantPictureVO.setStorePicture(pic);
		 dao.update(restaurantPictureVO);


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
