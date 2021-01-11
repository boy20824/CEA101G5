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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;

public class RestaurantPictureDAO implements RestaurantPicture_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RESTAURANT_PICTURE (STORE_PICTURE_ID, STORE_ID, STORE_PICTURE) VALUES ('SP' ||LPAD(SEQ_STORE_PICTURE_ID.NEXTVAL,6,'0'),?,?)";
	private static final String UPDATE = "UPDATE RESTAURANT_PICTURE SET STORE_PICTURE=? WHERE STORE_PICTURE_ID=?";
	private static final String GET_ALL_STMT = "SELECT STORE_PICTURE_ID,STORE_ID,STORE_PICTURE FROM RESTAURANT_PICTURE ORDER BY STORE_PICTURE_ID";
	private static final String GET_PIC_STMT = "SELECT STORE_PICTURE_ID,STORE_ID,STORE_PICTURE FROM RESTAURANT_PICTURE WHERE STORE_PICTURE_ID=?";
	private static final String GET_PIC_BYSTORE_STMT = "SELECT STORE_PICTURE_ID,STORE_ID,STORE_PICTURE FROM RESTAURANT_PICTURE WHERE STORE_ID=?";
	private static final String GET_ONEPIC_BYSTORE_STMT = "SELECT STORE_PICTURE_ID,STORE_ID,STORE_PICTURE FROM RESTAURANT_PICTURE WHERE STORE_ID=? AND ROWNUM = 1";
	private static final String DELETE = "DELETE FROM RESTAURANT_PICTURE WHERE STORE_PICTURE_ID = ?";

	@Override
	public List<RestaurantPictureVO> insert(List<RestaurantPictureVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			for (RestaurantPictureVO restaurantPictureVO : list) {

				pstmt.setString(1, restaurantPictureVO.getStoreId());
				pstmt.setBytes(2, restaurantPictureVO.getStorePicture());

				pstmt.executeUpdate();
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restaurantPictureVO.getStoreId());
			pstmt.setBytes(2, restaurantPictureVO.getStorePicture());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public void insertWithStore(RestaurantPictureVO restaurantPictureVO, Connection con) {

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restaurantPictureVO.getStoreId());
			pstmt.setBytes(2, restaurantPictureVO.getStorePicture());

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
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}

	// 修改
	@Override
	public void update(RestaurantPictureVO restaurantPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, restaurantPictureVO.getStorePicture());
			pstmt.setString(2, restaurantPictureVO.getStorePictureId());

			pstmt.executeUpdate();

			// Handle any driver errors
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
		RestaurantPictureVO restaurantPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
			con = ds.getConnection();
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

	public RestaurantPictureVO findOneByStoreId(String storeId) {
		RestaurantPictureVO restaurantPictureVO = new RestaurantPictureVO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEPIC_BYSTORE_STMT);
			pstmt.setString(1, storeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			restaurantPictureVO = new RestaurantPictureVO();
			restaurantPictureVO.setStorePictureId(rs.getString("STORE_PICTURE_ID"));
			restaurantPictureVO.setStorePicture(rs.getBytes("STORE_PICTURE"));
			restaurantPictureVO.setStoreId(rs.getString("STORE_ID"));
			}
			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, storePictureId);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];// 資料流源頭(來源檔案)的大小 byte陣列會依照檔案大小產生
		fis.read(buffer);// fis會依照陣列大小讀取並放入buffer這個byte[]裡面
		fis.close();
		return buffer;
	}

}
