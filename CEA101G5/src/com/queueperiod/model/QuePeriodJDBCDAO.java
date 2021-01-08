package com.queueperiod.model;

import java.sql.*;
import java.util.*;

public class QuePeriodJDBCDAO implements QuePeriodDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO queue_period (queue_period_id, store_id, queue_st, queue_start_time, queue_end_time, queue_no_current) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE queue_period set queue_st=?, queue_start_time=?, queue_end_time=?, queue_no_current=? where queue_period_id=? AND store_id=?";
	private static final String DELETE = "DELETE FROM queue_period where queue_period_id = ? AND store_id = ?";
	private static final String GET_ONE_STMT = "SELECT queue_period_id, store_id, queue_st,  queue_start_time,  queue_end_time, queue_no_current FROM queue_period where queue_period_id = ? AND store_id = ?";
	private static final String GET_ALL_STMT = "SELECT queue_period_id, store_id, queue_st,  queue_start_time,  queue_end_time, queue_no_current FROM queue_period order by queue_period_id";
	private static final String GET_PART_STMT = "SELECT queue_period_id, store_id, queue_st,  queue_start_time,  queue_end_time, queue_no_current FROM queue_period where store_id = ?";

	// �s�W
	@Override
	public void insert(QuePeriodVO queperiodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, queperiodVO.getQueueperiodid());
			pstmt.setString(2, queperiodVO.getStoreid());
			pstmt.setInt(3, queperiodVO.getQueuest());
			pstmt.setTimestamp(4, queperiodVO.getQueuestarttime());
			pstmt.setTimestamp(5, queperiodVO.getQueueendtime());
			pstmt.setInt(6, queperiodVO.getQueuenocurrent());

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

	// �ק�
	@Override
	public void update(QuePeriodVO queperiodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, queperiodVO.getQueuest());
			pstmt.setTimestamp(2, queperiodVO.getQueuestarttime());
			pstmt.setTimestamp(3, queperiodVO.getQueueendtime());
			pstmt.setInt(4, queperiodVO.getQueuenocurrent());
			pstmt.setInt(5, queperiodVO.getQueueperiodid());
			pstmt.setString(6, queperiodVO.getStoreid());

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

	// �R��
	@Override
	public void delete(Integer queperiodid, String storeid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, queperiodid);
			pstmt.setString(2, storeid);

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

	// �̥D��d��(queueperioddid+orderid)
	@Override
	public QuePeriodVO findByPrimaryKey(Integer queperiodid, String storeid) {

		QuePeriodVO queperiodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, queperiodid);
			pstmt.setString(2, storeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				queperiodVO = new QuePeriodVO();
				queperiodVO.setQueueperiodid(rs.getInt("queue_period_id"));
				queperiodVO.setStoreid(rs.getString("store_id"));
				queperiodVO.setQueuest(rs.getInt("queue_st"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_start_time"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_end_time"));
				queperiodVO.setQueuenocurrent(rs.getInt("queue_no_current"));
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
		return queperiodVO;
	}

	// �d����
	@Override
	public List<QuePeriodVO> getAll() {
		List<QuePeriodVO> list = new ArrayList<QuePeriodVO>();
		QuePeriodVO queperiodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				queperiodVO = new QuePeriodVO();
				queperiodVO.setQueueperiodid(rs.getInt("queue_period_id"));
				queperiodVO.setStoreid(rs.getString("store_id"));
				queperiodVO.setQueuest(rs.getInt("queue_st"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_start_time"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_end_time"));
				queperiodVO.setQueuenocurrent(rs.getInt("queue_no_current"));
				list.add(queperiodVO);
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

	// �̩����d��
	@Override
	public List<QuePeriodVO> findByStoreid(String storeid) {
		List<QuePeriodVO> list = new ArrayList<QuePeriodVO>();
		QuePeriodVO queperiodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PART_STMT);

			pstmt.setString(1, storeid);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				queperiodVO = new QuePeriodVO();
				queperiodVO.setQueueperiodid(rs.getInt("queue_period_id"));
				queperiodVO.setStoreid(rs.getString("store_id"));
				queperiodVO.setQueuest(rs.getInt("queue_st"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_start_time"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_end_time"));
				queperiodVO.setQueuenocurrent(rs.getInt("queue_no_current"));
				list.add(queperiodVO);
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
		QuePeriodJDBCDAO dao = new QuePeriodJDBCDAO();

		// insert
//		QuePeriodVO quepVO1 = new QuePeriodVO();
//		quepVO1.setQueueperiodid(1);
//		quepVO1.setStoreid("S000001");
//		quepVO1.setQueuest(1);
////		quepVO1.setQueuestarttime(java.sql.Date.valueOf("2020-01-01"));
////		quepVO1.setQueueendtime(java.sql.Date.valueOf("2020-02-01"));
////		quepVO1.setStarttime(new Timestamp(System.currentTimeMillis()));
//		quepVO1.setStarttime(java.sql.Timestamp.valueOf("2020-01-01 20:12:12"));
//		quepVO1.setEndtime(new Timestamp(System.currentTimeMillis()));
//		quepVO1.setQueuenocurrent(1);
//		dao.insert(quepVO1);

		// update
//		QuePeriodVO quepVO2 = new QuePeriodVO();
//		quepVO2.setQueueperiodid(1);
//		quepVO2.setStoreid("S000001");
//		quepVO2.setQueuest(0);
//		quepVO2.setStarttime(java.sql.Timestamp.valueOf("2020-01-01 12:12:12"));
//		quepVO2.setEndtime(new Timestamp(System.currentTimeMillis()));
//		quepVO2.setQueuenocurrent(3);
//		dao.update(quepVO2);

		// �R��
//		dao.delete(7, "S000001");

		// �H�D��d��
//		QuePeriodVO queperiodVO3 = dao.findByPrimaryKey(2, "S000001");
//		System.out.print(queperiodVO3.getQueueperiodid() + ",");
//		System.out.print(queperiodVO3.getStoreid() + ",");
//		System.out.print(queperiodVO3.getQueuest() + ",");
//		System.out.print(queperiodVO3.getQueuestarttime() + ",");
//		System.out.print(queperiodVO3.getQueueendtime() + ",");
//		System.out.println(queperiodVO3.getQueuenocurrent());
//		System.out.println("-----------------------");

		// �d�ߥ���
//		List<QueperiodVO> list = dao.getAll();
//		for(QueperiodVO aQue : list) {
//			System.out.print(aQue.getQueueperiodid() + ",");
//			System.out.print(aQue.getStoreid() + ",");
//			System.out.print(aQue.getQueuest() + ",");
//			System.out.print(aQue.getQueuestarttime() + ",");
//			System.out.print(aQue.getQueueendtime() + ",");
//			System.out.println(aQue.getQueuenocurrent());
//			System.out.println();
//	}

		// �̩����d��
		List<QuePeriodVO> list = dao.findByStoreid("S000001");
		for (QuePeriodVO sQue : list) {
			System.out.print(sQue.getQueueperiodid() + ",");
			System.out.print(sQue.getStoreid() + ",");
			System.out.print(sQue.getQueuest() + ",");
			System.out.print(sQue.getQueuestarttime() + ",");
			System.out.print(sQue.getQueueendtime() + ",");
			System.out.println(sQue.getQueuenocurrent());
		}
	}

}
