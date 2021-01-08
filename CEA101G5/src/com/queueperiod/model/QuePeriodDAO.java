package com.queueperiod.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class QuePeriodDAO implements QuePeriodDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO queue_period (queue_period_id, store_id, queue_st, queue_start_time, queue_end_time, queue_no_current) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT queue_period_id, store_id, queue_st,  queue_start_time,  queue_end_time, queue_no_current FROM queue_period order by queue_period_id";
	private static final String GET_ONE_STMT = "SELECT queue_period_id, store_id, queue_st,  queue_start_time,  queue_end_time, queue_no_current FROM queue_period where queue_period_id = ? AND store_id = ?";
	private static final String GET_PART_STMT = "SELECT queue_period_id, store_id, queue_st,  queue_start_time,  queue_end_time, queue_no_current FROM queue_period where store_id = ?";
	private static final String DELETE = "DELETE FROM queue_period where queue_period_id = ? AND store_id = ?";
	private static final String UPDATE = "UPDATE queue_period set queue_st=?, queue_start_time=?, queue_end_time=?, queue_no_current=? where queue_period_id=? AND store_id=?";

	// �s�W
	@Override
	public void insert(QuePeriodVO queperiodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, queperiodVO.getQueueperiodid());
			pstmt.setString(2, queperiodVO.getStoreid());
			pstmt.setInt(3, queperiodVO.getQueuest());
			pstmt.setTimestamp(4, queperiodVO.getQueuestarttime());
			pstmt.setTimestamp(5, queperiodVO.getQueueendtime());
			pstmt.setInt(6, queperiodVO.getQueuenocurrent());

			pstmt.executeUpdate();

			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, queperiodVO.getQueuest());
			pstmt.setTimestamp(2, queperiodVO.getQueuestarttime());
			pstmt.setTimestamp(3, queperiodVO.getQueueendtime());
			pstmt.setInt(4, queperiodVO.getQueuenocurrent());
			pstmt.setInt(5, queperiodVO.getQueueperiodid());
			pstmt.setString(6, queperiodVO.getStoreid());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, queperiodid);
			pstmt.setString(2, storeid);

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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
				queperiodVO.setQueueendtime(rs.getTimestamp("queue_end_time"));
				queperiodVO.setQueuenocurrent(rs.getInt("queue_no_current"));
			}
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

	@Override
	public List<QuePeriodVO> getAll() {
		List<QuePeriodVO> list = new ArrayList<QuePeriodVO>();
		QuePeriodVO queperiodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				queperiodVO = new QuePeriodVO();
				queperiodVO.setQueueperiodid(rs.getInt("queue_period_id"));
				queperiodVO.setStoreid(rs.getString("store_id"));
				queperiodVO.setQueuest(rs.getInt("queue_st"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_start_time"));
				queperiodVO.setQueueendtime(rs.getTimestamp("queue_end_time"));
				queperiodVO.setQueuenocurrent(rs.getInt("queue_no_current"));
				list.add(queperiodVO);
			}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PART_STMT);

			pstmt.setString(1, storeid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				queperiodVO = new QuePeriodVO();
				queperiodVO.setQueueperiodid(rs.getInt("queue_period_id"));
				queperiodVO.setStoreid(rs.getString("store_id"));
				queperiodVO.setQueuest(rs.getInt("queue_st"));
				queperiodVO.setQueuestarttime(rs.getTimestamp("queue_start_time"));
				queperiodVO.setQueueendtime(rs.getTimestamp("queue_end_time"));
				queperiodVO.setQueuenocurrent(rs.getInt("queue_no_current"));
				list.add(queperiodVO);
			}

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
}
