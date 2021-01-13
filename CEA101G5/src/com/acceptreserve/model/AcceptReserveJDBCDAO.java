package com.acceptreserve.model;
//這是JDBC寫法, 之後會改成DAO(連線池寫法)
import java.util.*;
import java.sql.*;

public class AcceptReserveJDBCDAO implements AcceptReserveDAO_interface {
	

	@Override
	public List<AcceptReserveVO> schedule2(Integer periodStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ACCEPT_RESERVE (PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS FROM ACCEPT_RESERVE ORDER BY STORE_ID";
	private static final String GET_ONE_STMT = 
		"SELECT PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS FROM ACCEPT_RESERVE WHERE STORE_ID = ? AND PERIOD_ID = ?";
	private static final String DELETE = 
		"DELETE FROM ACCEPT_RESERVE WHERE STORE_ID = ? AND PERIOD_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACCEPT_RESERVE SET START_TIME=?, END_TIME=?, PERIOD_STATUS=? WHERE STORE_ID = ? AND PERIOD_ID = ?";

	@Override
	public List<AcceptReserveVO> search(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@Override
	public void insert(AcceptReserveVO arVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, arVO.getPeriodId());
			pstmt.setString(2, arVO.getStoreId());
			pstmt.setTimestamp(3, arVO.getStartTime());
			pstmt.setTimestamp(4, arVO.getEndTime());
			pstmt.setInt(5, arVO.getPeriodStatus());

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
	public void update(AcceptReserveVO arVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setTimestamp(1, arVO.getStartTime());
//			pstmt.setTimestamp(2, arVO.getEndTime());
//			pstmt.setInt(3, arVO.getPeriodStatus());
//			pstmt.setInt(4, arVO.getPeriodId());
//			pstmt.setString(5, arVO.getStoreId());
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
	public void delete(String storeId, Integer periodId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AcceptReserveVO findByPrimaryKey(String storeId,Integer periodId) {

		AcceptReserveVO arVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeId);
			pstmt.setInt(2, periodId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				arVO = new AcceptReserveVO();
				arVO.setStoreId(rs.getString("STORE_ID"));
				arVO.setPeriodId(rs.getInt("PERIOD_ID"));
				arVO.setStartTime(rs.getTimestamp("START_TIME")); //這邊應該要改格式
				arVO.setEndTime(rs.getTimestamp("END_TIME"));
				arVO.setPeriodStatus(rs.getInt("PERIOD_STATUS"));
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
		return arVO;
	}

	@Override
	public List<AcceptReserveVO> getAll() {
		List<AcceptReserveVO> list = new ArrayList<AcceptReserveVO>();
		AcceptReserveVO arVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// arVO 也稱為 Domain objects
				arVO = new AcceptReserveVO();
				arVO.setStoreId(rs.getString("STORE_ID"));
				arVO.setPeriodId(rs.getInt("PERIOD_ID"));
				arVO.setStartTime(rs.getTimestamp("START_TIME"));
				arVO.setEndTime(rs.getTimestamp("END_TIME"));
				arVO.setPeriodStatus(rs.getInt("PERIOD_STATUS"));
				list.add(arVO); // Store the row in the list
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

		AcceptReserveJDBCDAO dao = new AcceptReserveJDBCDAO();

		// 新增
//		AcceptReserveVO arVO1 = new AcceptReserveVO();
//		arVO1.setStoreId("S000005");
//		arVO1.setPeriodId(1);
//		arVO1.setStartTime(java.sql.Timestamp.valueOf("2020-12-10 10:00:00"));
//		arVO1.setEndTime(java.sql.Timestamp.valueOf("2020-12-10 12:00:00"));
//		arVO1.setPeriodStatus(0);
//		dao.insert(arVO1);

		// 修改 (要可以修改時段狀態)
//		AcceptReserveVO arVO2 = new AcceptReserveVO();
//		arVO1.setStartTime(java.sql.Timestamp.valueOf("2005-01-01"));
//		arVO1.setEndTime(java.sql.Timestamp.valueOf("2005-01-01"));
//		arVO1.setPeriodStatus(0);
//		dao.update(arVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
		AcceptReserveVO arVO3 = dao.findByPrimaryKey("S000001",1);
		System.out.print(arVO3.getStoreId() + ",");
		System.out.print(arVO3.getPeriodId() + ",");
		System.out.print(arVO3.getStartTime() + ",");
		System.out.print(arVO3.getEndTime() + ",");
		System.out.print(arVO3.getPeriodStatus() + ",");
		System.out.println("---------------------");

		// 查詢
		List<AcceptReserveVO> list = dao.getAll();
		for (AcceptReserveVO aar : list) {
			System.out.print(aar.getStoreId() + ",");
			System.out.print(aar.getPeriodId() + ",");
			System.out.print(aar.getStartTime() + ",");
			System.out.print(aar.getEndTime() + ",");
			System.out.print(aar.getPeriodStatus() + ",");
			System.out.println();
		}
	}
}