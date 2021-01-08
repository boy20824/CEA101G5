package com.restaurant.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RestaurantDAO implements Restaurant_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO restaurant (store_id, mem_phone, store_char, store_info, store_name, store_phone, "
			+ "store_address, store_status, store_final_reservdate, store_order_condition, store_reserv_condition,store_queue_condition, "
			+ "store_order_waittime, store_opentime, store_closetime, STORE_START_ORDERDATE, STORE_END_ORDERDATE, accept_groups, num_of_groups, "
			+ "store_people_total, store_rating_total)"
			+ "VALUES ('S' ||LPAD(SEQ_STORE_ID.NEXTVAL,6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE restaurant set MEM_PHONE= ?, STORE_CHAR=?, store_info=?, store_name=?, store_phone=?, store_address=?, store_status=?, "
			+ "store_final_reservdate=?, store_order_condition=?, store_reserv_condition=?, store_queue_condition=?, store_order_waittime=?, "
			+ "store_opentime=?, store_closetime=?, STORE_START_ORDERDATE=?, STORE_END_ORDERDATE=?, accept_groups=?, num_of_groups=?, "
			+ "store_people_total=?, store_rating_total=? WHERE store_id=? ";
	
	private static final String GET_ONE_STMT = "SELECT store_id, mem_phone, store_char, store_info, store_name, store_phone," + 
			"store_address, store_status, store_final_reservdate, store_order_condition, store_reserv_condition,store_queue_condition," + 
			"store_order_waittime, store_opentime, store_closetime, STORE_START_ORDERDATE, STORE_END_ORDERDATE, accept_groups, num_of_groups,"+ 
			"store_people_total, store_rating_total from restaurant where store_id=?";
	
	private static final String GET_ALl_STMT = "select * from restaurant order by store_id";
			
	
	
	// 新增
	@Override
	public void insert(RestaurantVO restaurantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	// 修改
	@Override
	public void update(RestaurantVO restaurantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,restaurantVO.getMemPhone());
			pstmt.setString	(2,restaurantVO.getStoreChar())	;	
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

	// 主鍵查詢
	@Override
	public RestaurantVO findByPrimaryKey(String storeId) {
		RestaurantVO restaurantVO = null;
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
				restaurantVO = new RestaurantVO();
				restaurantVO.setStoreId(rs.getString("store_id"));
				restaurantVO.setMemPhone(rs.getString("mem_phone"));
				restaurantVO.setStoreChar(rs.getString("store_char"));
				restaurantVO.setStoreInfo(rs.getString("store_info"));
				restaurantVO.setStoreName(rs.getString("store_name"));
				restaurantVO.setStorePhone(rs.getString("store_phone"));
				restaurantVO.setStoreAddress(rs.getString("store_address"));
				restaurantVO.setStoreStatus(rs.getInt("store_status"));
				restaurantVO.setStoreFinalReservDate(rs.getInt("store_final_reservdate"));
				restaurantVO.setStoreQueueCondition(rs.getInt("store_queue_condition"));
				restaurantVO.setStoreReservCondition(rs.getInt("STORE_RESERV_CONDITION"));				
				restaurantVO.setStoreOrderCondition(rs.getInt("store_order_condition"));
				restaurantVO.setStoreOrderWaitTime(rs.getInt("store_order_waittime"));
				restaurantVO.setStoreOpenTime(rs.getTimestamp("store_opentime"));
				restaurantVO.setStoreCloseTime(rs.getTimestamp("store_closetime"));
				restaurantVO.setStoreStartOrderDate(rs.getTimestamp("STORE_START_ORDERDATE"));
				restaurantVO.setStoreEndOrderDate(rs.getTimestamp("STORE_END_ORDERDATE"));
				restaurantVO.setAcceptGroups(rs.getInt("accept_groups"));
				restaurantVO.setNumOfGroups(rs.getInt("num_of_groups"));
				restaurantVO.setStorePeopleTotal(rs.getInt("store_people_total"));
				restaurantVO.setStoreRatingTotal(rs.getInt("store_rating_total"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALl_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				restaurantVO = new RestaurantVO();
				restaurantVO.setStoreId(rs.getString("store_id"));
				restaurantVO.setMemPhone(rs.getString("mem_phone"));
				restaurantVO.setStoreChar(rs.getString("store_char"));
				restaurantVO.setStoreInfo(rs.getString("store_info"));
				restaurantVO.setStoreId(rs.getString("store_id"));
				restaurantVO.setStoreName(rs.getString("store_name"));
				restaurantVO.setStorePhone(rs.getString("store_phone"));
				restaurantVO.setStoreAddress(rs.getString("store_address"));
				restaurantVO.setStoreStatus(rs.getInt("store_status"));
				restaurantVO.setStoreFinalReservDate(rs.getInt("store_final_reservdate"));
				restaurantVO.setStoreQueueCondition(rs.getInt("store_queue_condition"));
				restaurantVO.setStoreOrderCondition(rs.getInt("store_order_condition"));
				restaurantVO.setStoreOrderWaitTime(rs.getInt("store_order_waittime"));
				restaurantVO.setStoreOpenTime(rs.getTimestamp("store_opentime"));
				restaurantVO.setStoreCloseTime(rs.getTimestamp("store_closetime"));
				restaurantVO.setStoreStartOrderDate(rs.getTimestamp("STORE_START_ORDERDATE"));
				restaurantVO.setStoreEndOrderDate(rs.getTimestamp("STORE_END_ORDERDATE"));
				restaurantVO.setAcceptGroups(rs.getInt("accept_groups"));
				restaurantVO.setNumOfGroups(rs.getInt("num_of_groups"));
				restaurantVO.setStorePeopleTotal(rs.getInt("store_people_total"));
				restaurantVO.setStoreRatingTotal(rs.getInt("store_rating_total"));
				list.add(restaurantVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	
	
	public static void main(String[] args) {
		RestaurantDAO dao = new RestaurantDAO();
		
		// 新增
//		RestaurantVO rstrtVO1 = new RestaurantVO();
//		rstrtVO1.setMemPhone("0921842852");
//		rstrtVO1.setStoreChar("SCHAR000001");
//		rstrtVO1.setStoreInfo("美味便當店");
//		rstrtVO1.setStoreName("瀚林察院");
//		rstrtVO1.setStorePhone("044445555");
//		rstrtVO1.setStoreAddress("Taipei");
//		rstrtVO1.setStoreStatus(1);
//		rstrtVO1.setStoreFinalReservDate(30);
//		rstrtVO1.setStoreOrderCondition(1);
//		rstrtVO1.setStoreReservCondition(1);
//		rstrtVO1.setStoreQueueCondition(1);
//		rstrtVO1.setStoreOrderWaitTime(30);
//		rstrtVO1.setStoreOpenTime(java.sql.Timestamp.valueOf("2021-01-01 09:22:30"));
//		rstrtVO1.setStoreCloseTime(java.sql.Timestamp.valueOf("2021-02-01 09:22:30"));
//		rstrtVO1.setStoreStartOrderDate(java.sql.Timestamp.valueOf("2021-01-01 09:22:30"));
//		rstrtVO1.setStoreEndOrderDate(java.sql.Timestamp.valueOf("2021-02-01 09:22:30"));
//		rstrtVO1.setAcceptGroups(1);
//		rstrtVO1.setNumOfGroups(4);
//		rstrtVO1.setStorePeopleTotal(10);
//		rstrtVO1.setStoreRatingTotal(1200);
//		
//		dao.insert(rstrtVO1);
	
		
		// 修改
//		RestaurantVO rstrtVO2 = new RestaurantVO();
//		rstrtVO2.setStoreId("S000012");
//		rstrtVO2.setMemPhone("0921842852");
//		rstrtVO2.setStoreChar("SCHAR000001");
//		rstrtVO2.setStoreInfo("this is fucking good");
//		rstrtVO2.setStoreName("googoo");
//		rstrtVO2.setStorePhone("044445555");
//		rstrtVO2.setStoreAddress("Caipei");
//		rstrtVO2.setStoreStatus(1);
//		rstrtVO2.setStoreFinalReservDate(30);
//		rstrtVO2.setStoreOrderCondition(1);
//		rstrtVO2.setStoreReservCondition(1);
//		rstrtVO2.setStoreQueueCondition(1);
//		rstrtVO2.setStoreOrderWaitTime(30);
//		rstrtVO2.setStoreOpenTime(java.sql.Timestamp.valueOf("2021-01-01 09:23:30"));
//		rstrtVO2.setStoreCloseTime(java.sql.Timestamp.valueOf("2021-01-01 09:22:30"));
//		rstrtVO2.setStoreStartOrderDate(java.sql.Timestamp.valueOf("2021-01-01 09:25:30"));
//		rstrtVO2.setStoreEndOrderDate(java.sql.Timestamp.valueOf("2021-01-01 09:27:30"));
//		rstrtVO2.setAcceptGroups(1);
//		rstrtVO2.setNumOfGroups(4);
//		rstrtVO2.setStorePeopleTotal(10);
//		rstrtVO2.setStoreRatingTotal(1200);
//		
//		dao.update(rstrtVO2);
		
//		// 主鍵查詢
//		RestaurantVO rstrtVO3 = dao.findByPrimaryKey("S000001");
//		System.out.print(rstrtVO3.getStoreId()+",");
//		System.out.print(rstrtVO3.getMemPhone()+",");
//		System.out.print(rstrtVO3.getStoreChar()+",");
//		System.out.print(rstrtVO3.getStoreInfo()+",");
//		System.out.print(rstrtVO3.getStoreName()+",");
//		System.out.print(rstrtVO3.getStorePhone()+",");
//		System.out.print(rstrtVO3.getStoreAddress()+",");
//		System.out.print(rstrtVO3.getStoreStatus()+",");
//		System.out.print(rstrtVO3.getStoreFinalReservDate()+",");
//		System.out.print(rstrtVO3.getStoreOrderCondition()+",");
//		System.out.print(rstrtVO3.getStoreReservCondition()+",");
//		System.out.print(rstrtVO3.getStoreQueueCondition()+",");
//		System.out.print(rstrtVO3.getStoreOrderWaitTime()+",");
//		System.out.print(rstrtVO3.getStoreOpenTime()+",");
//		System.out.print(rstrtVO3.getStoreCloseTime()+",");
//		System.out.print(rstrtVO3.getStoreStartOrderDate()+",");
//		System.out.print(rstrtVO3.getStoreEndOrderDate()+",");
//		System.out.print(rstrtVO3.getAcceptGroups()+",");
//		System.out.print(rstrtVO3.getNumOfGroups()+",");
//		System.out.print(rstrtVO3.getStorePeopleTotal()+",");
//		System.out.print(rstrtVO3.getStoreRatingTotal());
		
	// 查詢全部
		
		List<RestaurantVO> list = dao.getAll();
		for (RestaurantVO rstrtVO : list) {
			System.out.print(rstrtVO.getStoreId()+",");
			System.out.print(rstrtVO.getMemPhone()+",");
			System.out.print(rstrtVO.getStoreChar()+",");
			System.out.print(rstrtVO.getStoreInfo()+",");
			System.out.print(rstrtVO.getStoreName()+",");
			System.out.print(rstrtVO.getStorePhone()+",");
			System.out.print(rstrtVO.getStoreAddress()+",");
			System.out.print(rstrtVO.getStoreStatus()+",");
			System.out.print(rstrtVO.getStoreFinalReservDate()+",");
			System.out.print(rstrtVO.getStoreOrderCondition()+",");
			System.out.print(rstrtVO.getStoreReservCondition()+",");
			System.out.print(rstrtVO.getStoreQueueCondition()+",");
			System.out.print(rstrtVO.getStoreOrderWaitTime()+",");
			System.out.print(rstrtVO.getStoreOpenTime()+",");
			System.out.print(rstrtVO.getStoreCloseTime()+",");
			System.out.print(rstrtVO.getStoreStartOrderDate()+",");
			System.out.print(rstrtVO.getStoreEndOrderDate()+",");
			System.out.print(rstrtVO.getAcceptGroups()+",");
			System.out.print(rstrtVO.getNumOfGroups()+",");
			System.out.print(rstrtVO.getStorePeopleTotal()+",");
			System.out.println(rstrtVO.getStoreRatingTotal());
			System.out.println();
		}
		
	}
	
}