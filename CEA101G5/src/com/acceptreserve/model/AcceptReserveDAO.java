package com.acceptreserve.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

public class AcceptReserveDAO implements AcceptReserveDAO_interface {

	// 銝���蝔�葉,�������澈 ,��銝��ataSource��
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO ACCEPT_RESERVE (PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS FROM ACCEPT_RESERVE ORDER BY STORE_ID,PERIOD_ID";
	private static final String GET_ONE_STMT = 
		"SELECT PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS FROM ACCEPT_RESERVE WHERE STORE_ID = ? AND PERIOD_ID = ? ORDER BY START_TIME";
	private static final String DELETE = 
		"DELETE FROM ACCEPT_RESERVE WHERE STORE_ID = ? AND PERIOD_ID = ?";
	private static final String UPDATE = 
		"UPDATE ACCEPT_RESERVE SET PERIOD_STATUS=? WHERE STORE_ID = ? AND PERIOD_ID = ?";
	private static final String SEARCH = "SELECT PERIOD_ID,STORE_ID,START_TIME,END_TIME,PERIOD_STATUS FROM ACCEPT_RESERVE WHERE STORE_ID = ? ORDER BY PERIOD_ID";
	private static final String SCHEDULE2 = "SELECT STORE_ID,PERIOD_ID FROM ACCEPT_RESERVE WHERE PERIOD_STATUS = ?";
	
	@Override
	public List<AcceptReserveVO> schedule2(Integer periodStatus) {
		List<AcceptReserveVO> list = new ArrayList<AcceptReserveVO>();
		AcceptReserveVO arVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SCHEDULE2);
			pstmt.setInt(1,periodStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// arVO 銋迂� Domain objects
				arVO = new AcceptReserveVO();
				arVO.setStoreId(rs.getString("STORE_ID"));
				arVO.setPeriodId(rs.getInt("PERIOD_ID"));
//				arVO.setStartTime(rs.getTimestamp("START_TIME"));
//				arVO.setEndTime(rs.getTimestamp("END_TIME"));
//				arVO.setPeriodStatus(rs.getInt("PERIOD_STATUS"));
				list.add(arVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	@Override
	public List<AcceptReserveVO> search(String storeId) {
		List<AcceptReserveVO> list = new ArrayList<AcceptReserveVO>();
		AcceptReserveVO arVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1,storeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// arVO 銋迂� Domain objects
				arVO = new AcceptReserveVO();
				arVO.setStoreId(rs.getString("STORE_ID"));
				arVO.setPeriodId(rs.getInt("PERIOD_ID"));
				arVO.setStartTime(rs.getTimestamp("START_TIME"));
				arVO.setEndTime(rs.getTimestamp("END_TIME"));
				arVO.setPeriodStatus(rs.getInt("PERIOD_STATUS"));
				list.add(arVO); // Store the row in the list
			}

			// Handle any driver errors
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

	@Override
	public void insert(AcceptReserveVO arVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//瘜冽�DBC��repareStatement�敺�1����
			pstmt.setInt(1, arVO.getPeriodId());
			pstmt.setString(2, arVO.getStoreId());
			pstmt.setTimestamp(3, arVO.getStartTime());
			pstmt.setTimestamp(4, arVO.getEndTime());
			pstmt.setInt(5, arVO.getPeriodStatus());

			pstmt.executeUpdate();

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

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, arVO.getPeriodStatus());
			pstmt.setString(2, arVO.getStoreId());
			pstmt.setInt(3, arVO.getPeriodId());
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public AcceptReserveVO findByPrimaryKey(String storeId,Integer periodId) {

		AcceptReserveVO arVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, storeId);
			pstmt.setInt(2, periodId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				arVO = new AcceptReserveVO();
				arVO.setStoreId(rs.getString("STORE_ID"));
				arVO.setPeriodId(rs.getInt("PERIOD_ID"));
				arVO.setStartTime(rs.getTimestamp("START_TIME"));
				arVO.setEndTime(rs.getTimestamp("END_TIME"));
				arVO.setPeriodStatus(rs.getInt("PERIOD_STATUS"));
			}

			// Handle any driver errors
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
	//隞��etAll 撠望��� Select * �List�(銝�蝑�銵�, �����?)
	@Override
	public List<AcceptReserveVO> getAll() {
		List<AcceptReserveVO> list = new ArrayList<AcceptReserveVO>();
		AcceptReserveVO arVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// arVO 銋迂� Domain objects
				arVO = new AcceptReserveVO();
				arVO.setStoreId(rs.getString("STORE_ID"));
				arVO.setPeriodId(rs.getInt("PERIOD_ID"));
				arVO.setStartTime(rs.getTimestamp("START_TIME"));
				arVO.setEndTime(rs.getTimestamp("END_TIME"));
				arVO.setPeriodStatus(rs.getInt("PERIOD_STATUS"));
				list.add(arVO); // Store the row in the list
			}

			// Handle any driver errors
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

	@Override
	public void delete(String storeId, Integer periodId) {
		// TODO Auto-generated method stub
		
	}
	
}