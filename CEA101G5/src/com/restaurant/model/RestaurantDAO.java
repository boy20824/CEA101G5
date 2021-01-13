package com.restaurant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.restaurantpicture.model.RestaurantPictureDAO;
import com.restaurantpicture.model.RestaurantPictureVO;

public class RestaurantDAO implements Restaurant_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO RESTAURANT (STORE_ID, MEM_PHONE, STORE_CHAR, STORE_INFO, STORE_NAME, STORE_PHONE, "
			+ "STORE_ADDRESS, STORE_STATUS, STORE_FINAL_RESERVDATE, STORE_ORDER_CONDITION, STORE_RESERV_CONDITION,STORE_QUEUE_CONDITION, "
			+ "STORE_ORDER_WAITTIME, STORE_OPENTIME, STORE_CLOSETIME, STORE_START_ORDERDATE, STORE_END_ORDERDATE, ACCEPT_GROUPS, NUM_OF_GROUPS, "
			+ "STORE_PEOPLE_TOTAL, STORE_RATING_TOTAL)"
			+ "VALUES ('S' ||LPAD(SEQ_STORE_ID.NEXTVAL,6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String EASY_INSERT_STMT = "INSERT INTO RESTAURANT (STORE_ID, MEM_PHONE, STORE_CHAR, STORE_INFO, STORE_NAME, STORE_PHONE, "
			+ "STORE_ADDRESS, STORE_STATUS, STORE_OPENTIME, STORE_CLOSETIME)"
			+ "VALUES ('S' ||LPAD(SEQ_STORE_ID.NEXTVAL,6,'0'),?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE = "UPDATE RESTAURANT SET MEM_PHONE= ?, STORE_CHAR=?, STORE_INFO=?, STORE_NAME=?, STORE_PHONE=?, STORE_ADDRESS=?, STORE_STATUS=?, "
			+ "STORE_FINAL_RESERVDATE=?, STORE_ORDER_CONDITION=?, STORE_RESERV_CONDITION=?, STORE_QUEUE_CONDITION=?, STORE_ORDER_WAITTIME=?, "
			+ "STORE_OPENTIME=?, STORE_CLOSETIME=?, STORE_START_ORDERDATE=?, STORE_END_ORDERDATE=?, ACCEPT_GROUPS=?, NUM_OF_GROUPS=?, "
			+ "STORE_PEOPLE_TOTAL=?, STORE_RATING_TOTAL=? WHERE STORE_ID=? ";

	private static final String GET_ONE_STMT = "SELECT STORE_ID, MEM_PHONE, STORE_CHAR, STORE_INFO, STORE_NAME, STORE_PHONE,"
			+ "STORE_ADDRESS, STORE_STATUS, STORE_FINAL_RESERVDATE, STORE_ORDER_CONDITION, STORE_RESERV_CONDITION,STORE_QUEUE_CONDITION,"
			+ "STORE_ORDER_WAITTIME, STORE_OPENTIME, STORE_CLOSETIME, STORE_START_ORDERDATE, STORE_END_ORDERDATE, ACCEPT_GROUPS, NUM_OF_GROUPS,"
			+ "STORE_PEOPLE_TOTAL, STORE_RATING_TOTAL FROM RESTAURANT WHERE STORE_ID=?";

	private static final String GET_ALl_STMT = "SELECT * FROM RESTAURANT";

	private static final String DELETE_RESTAURANT = "DELETE FROM RESTAURANT WHERE STORE_ID = ?";

	// 新增
	@Override
	public void insert(RestaurantVO restaurantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, restaurantVO.getMemPhone());
			pstmt.setString(2, restaurantVO.getStoreChar());
			pstmt.setString(3, restaurantVO.getStoreInfo());
			pstmt.setString(4, restaurantVO.getStoreName());
			pstmt.setString(5, restaurantVO.getStorePhone());
			pstmt.setString(6, restaurantVO.getStoreAddress());
			pstmt.setInt(7, restaurantVO.getStoreStatus());
			pstmt.setInt(8, restaurantVO.getStoreFinalReservDate());
			pstmt.setInt(9, restaurantVO.getStoreOrderCondition());
			pstmt.setInt(10, restaurantVO.getStoreReservCondition());
			pstmt.setInt(11, restaurantVO.getStoreQueueCondition());
			pstmt.setInt(12, restaurantVO.getStoreOrderWaitTime());
			pstmt.setTimestamp(13, restaurantVO.getStoreOpenTime());
			pstmt.setTimestamp(14, restaurantVO.getStoreCloseTime());
			pstmt.setTimestamp(15, restaurantVO.getStoreStartOrderDate());
			pstmt.setTimestamp(16, restaurantVO.getStoreEndOrderDate());
			pstmt.setInt(17, restaurantVO.getAcceptGroups());
			pstmt.setInt(18, restaurantVO.getNumOfGroups());
			pstmt.setInt(19, restaurantVO.getStorePeopleTotal());
			pstmt.setInt(20, restaurantVO.getStoreRatingTotal());

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

	// 修改
	@Override
	public void update(RestaurantVO restaurantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restaurantVO.getMemPhone());
			pstmt.setString(2, restaurantVO.getStoreChar());
			pstmt.setString(3, restaurantVO.getStoreInfo());
			pstmt.setString(4, restaurantVO.getStoreName());
			pstmt.setString(5, restaurantVO.getStorePhone());
			pstmt.setString(6, restaurantVO.getStoreAddress());
			pstmt.setInt(7, restaurantVO.getStoreStatus());
			pstmt.setInt(8, restaurantVO.getStoreFinalReservDate());
			pstmt.setInt(9, restaurantVO.getStoreOrderCondition());
			pstmt.setInt(10, restaurantVO.getStoreReservCondition());
			pstmt.setInt(11, restaurantVO.getStoreQueueCondition());
			pstmt.setInt(12, restaurantVO.getStoreOrderWaitTime());
			pstmt.setTimestamp(13, restaurantVO.getStoreOpenTime());
			pstmt.setTimestamp(14, restaurantVO.getStoreCloseTime());
			pstmt.setTimestamp(15, restaurantVO.getStoreStartOrderDate());
			pstmt.setTimestamp(16, restaurantVO.getStoreEndOrderDate());
			pstmt.setInt(17, restaurantVO.getAcceptGroups());
			pstmt.setInt(18, restaurantVO.getNumOfGroups());
			pstmt.setInt(19, restaurantVO.getStorePeopleTotal());
			pstmt.setInt(20, restaurantVO.getStoreRatingTotal());
			pstmt.setString(21, restaurantVO.getStoreId());

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

	// 主鍵查詢
	@Override
	public RestaurantVO findByPrimaryKey(String storeId) {
		RestaurantVO restaurantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantVO = new RestaurantVO();
				restaurantVO.setStoreId(rs.getString("STORE_ID"));
				restaurantVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantVO.setStoreChar(rs.getString("STORE_CHAR"));
				restaurantVO.setStoreInfo(rs.getString("STORE_INFO"));
				restaurantVO.setStoreName(rs.getString("STORE_NAME"));
				restaurantVO.setStorePhone(rs.getString("STORE_PHONE"));
				restaurantVO.setStoreAddress(rs.getString("STORE_ADDRESS"));
				restaurantVO.setStoreStatus(rs.getInt("STORE_STATUS"));
				restaurantVO.setStoreFinalReservDate(rs.getInt("STORE_FINAL_RESERVDATE"));
				restaurantVO.setStoreQueueCondition(rs.getInt("STORE_QUEUE_CONDITION"));
				restaurantVO.setStoreReservCondition(rs.getInt("STORE_RESERV_CONDITION"));
				restaurantVO.setStoreOrderCondition(rs.getInt("STORE_ORDER_CONDITION"));
				restaurantVO.setStoreOrderWaitTime(rs.getInt("STORE_ORDER_WAITTIME"));
				restaurantVO.setStoreOpenTime(rs.getTimestamp("STORE_OPENTIME"));
				restaurantVO.setStoreCloseTime(rs.getTimestamp("STORE_CLOSETIME"));
				restaurantVO.setStoreStartOrderDate(rs.getTimestamp("STORE_START_ORDERDATE"));
				restaurantVO.setStoreEndOrderDate(rs.getTimestamp("STORE_END_ORDERDATE"));
				restaurantVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				restaurantVO.setNumOfGroups(rs.getInt("NUM_OF_GROUPS"));
				restaurantVO.setStorePeopleTotal(rs.getInt("STORE_PEOPLE_TOTAL"));
				restaurantVO.setStoreRatingTotal(rs.getInt("STORE_RATING_TOTAL"));

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return restaurantVO;
	}

	@Override
	public List<RestaurantVO> getAll() {
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		RestaurantVO restaurantVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALl_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantVO = new RestaurantVO();
				restaurantVO.setStoreId(rs.getString("STORE_ID"));
				restaurantVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantVO.setStoreChar(rs.getString("STORE_CHAR"));
				restaurantVO.setStoreInfo(rs.getString("STORE_INFO"));
				restaurantVO.setStoreId(rs.getString("STORE_ID"));
				restaurantVO.setStoreName(rs.getString("STORE_NAME"));
				restaurantVO.setStorePhone(rs.getString("STORE_PHONE"));
				restaurantVO.setStoreAddress(rs.getString("STORE_ADDRESS"));
				restaurantVO.setStoreStatus(rs.getInt("STORE_STATUS"));
				restaurantVO.setStoreFinalReservDate(rs.getInt("STORE_FINAL_RESERVDATE"));
				restaurantVO.setStoreQueueCondition(rs.getInt("STORE_QUEUE_CONDITION"));
				restaurantVO.setStoreOrderCondition(rs.getInt("STORE_ORDER_CONDITION"));
				restaurantVO.setStoreOrderWaitTime(rs.getInt("STORE_ORDER_WAITTIME"));
				restaurantVO.setStoreOpenTime(rs.getTimestamp("STORE_OPENTIME"));
				restaurantVO.setStoreCloseTime(rs.getTimestamp("STORE_CLOSETIME"));
				restaurantVO.setStoreStartOrderDate(rs.getTimestamp("STORE_START_ORDERDATE"));
				restaurantVO.setStoreEndOrderDate(rs.getTimestamp("STORE_END_ORDERDATE"));
				restaurantVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				restaurantVO.setNumOfGroups(rs.getInt("NUM_OF_GROUPS"));
				restaurantVO.setStorePeopleTotal(rs.getInt("STORE_PEOPLE_TOTAL"));
				restaurantVO.setStoreRatingTotal(rs.getInt("STORE_RATING_TOTAL"));
				list.add(restaurantVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String storeId) {
		int updateCount_RESTAURANT = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 刪除會員
			pstmt = con.prepareStatement(DELETE_RESTAURANT);
			pstmt.setString(1, storeId);
			updateCount_RESTAURANT = pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除餐廳編號" + storeId + "時,共有餐廳" + updateCount_RESTAURANT + "間同時被刪除");

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void easyinsert(RestaurantVO restaurantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(EASY_INSERT_STMT);
			
			pstmt.setString(1, restaurantVO.getMemPhone());
			pstmt.setString(2, restaurantVO.getStoreChar());
			pstmt.setString(3, restaurantVO.getStoreInfo());
			pstmt.setString(4, restaurantVO.getStoreName());
			pstmt.setString(5, restaurantVO.getStorePhone());
			pstmt.setString(6, restaurantVO.getStoreAddress());
			pstmt.setInt(7, restaurantVO.getStoreStatus());
			pstmt.setTimestamp(8, restaurantVO.getStoreOpenTime());
			pstmt.setTimestamp(9, restaurantVO.getStoreCloseTime());
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
	public void easyInsertWithPics(RestaurantVO restaurantVO,RestaurantPictureVO restaurantPictureVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增餐廳
			String cols[] = {"STORE_ID"};
			pstmt = con.prepareStatement(EASY_INSERT_STMT , cols);			
			pstmt.setString(1, restaurantVO.getMemPhone());
			pstmt.setString(2, restaurantVO.getStoreChar());
			pstmt.setString(3, restaurantVO.getStoreInfo());
			pstmt.setString(4, restaurantVO.getStoreName());
			pstmt.setString(5, restaurantVO.getStorePhone());
			pstmt.setString(6, restaurantVO.getStoreAddress());
			pstmt.setInt(7, restaurantVO.getStoreStatus());
			pstmt.setTimestamp(8, restaurantVO.getStoreOpenTime());
			pstmt.setTimestamp(9, restaurantVO.getStoreCloseTime());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_storeId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_storeId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_storeId +"(剛新增成功的餐廳編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增餐廳照片
			RestaurantPictureDAO dao = new RestaurantPictureDAO(); 
			restaurantPictureVO.setStoreId(next_storeId);
			dao.insertWithStore(restaurantPictureVO,con);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-store");
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
	public List<RestaurantVO> getAll(Map<String, String[]> map) {
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		RestaurantVO restaurantVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "SELECT DISTINCT RESTAURANT.STORE_ID, MEM_PHONE, STORE_CHAR, STORE_INFO, STORE_NAME, STORE_PHONE,STORE_ADDRESS, "
				  + "STORE_STATUS, STORE_FINAL_RESERVDATE, STORE_ORDER_CONDITION, STORE_RESERV_CONDITION,STORE_QUEUE_CONDITION,STORE_ORDER_WAITTIME, STORE_OPENTIME, STORE_CLOSETIME, "
				  + "STORE_START_ORDERDATE, STORE_END_ORDERDATE, ACCEPT_GROUPS, NUM_OF_GROUPS,STORE_PEOPLE_TOTAL, STORE_RATING_TOTAL "
				  + "FROM RESTAURANT "
				  + "JOIN MENU ON (RESTAURANT.STORE_ID = MENU.STORE_ID)"
		          + jdbcUtil_CompositeQuery_Restaurant.get_WhereCondition(map)
		          + "ORDER BY STORE_ID";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				restaurantVO = new RestaurantVO();
				restaurantVO.setStoreId(rs.getString("STORE_ID"));
				restaurantVO.setMemPhone(rs.getString("MEM_PHONE"));
				restaurantVO.setStoreChar(rs.getString("STORE_CHAR"));
				restaurantVO.setStoreInfo(rs.getString("STORE_INFO"));
				restaurantVO.setStoreId(rs.getString("STORE_ID"));
				restaurantVO.setStoreName(rs.getString("STORE_NAME"));
				restaurantVO.setStorePhone(rs.getString("STORE_PHONE"));
				restaurantVO.setStoreAddress(rs.getString("STORE_ADDRESS"));
				restaurantVO.setStoreStatus(rs.getInt("STORE_STATUS"));
				restaurantVO.setStoreFinalReservDate(rs.getInt("STORE_FINAL_RESERVDATE"));
				restaurantVO.setStoreQueueCondition(rs.getInt("STORE_QUEUE_CONDITION"));
				restaurantVO.setStoreOrderCondition(rs.getInt("STORE_ORDER_CONDITION"));
				restaurantVO.setStoreOrderWaitTime(rs.getInt("STORE_ORDER_WAITTIME"));
				restaurantVO.setStoreOpenTime(rs.getTimestamp("STORE_OPENTIME"));
				restaurantVO.setStoreCloseTime(rs.getTimestamp("STORE_CLOSETIME"));
				restaurantVO.setStoreStartOrderDate(rs.getTimestamp("STORE_START_ORDERDATE"));
				restaurantVO.setStoreEndOrderDate(rs.getTimestamp("STORE_END_ORDERDATE"));
				restaurantVO.setAcceptGroups(rs.getInt("ACCEPT_GROUPS"));
				restaurantVO.setNumOfGroups(rs.getInt("NUM_OF_GROUPS"));
				restaurantVO.setStorePeopleTotal(rs.getInt("STORE_PEOPLE_TOTAL"));
				restaurantVO.setStoreRatingTotal(rs.getInt("STORE_RATING_TOTAL"));
				list.add(restaurantVO);
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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